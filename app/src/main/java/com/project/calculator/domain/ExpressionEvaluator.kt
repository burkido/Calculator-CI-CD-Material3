package com.project.calculator.domain

/**
 * Context Free Grammar for the [ExpressionParser]
 * Expression -> Term + Term | Term - Term | Term
 * Term -> Factor * Factor | Factor / Factor | Factor
 * Factor -> Number | (Expression) | - Factor | + Factor
 * Number -> Digit | Digit Number
 * Digit -> 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9
 *
 * Thanks to @phillipLackner
*/

class ExpressionEvaluator(
    private val expression: List<ExpressionPart>,
) {

    fun evaluate(): Double {
        return evalExpression(expression).value
    }

    private fun evalExpression(expression: List<ExpressionPart>): ExpressionResult {
        val term = evalTerm(expression)
        var remaining = term.remainingExpression
        var sum = term.value

        while (true) {
            when (remaining.firstOrNull()) {
                ExpressionPart.Op(Operation.ADD) -> {
                    val nextTerm = evalTerm(remaining.drop(1))
                    sum += nextTerm.value
                    remaining = nextTerm.remainingExpression
                }
                ExpressionPart.Op(Operation.SUBTRACT) -> {
                    val nextTerm = evalTerm(remaining.drop(1))
                    sum -= nextTerm.value
                    remaining = nextTerm.remainingExpression
                }
                else -> return ExpressionResult(remaining, sum)
            }
        }
    }

    private fun evalTerm(expression: List<ExpressionPart>): ExpressionResult {
        val factor = evalFactor(expression)
        var remaining = factor.remainingExpression
        var sum = factor.value

        while (true) {
            when (remaining.firstOrNull()) {
                ExpressionPart.Op(Operation.MULTIPLY) -> {
                    val nextFactor = evalFactor(remaining.drop(1))
                    sum *= nextFactor.value
                    remaining = nextFactor.remainingExpression
                }
                ExpressionPart.Op(Operation.DIVIDE) -> {
                    val nextFactor = evalFactor(remaining.drop(1))
                    sum /= nextFactor.value
                    remaining = nextFactor.remainingExpression
                }
                ExpressionPart.Op(Operation.PERCENT) -> {
                    val nextFactor = evalFactor(remaining.drop(1))
                    //sum %= nextFactor.value
                    sum *= nextFactor.value / 100.0
                    remaining = nextFactor.remainingExpression
                }
                else -> { return ExpressionResult(remaining, sum) }
            }
        }
    }

    // A factor is either a number or an expression in parentheses
    // e.g. 5.0, -7.5, -(3+4*5)
    // But NOT something like 3 * 5, 4 + 5
    private fun evalFactor(expression: List<ExpressionPart>): ExpressionResult {
        return when (val part = expression.firstOrNull()) {
            ExpressionPart.Op(Operation.ADD) -> evalFactor(expression.drop(1))
            ExpressionPart.Op(Operation.SUBTRACT) -> evalFactor(expression.drop(1)).run {
                ExpressionResult(remainingExpression, -value)
            }
            ExpressionPart.Op(Operation.PERCENT) -> evalTerm(expression.drop(1))
            ExpressionPart.Parenthesis(ParanthesesType.Open) -> {
                /*val result = evalExpression(expression.drop(1))
                ExpressionResult(result.remainingExpression.drop(1), result.value)*/
                evalExpression(expression.drop(1)).run {
                    ExpressionResult(remainingExpression.drop(1), value)
                }
            }
            is ExpressionPart.Number -> {
                ExpressionResult(expression.drop(1), (part as ExpressionPart.Number).value)
            }
            else -> throw RuntimeException("Invalid part: $part")
        }
    }

    /*
    *  This data class represents a value of current evaluation. -3*(3+5). This class accumulates the first value of expression
    * */
    data class ExpressionResult(
        val remainingExpression: List<ExpressionPart>,
        val value: Double,
    )
}

