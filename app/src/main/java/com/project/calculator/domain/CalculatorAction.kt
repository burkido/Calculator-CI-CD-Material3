package com.project.calculator.domain

sealed interface CalculatorAction {
    data class EnterNumber(val number: Int) : CalculatorAction
    data class EnterOperation(val operation: Operation) : CalculatorAction
    object EnterDecimal : CalculatorAction
    object Parantheses : CalculatorAction
    object Clear : CalculatorAction
    object Delete : CalculatorAction
    object Calculate : CalculatorAction
}