package com.project.calculator.domain

sealed interface ExpressionPart {
    data class Number(val value: Double) : ExpressionPart
    data class Op(val operation: Operation) : ExpressionPart
    //data class Parenthesis(val isOpen: Boolean) : ExpressionPart
    data class Parenthesis(val type: ParanthesesType) : ExpressionPart
}

sealed interface ParanthesesType {
    object Open : ParanthesesType
    object Close : ParanthesesType
}
