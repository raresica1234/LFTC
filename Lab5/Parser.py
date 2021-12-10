from Grammar import Grammar


epsilon = "EPSILON"


class Parser:
    def __init__(self, grammar: Grammar):
        self.firstMap = {nonterminal: None for nonterminal in grammar.nonTerminals}
        self.followMap = {}
        self.grammar = grammar

        self.initializeFirstMap()
        self.initializeFollowMap()

    def initializeFirstMap(self):
        changed = False

        for key in self.grammar.productions.keys():
            changed = self.addTokenInSet(key, self.grammar.productions[key]) or changed

        if changed:
            self.initializeFirstMap()

    def initializeFollowMap(self):
        for key in self.grammar.productions.keys():
            for rule in self.grammar.productions[key]:
                pass


    def addTokenInSet(self, key, production):
        tokensToAdd = []

        for value in production:
            token = value[0]
            if token in self.grammar.terminals:  # X is a terminal
                tokensToAdd.append(token)
                break

            if token == epsilon:
                tokensToAdd.append(epsilon)
                break

        tokenTerminals = self.firstMap[key]

        if tokenTerminals is None:
            tokenTerminals = []
            self.firstMap[key] = []
            for value in production:
                tokenTerminals.append(value[0])

        for i in range(len(tokenTerminals)):
            if tokenTerminals[i] in self.grammar.nonTerminals:
                for prodTerminal in self.grammar.productions[tokenTerminals[i]]:
                    tokenTerminals.append(prodTerminal[0])
                tokenTerminals.remove(tokenTerminals[i])

        tokensToAdd += tokenTerminals

        return self.addItemsInSet(self.firstMap[key], tokensToAdd)

    def addItemsInSet(self, set, items):
        result = False

        for item in items:
            result = self.addItemInSet(set, item) or result

        return result

    def addItemInSet(self, set, item):
        if item not in set:
            set.append(item)
            return True

        return False
