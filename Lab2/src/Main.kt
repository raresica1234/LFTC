import domain.StringLiteral
import domain.SymbolTable

fun main() {
    val table: SymbolTable = SymbolTable(10, 0.7f)

    table.add(StringLiteral("test"))
    table.add(StringLiteral("a"))
    table.add(StringLiteral("b"))
    table.add(StringLiteral("test"))
    table.add(StringLiteral("c"))

    if (table.find(StringLiteral("test"))) {
        println("Found test")
    }

}