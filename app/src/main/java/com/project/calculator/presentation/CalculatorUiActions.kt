package com.project.calculator.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import com.project.calculator.domain.CalculatorAction
import com.project.calculator.domain.Operation

val calculatorActions = listOf(
    CalculatorState(
        text = "AC",
        highlightLevel = HighlightLevel.Highlighted,
        action = CalculatorAction.Clear
    ),
    CalculatorState(
        text = "()",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.Parantheses
    ),
    CalculatorState(
        text = "%",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.PERCENT)
    ),
    CalculatorState(
        text = "÷",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.DIVIDE)
    ),
    CalculatorState(
        text = "7",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(7)
    ),
    CalculatorState(
        text = "8",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(8)
    ),
    CalculatorState(
        text = "9",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(9)
    ),
    CalculatorState(
        text = "x",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.MULTIPLY)
    ),
    CalculatorState(
        text = "4",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(4)
    ),
    CalculatorState(
        text = "5",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(5)
    ),
    CalculatorState(
        text = "6",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(6)
    ),
    CalculatorState(
        text = "-",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.SUBTRACT)
    ),
    CalculatorState(
        text = "1",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(1)
    ),
    CalculatorState(
        text = "2",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(2)
    ),
    CalculatorState(
        text = "3",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(3)
    ),
    CalculatorState(
        text = "+",
        highlightLevel = HighlightLevel.SemiHighlighted,
        action = CalculatorAction.EnterOperation(Operation.ADD)
    ),
    CalculatorState(
        text = "0",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterNumber(0)
    ),
    CalculatorState(
        text = ".",
        highlightLevel = HighlightLevel.Neutral,
        action = CalculatorAction.EnterDecimal
    ),
    CalculatorState(
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
    CalculatorState(
        text = "=",
        highlightLevel = HighlightLevel.StronglyHighlighted,
        action = CalculatorAction.Calculate
    ),
)