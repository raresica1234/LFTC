package domain

class Identifier(
    val name: String
) : Value {
    override fun hashFunction() : Int {
        return name.hashCode()
    }
}