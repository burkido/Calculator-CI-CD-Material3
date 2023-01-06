package com.project.calculator

import com.project.calculator.domain.ExpressionPart
import com.project.calculator.domain.Operation
import com.project.calculator.domain.ParanthesesType

class ExpressionParserCoPilot(
    private val expression: String
) {
    private var index = 0

    fun parse(): List<ExpressionPart> {
        val parts = mutableListOf<ExpressionPart>()
        while (index < expression.length) {
            val part = parseNextPart()
            parts.add(part)
        }
        return parts
    }

    private fun parseNextPart(): ExpressionPart {
        val char = expression[index]
        return when {
            char.isDigit() -> parseNumber()
            char.isOperation() -> parseOperation()
            //char.isParenthesis() -> parseParenthesis()
            else -> throw IllegalArgumentException("Unknown character: $char")
        }
    }

    private fun parseNumber(): ExpressionPart {
        val number = StringBuilder()
        while (index < expression.length && expression[index].isDigit()) {
            number.append(expression[index])
            index++
        }
        return ExpressionPart.Number(number.toString().toDouble())
    }

    private fun parseOperation(): ExpressionPart {
        val operation = expression[index]
        index++
        return ExpressionPart.Op(operationFromSymbol(operation))
    }

    private fun parseParenthesis(): ExpressionPart {
        val parenthesis = expression[index]
        index++
        return ExpressionPart.Parenthesis(parenthesisFromSymbol(parenthesis))
    }

    private fun operationFromSymbol(symbol: Char): Operation {
        return Operation.values().find { it.symbol == symbol } ?: throw IllegalArgumentException("Unknown operation symbol: $symbol")
    }

    private fun parenthesisFromSymbol(symbol: Char): ParanthesesType {
        //return ParanthesesType.values().find { it.symbol == symbol } ?: throw IllegalArgumentException("Unknown parenthesis symbol: $symbol")
        return when (symbol) {
            '(' -> ParanthesesType.Open
            ')' -> ParanthesesType.Close
            else -> throw IllegalArgumentException("Unknown parenthesis symbol: $symbol")
        }
    }

    private fun Char.isOperation(): Boolean {
        return Operation.values().map { it.symbol }.contains(this)
    }

//    private fun Char.isParenthesis(): Boolean {
//        return true if this.equals('(') || this.equals(')')
//    }
}
