data class Transition(val source: String, val destination: String, val value: String) {
	override fun toString(): String {
		return "($source, $destination, $value)"
	}
}