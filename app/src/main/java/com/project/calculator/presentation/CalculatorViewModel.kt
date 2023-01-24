package com.project.calculator.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.project.calculator.domain.CalculatorAction
import com.project.calculator.domain.ExpressionWriter

class CalculatorViewModel(
    private val expressionWriter: ExpressionWriter = ExpressionWriter()
) : ViewModel() {

    var expression by mutableStateOf("")
        private set

    fun onAction(action: CalculatorAction) {
        expressionWriter.handleAction(action)
        this.expression = expressionWriter.expression
    }
}