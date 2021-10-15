import domain.StringLiteral
import domain.SymbolTable

fun main() {
    val table = SymbolTable(10, 0.7f)

    table.add(StringLiteral("test"))
    table.add(StringLiteral("a"))
    table.add(StringLiteral("b"))
    table.add(StringLiteral("tset"))
    table.add(StringLiteral("c"))

    println(table.find(StringLiteral("test")))
    println(table.find(StringLiteral("tset")))
}