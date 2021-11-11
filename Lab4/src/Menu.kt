class Menu {

	private val commands = ArrayList<MenuCommand>()

	fun start() {
		while (true) {
			displayMenu()
			print("> ")
			val command = Integer.parseInt(readLine())

			if (command == commands.size + 1) {
				return
			}

			println(commands.getOrNull(command - 1)?.execute() ?: "Invalid command")
		}
	}

	private fun displayMenu() {
		commands.forEachIndexed { index, menuCommand -> println("${index + 1}. ${menuCommand.description}") }
		println("${commands.size + 1}. Exit")
	}

	fun addMenuCommand(menuCommand: MenuCommand) {
		commands.add(menuCommand)
	}

}