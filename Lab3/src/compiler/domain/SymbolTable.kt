package compiler.domain

class SymbolTable(private var capacity: Int, private val alpha: Float) {
    private var values: ArrayList<ArrayList<Token>> = ArrayList(capacity)

    private var size = 0;

    init {
        initializeLists()
    }

    fun add(token: Token) {
        checkSizeAndGrow()
        size++
        val position = hash(token)
        values[position].add(token)
    }

    fun find(token: Token): Position {
        val position = hash(token)
        for (i in 0 until values[position].size) {
            if (values[position][i].value == token.value) {
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

    private fun hash(token: Token): Int {
        return token.hash() % capacity
    }

    override fun toString(): String {
        var result = ""
        for (i in 0 until values.size) {
            val list = values[i]
            if (list.isEmpty())
                continue
            result += "$i."

            list.forEach {
                result += "$it "
            }

            result += "\n"
        }
        return result
    }
}