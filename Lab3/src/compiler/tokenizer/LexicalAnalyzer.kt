package compiler.tokenizer

import compiler.domain.PIFEntry
import compiler.domain.SymbolTable
import compiler.domain.Token

class LexicalAnalyzer(var data: String) {

	// No resizing cause hash table
	// Capacity go brrrrrrr
	val symbolTable = SymbolTable(300, 0.8f);
	val programInternalForm: ArrayList<PIFEntry> = ArrayList()

	var currentLine = 0;

	var validProgram = true;

	var error: String = ""

	init {
		performTokenizing();
	}

	private fun performTokenizing() {
		val tokens = Token.TOKENS.map { Regex("^$it") }
		val identifier = Regex("^${Token.IDENTIFIER}")
		val literals = Token.LITERALS.map { Regex("^$it") }

		while (data.isNotBlank()) {
			data = removeWhiteSpaces(data)

			var found = false;

			var token = Token("")

			tokens.forEach {
				if (!found) {
					it.find(data)?.let { answer ->
						token = Token(answer.value)
						programInternalForm.add(PIFEntry(token, null, currentLine))
						found = true
					}
				}
			}

			if (!found) {
				identifier.find(data)?.let { answer ->
					token = Token(answer.value)
					addLiteralOrConstant(token, "identifier")
					found = true
				}
			}

			if (!found) {
				literals.forEach {
					it.find(data)?.let { answer ->
						token = Token(answer.value)
						addLiteralOrConstant(token, "constant")
						found = true
					}
				}
			}

			if (!found) {
				error += "Lexical error at line $currentLine, unknown token.\n"
				error += "Trying to parse: " + data.substringBefore(" ")
				validProgram = false
				return
			}

			// Remove the found token
			data = data.substring(token.value.length)
		}
	}

	private fun removeWhiteSpaces(currentString: String): String {
		var result = currentString
		while (result.startsWith(" ") || result.startsWith("\n") || result.startsWith("\t")) {
			if (result.startsWith('\n')) {
				currentLine++
			}
			result = result.substring(1)
		}
		return result
	}

	private fun addLiteralOrConstant(token: Token, type: String) {
		var position = symbolTable.find(token)
		if (position.left == -1) { // token not found, we must add it
			symbolTable.add(token)
			position = symbolTable.find(token) // making good use of that O(1)
		}

		programInternalForm.add(PIFEntry(Token(type), position, currentLine))
	}

	fun pifToString(): String {
		var result: String = ""

		programInternalForm.forEach {
			result += "(\"${it.token}\", ${it.position?.left ?: "-1"}, ${it.currentLine})\n"
		}

		return result
	}

}