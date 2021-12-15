package year2021

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class Day09Test {
    private val day09: Day09 = Day09()
    private val testInputPath = "src/test/resources/day09.txt"
    private val testSample: List<String> = listOf(
        "2199943210",
        "3987894921",
        "9856789892",
        "8767896789",
        "9899965678"
    )


    @Test
    fun shouldConvertLinesToMatrix() {
        testSample.toMatrix()
        assertEquals(6, testSample.toMatrix()[2][3])
    }

    @Test
    fun shouldIdentifyLowest() {
        val matrix = testSample.toMatrix()
        assertTrue(isLowest(matrix, 0, 1))
        assertFalse(isLowest(matrix, 3, 3))
    }

    @Test
    fun shouldReturnOnlyLowest() {
        val matrix = testSample.toMatrix()
        assertEquals(listOf(1, 0, 5, 5), matrix.onlyLowest())
    }

    @Test
    fun shouldCalculateRiskLevelSum() {
        val matrix = testSample.toMatrix()
        assertEquals(15, matrix.riskLevelSum())
    }



    @Test
    fun solutionA() {
        assertEquals(566, day09.solutionA(testInputPath))
    }
//
//    @Test
//    fun solutionB() {
//        assertEquals(16793, day09.solutionB(testInputPath))
//    }
}