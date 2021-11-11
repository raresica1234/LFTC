class MenuCommand(val description: String, val function: () -> String) {
	fun execute() : String {
		return function()
	}
}