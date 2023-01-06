package com.project.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ExpressionParserTest {

    private lateinit var parser: ExpressionParser

    @Test
    fun `parse expression`() {
        // GIVEN
        parser = ExpressionParser("1*2/3+4-5%67")

        // APPLY LOGIC
        val actual = parser.parse()

        // ASSERT EXPECTED RESULT
        val expected = listOf(
            ExpressionPart.Number(1.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.PERCENT),
            ExpressionPart.Number(67.0)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `parse expression with parenthesis`() {
        // GIVEN
        parser = ExpressionParser("1*(2/3)+4-(5%67)")

        // APPLY LOGIC
        val actual = parser.parse()

        // ASSERT EXPECTED RESULT
        val expected = listOf(
            ExpressionPart.Number(1.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.PERCENT),
            ExpressionPart.Number(67.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `parse expression with parenthesis and spaces`() {
        // GIVEN
        parser = ExpressionParser("1 * ( 2 / 3 ) + 4 - ( 5 % 67 )")

        // APPLY LOGIC
        val actual = parser.parse()

        // ASSERT EXPECTED RESULT
        val expected = listOf(
            ExpressionPart.Number(1.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.PERCENT),
            ExpressionPart.Number(67.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `parse expression with parenthesis and spaces and negative numbers`() {
        // GIVEN
        parser = ExpressionParser("1 * ( 2 / 3 ) + 4 - ( 5 % - 67 )")

        // APPLY LOGIC
        val actual = parser.parse()

        // ASSERT EXPECTED RESULT
        val expected = listOf(
            ExpressionPart.Number(1.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.PERCENT),
            ExpressionPart.Number(-67.0),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        )
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `parse expression with decimal numbers`() {
        // GIVEN
        parser = ExpressionParser("1.1 * ( 2.2 / 3.3 ) + 4.4 - ( 5.5 % 6.6 )")

        // APPLY LOGIC
        val actual = parser.parse()

        // ASSERT EXPECTED RESULT
        val expected = listOf(
            ExpressionPart.Number(1.1),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.2),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.3),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.4),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.PERCENT),
            ExpressionPart.Number(6.6),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        )
        assertThat(expected).isEqualTo(actual)
    }

}