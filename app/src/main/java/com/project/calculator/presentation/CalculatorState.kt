package com.project.calculator.presentation

import androidx.compose.runtime.Composable
import com.project.calculator.domain.CalculatorAction

data class CalculatorState(
    val text: String?,
    val highlightLevel: HighlightLevel,
    val action: CalculatorAction,
    val content: @Composable () -> Unit = {}
)

sealed interface HighlightLevel {
    object Neutral : HighlightLevel
    object Highlighted : HighlightLevel
    object SemiHighlighted : HighlightLevel
    object StronglyHighlighted : HighlightLevel

}
