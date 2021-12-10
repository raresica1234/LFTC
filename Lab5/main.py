import json

from Grammar import Grammar
from Parser import Parser


def readGrammarFromFile(filePath):
    grammarInput = json.load(open(filePath, "r"))
    return Grammar(grammarInput["nonTerminals"], grammarInput["terminals"], grammarInput["start"], grammarInput["productions"])


def main():
    parser = Parser(readGrammarFromFile("G3.json"))

    print(parser.firstMap)

main()
