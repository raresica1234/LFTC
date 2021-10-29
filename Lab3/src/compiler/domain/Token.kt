package compiler.domain;


class Token(val value: String) {
	companion object {
		private const val COMMENT = "//.*\n";
		private val COMPOSED_TOKENS = arrayOf("==", "!=", ">=", "<=", "\\+\\+", "--", "+=", "-=", "\\*=", "/=")

		private val NORMAL_TOKENS =
			arrayOf("\\+", "-", "\\*", "/", "%", ";", ",", "=", "<", ">", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]")
		private val KEYWORDS =
			arrayOf(
				"\\bint\\b",
				"\\bboolean\\b",
				"\\bstring\\b",
				"\\bprint",
				"\\breadInt\\b",
				"\\breadString\\b",
				"\\bif\\b",
				"\\belse\\b",
				"\\bwhile\\b",
				"\\bfor\\b",
				"\\bmain\\b"
			)
		val LITERALS = arrayOf(
			"\\b{0,1}([1-9])*[0-9]\\b", "\"[ 0-9a-zA-Z@!?'_]*\"", "\\b(true|false)\\b"
		)

		const val IDENTIFIER = "[a-zA-Z][a-zA-Z0-9_]*";

		val TOKENS = arrayOf(COMMENT) + COMPOSED_TOKENS + NORMAL_TOKENS + KEYWORDS
	}

	fun hash(): Int {
		return value.codePoints().sum()
	}


	override fun toString(): String {
		return value
	}
}
