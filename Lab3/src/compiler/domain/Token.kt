package compiler.domain;


class Token(val value: String) {
    companion object {
        private const val COMMENT = "//.*\n";
        private val COMPOSED_TOKENS = arrayOf("==", "!=", ">=", "<=", "\\+\\+", "--", "+=", "-=", "\\*=", "/=")

        private val NORMAL_TOKENS = arrayOf("\\+", "-", "\\*", "/", "%", ";", ",", "=", "<", ">", "\\(", "\\)", "\\{", "\\}", "\\[", "\\]")
        private val KEYWORDS =
            arrayOf("int", "boolean", "string", "print", "readInt", "readString", "if", "else", "while", "for", "main")
        val LITERALS = arrayOf(
            "[-+]{0,1}([1-9])*[0-9]", "\"[ 0-9a-zA-Z@!?']*\"", "true|false"
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
