import compiler.tokenizer.LexicalAnalyzer
import java.io.File

fun main() {

	val program = "res/p2.txt"

	val contents = File(program).bufferedReader().readLines()

	val lexicalAnalyzer = LexicalAnalyzer(contents.reduce { it1, it2 -> it1 + "\n" + it2 })

	if (lexicalAnalyzer.validProgram) {
		File("res/ST.out").bufferedWriter().apply {
			write(lexicalAnalyzer.symbolTable.toString())
			close()
		}
		File("res/PIF.out").bufferedWriter().apply {
			write(lexicalAnalyzer.pifToString())
			close()
		}
	}
	else {
		File("res/ST.out").bufferedWriter().apply {
			write("")
			close()
		}
		File("res/PIF.out").bufferedWriter().apply {
			write(lexicalAnalyzer.error)
			close()
		}
	}
}