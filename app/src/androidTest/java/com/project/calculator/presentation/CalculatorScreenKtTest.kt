package com.project.calculator.presentation


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.project.calculator.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CalculatorScreenKtTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)
    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()
    }

    @Test
    fun enterExpression_correctResultDisplayed() {
        composeTestRule.onNodeWithText("1").performClick()
        composeTestRule.onNodeWithText("+").performClick()
        composeTestRule.onNodeWithText("2").performClick()
        composeTestRule.onNodeWithText("x").performClick()
        composeTestRule.onNodeWithText("3").performClick()
        composeTestRule.onNodeWithText("-").performClick()
        composeTestRule.onNodeWithText("5").performClick()
        composeTestRule.onNodeWithText("=").performClick()

        composeTestRule.onNodeWithText("2.0").assertIsDisplayed()
    }
}