import java.io.File

class FiniteAutomata(filename: String) {
	private val states = ArrayList<String>()
	private var initialState: String = ""
	private val alphabet = ArrayList<String>()
	private val finalStates = ArrayList<String>()
	private val transitions = ArrayList<Transition>()

	var isDeterministic = true

	init {
		loadAutomata(filename)
	}


	private fun loadAutomata(filename: String) {
		val lines = File(filename).readLines()
		lines[0].split(" ").forEach { states.add(it) }
		initialState = lines[1]
		lines[2].split(" ").forEach { alphabet.add(it) }
		lines[3].split(" ").forEach { finalStates.add(it) }

		(4 until lines.size).forEach {
			val data = lines[it].split(" ")
			val transition = Transition(data[0], data[1], data[2])
			transitions.forEach { tr ->
				if (tr.source == transition.source && tr.value == transition.value) {
					isDeterministic = false
				}
			}

			transitions.add(transition)
		}
	}

	fun verify(sequence: String): Boolean {
		return verifyRecursive(initialState, sequence, 0)
	}

	private fun verifyRecursive(currentState: String, sequence: String, index: Int): Boolean {
		if (index == sequence.length)
			return finalStates.contains(currentState)

		val symbol = sequence[index].toString()
		val acceptedTransitions = ArrayList<Transition>()

		transitions.forEach { if (it.source == currentState && it.value == symbol) acceptedTransitions.add(it) }
		if (acceptedTransitions.size == 0)
			return false

		return acceptedTransitions.any { verifyRecursive(it.destination, sequence, index + 1) }
	}

	fun getStatesAsString() = states.toString()

	fun getAlphabetAsString() = alphabet.toString()

	fun getTransitionsAsString(): String {
		var result = ""
		transitions.forEach { result += it.toString() + "\n" }

		return result
	}

	fun getFinalStatesAsString() = finalStates.toString()

}