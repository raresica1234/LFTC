fun main() {
	val fa = FiniteAutomata("res/FA.in")
	val menu = Menu()

	menu.addMenuCommand(MenuCommand("Display set of states") {
		fa.getStatesAsString()
	})
	menu.addMenuCommand(MenuCommand("Display the alphabet") {
		fa.getAlphabetAsString()
	})

	menu.addMenuCommand(MenuCommand("Display set of final states") {
		fa.getFinalStatesAsString()
	})

	menu.addMenuCommand(MenuCommand("Display all transitions") {
		fa.getTransitionsAsString()
	})

	menu.addMenuCommand(MenuCommand("Test if automata is deterministic") {
		if (fa.isDeterministic) "The automata is deterministic" else "The automata is not deterministic"
	})

	menu.addMenuCommand(MenuCommand("Test sequence") {
		if (fa.isDeterministic) {
			print("Give sequence: ")
			val data = readLine()
			data?.let {
				if (fa.verify(it))
					"Sequence is accepted"
				else
					"Sequence is not accepted"
			} ?: "Invalid sequence"
		} else {
			"Automata is not deterministic"
		}
	})

	menu.start()
}