import json


def readGrammarFromFile(filePath):
    grammarInput = json.load(open(filePath, "r"))
    return Grammar(grammarInput["nonTerminals"], grammarInput["terminals"], grammarInput["start"], grammarInput["productions"])

class Grammar:
    def __init__(self, nonTerminals, terminals, start, productions):
        self.nonTerminals = nonTerminals
        self.terminals = terminals
        self.start = start
        self.productions = productions
        for key in self.productions.keys():
            for i in range(0, len(self.productions[key])):
                self.productions[key][i] = self.productions[key][i].split(" ")

    def CFGCheck(self):
        for key in self.productions:
            if len(key.split(" ")) > 1:
                # it has two non-terminals and thus it's not context free
                return False
        return True

    def productionsToString(self):
        result = ""
        for productionKey in self.productions.keys():
            result += productionKey + " -> " + str(self.productions[productionKey]) + "\n"
        return result

    def __str__(self):
        result = "Non terminals: " + str(self.nonTerminals)
        result += "\nTerminals: " + str(self.terminals)
        result += "\nStart: " + str(self.start)
        result += "\nProductions:\n" + self.productionsToString()

        result += "\n\nIs context free grammar? " + str(self.CFGCheck())

        return result


def main():
    print(readGrammarFromFile("G2.json"))


main()
