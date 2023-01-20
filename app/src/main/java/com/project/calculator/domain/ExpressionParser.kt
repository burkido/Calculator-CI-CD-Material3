package com.project.calculator.domain


class ExpressionParser(
    private val expression: String,
) {

    fun parse(): List<ExpressionPart> {
        val result = mutableListOf<ExpressionPart>()

        var i = 0
        while (i < expression.length) {
            val currentChar = expression[i]
            when {
                currentChar in operationSymbols -> {
                    result.add(
                        ExpressionPart.Op(operationFromSymbol(currentChar))
                    )
                }
                currentChar.isDigit() -> {
                    i = parseNumber(i, result)
                    continue
                }
                currentChar in "()" -> {
                    parseParantheses(currentChar, result)
                }
            }
            i++
        }

        return result
    }

    /*
    * This code is a function that parses a number from a string expression. It starts by building a string of characters that are digits or periods, and then converts that string to a double and
    *  adds it as an "ExpressionPart.Number" object to a list passed as an argument. The function returns the index where the parsing left off in the expression.
    * */
    private fun parseNumber(currIdx: Int, result: MutableList<ExpressionPart>): Int {
        var i = currIdx
        val numberInString = buildString {
            while (i < expression.length && expression[i] in "0123456789.") {
                append(expression[i])
                i++
            }
        }

        result.add(ExpressionPart.Number(numberInString.toDouble()))
        return i
    }

    private fun parseParantheses(currentChar: Char, result: MutableList<ExpressionPart>) {
        result.add(
            ExpressionPart.Parenthesis(
                type = when (currentChar) {
                    '(' -> ParanthesesType.Open
                    ')' -> ParanthesesType.Close
                    else -> throw IllegalArgumentException("Invalid parentheses")
                }
            )
        )
    }
}
