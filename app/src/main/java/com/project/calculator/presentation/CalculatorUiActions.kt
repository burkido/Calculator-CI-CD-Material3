package com.project.calculator.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.project.calculator.domain.CalculatorAction
import com.project.calculator.domain.Operation

val calculatorActions = listOf(
    CalculatorUiAction(
        text = "AC",
        highlightLevel = HighlightLevel.Highlighted,
        action = CalculatorAction.Clear
    ),
    CalculatorUiAction(
        text = "()",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.Parantheses
    ),
    CalculatorUiAction(
        text = "%",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.PERCENT)
    ),
    CalculatorUiAction(
        text = "÷",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.DIVIDE)
    ),
    CalculatorUiAction(
        text = "7",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(7)
    ),
    CalculatorUiAction(
        text = "8",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(8)
    ),
    CalculatorUiAction(
        text = "9",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(9)
    ),
    CalculatorUiAction(
        text = "x",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.MULTIPLY)
    ),
    CalculatorUiAction(
        text = "4",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(4)
    ),
    CalculatorUiAction(
        text = "5",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(5)
    ),
    CalculatorUiAction(
        text = "6",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(6)
    ),
    CalculatorUiAction(
        text = "-",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.SUBTRACT)
    ),
    CalculatorUiAction(
        text = "1",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(1)
    ),
    CalculatorUiAction(
        text = "2",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(2)
    ),
    CalculatorUiAction(
        text = "3",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(3)
    ),
    CalculatorUiAction(
        text = "+",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.ADD)
    ),
    CalculatorUiAction(
        text = "0",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(0)
    ),
    CalculatorUiAction(
        text = ".",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterDecimal
    ),
    CalculatorUiAction(
        text = null,
        content = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.Delete
    ),
    CalculatorUiAction(
        text = "=",
        highlightLevel = HighlightLevel.StronglyHighlighted,
        action = CalculatorAction.Calculate
    ),
)