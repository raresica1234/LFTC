package compiler.domain

data class PIFEntry(val token: Token, val position: Position?, val currentLine: Int) {
    override fun toString(): String {
        return "PIFEntry(\"$token\", $position, $currentLine)"
    }
}