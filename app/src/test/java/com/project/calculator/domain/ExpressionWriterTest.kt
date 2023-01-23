package com.project.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test


class ExpressionWriterTest {

    private lateinit var expressionWriter: ExpressionWriter

    @Before
    fun setUp() {
        expressionWriter = ExpressionWriter()
    }

    @Test
    fun `Initial parantheses parsed`() {
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.EnterOperation(Operation.ADD))
        expressionWriter.handleAction(CalculatorAction.EnterNumber(2))
        expressionWriter.handleAction(CalculatorAction.Parantheses)

        assertThat(expressionWriter.expression).isEqualTo("(1+2)")
    }

    @Test
    fun `Closing parantheses at the start not parsed`() {
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.Parantheses)

        assertThat(expressionWriter.expression).isEqualTo("((")
    }

    @Test
    fun `Parantheses around a number are parsed`() {
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.Parantheses)

        assertThat(expressionWriter.expression).isEqualTo("(1)")
    }

    @Test
    fun `Closing parantheses without opening not parsed`() {
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.Parantheses)

        assertThat(expressionWriter.expression).isEqualTo("1")
    }

    @Test
    fun `Nested parantheses parsed`() {
        //1+2*((1+1)*2)
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.EnterOperation(Operation.ADD))
        expressionWriter.handleAction(CalculatorAction.EnterNumber(2))
        expressionWriter.handleAction(CalculatorAction.EnterOperation(Operation.MULTIPLY))
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.EnterOperation(Operation.ADD))
        expressionWriter.handleAction(CalculatorAction.EnterNumber(1))
        expressionWriter.handleAction(CalculatorAction.Parantheses)
        expressionWriter.handleAction(CalculatorAction.EnterOperation(Operation.MULTIPLY))
        expressionWriter.handleAction(CalculatorAction.EnterNumber(2))
        expressionWriter.handleAction(CalculatorAction.Parantheses)

        assertThat(expressionWriter.expression).isEqualTo("1+2*((1+1)*2)")

    }

}