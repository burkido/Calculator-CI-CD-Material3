package com.project.calculator.domain

enum class Operation(val symbol: Char) {
    ADD('+'),
    SUBTRACT('-'),
    MULTIPLY('*'),
    DIVIDE('/'),
    PERCENT('%');
}
    //could be something like this Operation.values ...
    val operationSymbols = Operation.values().map { it.symbol }.joinToString("")

   fun operationFromSymbol(symbol: Char): Operation {
        return Operation.values().find { it.symbol == symbol } ?: throw IllegalArgumentException("Unknown operation symbol: $symbol")
    }
