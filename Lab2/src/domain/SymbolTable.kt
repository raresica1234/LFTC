package domain

class SymbolTable(private var capacity: Int, private val alpha: Float) {
    private var values: ArrayList<ArrayList<Value>> = ArrayList(capacity)

    private var size = 0;

    fun add(token: Value) {
        checkSizeAndGrow()
        size++
        val position = hash(token)
        values[position].add(token)
    }

    fun find(token: Value) : Boolean {
        val position = hash(token)
        for (currentToken in values[position]) {
            if (currentToken == token) {
                return true
            }
        }
        return false
    }

    fun checkSizeAndGrow() {
        val lastValues = values
        if(size.toFloat() / capacity.toFloat() >= alpha) {
            capacity *= 2
            values = ArrayList(capacity)
            for (tokenList in lastValues) {
                for (token in tokenList) {
                    add(token)
                }
            }
        }
    }

    private fun hash(token: Value) : Int {
        return token.hashFunction() % capacity
    }
}