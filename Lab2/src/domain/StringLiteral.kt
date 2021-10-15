package domain

data class StringLiteral(val value: String) : Value {
    override fun hashFunction(): Int {
        return value.hashCode()
    }

}
