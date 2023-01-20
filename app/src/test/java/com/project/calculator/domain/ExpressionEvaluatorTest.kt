package com.project.calculator.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class ExpressionEvaluatorTest {

    private lateinit var evaluator: ExpressionEvaluator

    @Test
    fun `Simple expression evaluated`() {
        // GIVEN
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Number(1.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(2.0),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.0),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.0),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(5.0),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(67.0)
        ))

        // APPLY LOGIC
        val actual = evaluator.evaluate()

        // ASSERT EXPECTED RESULT
        val expected = -330.3333333333333
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with decimals evaluated`() {
        // GIVEN 3.4 * 2.2 / 3.9 + 4.2 - 5.4 * 67.9
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Number(4.5),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Number(3.5),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(5.5),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.5),
        ))

        // APPLY LOGIC
        val actual = evaluator.evaluate()

        // ASSERT EXPECTED RESULT
        val expected = 4.5
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parenthesis evaluated`() {
        // GIVEN 3.4 * 2.2 / 3.9 + 4.2 - 5.4 * 67.9
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Number(3.4),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.2),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.9),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.2),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.4),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(67.9),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        ))

        // APPLY LOGIC
        val actual = evaluator.evaluate()

        // ASSERT EXPECTED RESULT
        val expected = 3.4 * 2.2 / 3.9 + 4.2 - 5.4 * 67.9
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Expression with parenthesis and decimals evaluated`() {
        // GIVEN 3.4 * 2.2 / 3.9 + 4.2 - 5.4 * 67.9
        evaluator = ExpressionEvaluator(listOf(
            ExpressionPart.Number(3.4),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(2.2),
            ExpressionPart.Op(Operation.DIVIDE),
            ExpressionPart.Number(3.9),
            ExpressionPart.Parenthesis(ParanthesesType.Close),
            ExpressionPart.Op(Operation.ADD),
            ExpressionPart.Number(4.2),
            ExpressionPart.Op(Operation.SUBTRACT),
            ExpressionPart.Parenthesis(ParanthesesType.Open),
            ExpressionPart.Number(5.4),
            ExpressionPart.Op(Operation.MULTIPLY),
            ExpressionPart.Number(67.9),
            ExpressionPart.Parenthesis(ParanthesesType.Close)
        ))

        // APPLY LOGIC
        val actual = evaluator.evaluate()

        // ASSERT EXPECTED RESULT
        val expected = 3.4 * 2.2 / 3.9 + 4.2 - 5.4 * 67.9
        assertThat(expected).isEqualTo(actual)
    }

    @Test
    fun `Simple equation with parentheses properly evaluated`() {
        evaluator = ExpressionEvaluator(
            listOf(
                ExpressionPart.Number(4.0),
                ExpressionPart.Op(Operation.ADD),
                ExpressionPart.Parenthesis(ParanthesesType.Open),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.SUBTRACT),
                ExpressionPart.Number(3.0),
                ExpressionPart.Parenthesis(ParanthesesType.Close),
                ExpressionPart.Op(Operation.MULTIPLY),
                ExpressionPart.Number(5.0),
                ExpressionPart.Op(Operation.DIVIDE),
                ExpressionPart.Number(4.0),
            )
        )

        assertThat(evaluator.evaluate()).isEqualTo(6.5)
    }
}