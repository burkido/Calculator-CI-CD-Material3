package com.project.calculator.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.calculator.domain.CalculatorAction

@Composable
fun CalculatorButton(
    action: CalculatorUiAction,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(
                when (action.highlightLevel) {
                    HighlightLevel.Highlighted -> MaterialTheme.colorScheme.tertiary
                    HighlightLevel.Neutral -> MaterialTheme.colorScheme.surfaceVariant
                    HighlightLevel.SemiHighlighted -> MaterialTheme.colorScheme.inverseSurface
                    HighlightLevel.StronglyHighlighted -> MaterialTheme.colorScheme.primary
                }
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        if (action.text != null) {
            Text(
                text = action.text,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = when (action.highlightLevel) {
                    HighlightLevel.Highlighted -> MaterialTheme.colorScheme.onTertiary
                    HighlightLevel.Neutral -> MaterialTheme.colorScheme.onSurfaceVariant
                    HighlightLevel.SemiHighlighted -> MaterialTheme.colorScheme.inverseOnSurface
                    HighlightLevel.StronglyHighlighted -> MaterialTheme.colorScheme.onPrimary
                }
            )
        } else {
            action.content()
        }
    }
}

@Preview
@Composable
fun CalculatorButtonPreviewHiglighted() {
    CalculatorButton(
        action = CalculatorUiAction(
            text = "1",
            highlightLevel = HighlightLevel.Highlighted,
            action = CalculatorAction.EnterNumber(1)
        )
    )
}

@Preview
@Composable
fun CalculatorButtonPreviewNeutral() {
    CalculatorButton(
        action = CalculatorUiAction(
            text = "1",
            highlightLevel = HighlightLevel.Neutral,
            action = CalculatorAction.EnterNumber(1)
        )
    )
}

@Preview
@Composable
fun CalculatorButtonPreviewSemiHighlighted() {
    CalculatorButton(
        action = CalculatorUiAction(
            text = "1",
            highlightLevel = HighlightLevel.SemiHighlighted,
            action = CalculatorAction.EnterNumber(1)
        )
    )
}

@Preview
@Composable
fun CalculatorButtonPreviewStronglyHighlighted() {
    CalculatorButton(
        action = CalculatorUiAction(
            text = "1",
            highlightLevel = HighlightLevel.StronglyHighlighted,
            action = CalculatorAction.EnterNumber(1)
        )
    )
}

@Preview
@Composable
fun CalculatorButtonPreviewContent() {
    CalculatorButton(
        action = CalculatorUiAction(
            text = null,
            highlightLevel = HighlightLevel.Neutral,
            action = CalculatorAction.EnterNumber(1),
            content = {
                Text(
                    text = "1",
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        )
    )
}