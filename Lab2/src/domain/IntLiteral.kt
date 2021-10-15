package domain

class IntLiteral(val value: Int) : Value{
    override fun hashFunction(): Int {
        return value.toString().hashCode()
    }
}