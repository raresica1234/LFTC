package domain

class SymbolTable(private var capacity: Int, private val alpha: Float) {
    private var values: ArrayList<ArrayList<Value>> = ArrayList(capacity)

    private var size = 0;

    init {
        initializeLists()
    }

    fun add(token: Value) {
        checkSizeAndGrow()
        size++
        val position = hash(token)
        values[position].add(token)
    }

    fun find(token: Value): Position {
        val position = hash(token)
        for (i in 0 until values[position].size) {
            if (values[position][i] == token) {
                return Position(position, i)
            }
        }
        return Position(-1, -1)
    }

    private fun checkSizeAndGrow() {
        val lastValues = values
        if (size.toFloat() / capacity.toFloat() >= alpha) {
            capacity *= 2
            values = ArrayList(capacity)
            initializeLists()
            for (tokenList in lastValues) {
                for (token in tokenList) {
                    add(token)
                }
            }
        }
    }

    private fun initializeLists() {
        for (i in 0 until capacity) {
            values.add(i, ArrayList())
        }
    }

    private fun hash(token: Value): Int {
        return token.hashFunction() % capacity
    }
}