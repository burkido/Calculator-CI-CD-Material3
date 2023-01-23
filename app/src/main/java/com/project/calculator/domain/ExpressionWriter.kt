package com.project.calculator.domain

class ExpressionWriter {

    var expression = ""

    fun handleAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.EnterNumber -> handleNumber(action.number)
            is CalculatorAction.EnterOperation -> handleOperation(action.operation)
            CalculatorAction.EnterDecimal -> handleDecimal()
            CalculatorAction.Parantheses -> handleParantheses()
            CalculatorAction.Clear -> handleClear()
            CalculatorAction.Delete -> handleDelete()
            CalculatorAction.Calculate -> handleCalculate()
        }
    }

    private fun handleNumber(number: Int) {
        expression += number
    }

    private fun handleOperation(operation: Operation) {
        if (canEnterOperation(operation))
            expression += operation.symbol
    }

    private fun handleDecimal() {
        if (canEnterDecimal())
            expression += "."
    }

    /**
     * We want to prevent something like this: (1+2*(3+4)
     * */
    private fun handleParantheses() {
        val openingCount = expression.count { it == '(' }
        val closingCount = expression.count { it == ')' }

        expression += when {
            expression.isEmpty() || expression.last() in "$operationSymbols(" -> "("
            expression.last() in "0123456789)" && openingCount == closingCount -> return
            else -> ")"
        }
    }

    private fun handleClear() {
        expression = ""
    }

    private fun handleDelete() {
        expression = expression.dropLast(1)
    }

    private fun handleCalculate() {
        val parser = ExpressionParser(isCalculable())
        val evaluator = ExpressionEvaluator(parser.parse())
        expression = evaluator.evaluate().toString()
    }

    // HELPER FUNCTIONS

    /**
     * We want to prevent something like this: 1++ or 1+* or 1+/
     * */
    private fun canEnterOperation(operation: Operation): Boolean {
        if (operation in listOf(Operation.ADD, Operation.SUBTRACT)) {
            return expression.isEmpty() || expression.last() in "$operationSymbols()0123456789"
        }
        return expression.isNotEmpty() && expression.last() in "0123456789)"

    }

    /**
     * We want to prevent something like this: or 1.+ or 1.2.3
     * */
    private fun canEnterDecimal(): Boolean {
        if (expression.isEmpty() || expression.last() in "$operationSymbols.()") return false
        return !expression.takeLastWhile {
            it in "0123456789."
        }.contains(".")
    }

    /**
     * We want to prevent something like this: 1+ or 1+2*
     * */
    private fun isCalculable(): String {
        val newExpression = expression.takeLastWhile {
            it in "$operationSymbols(."
        }

        if (newExpression.isEmpty())
            return "0"

        return newExpression
    }
}
