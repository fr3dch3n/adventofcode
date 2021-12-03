package year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day03Test {
    private val day03: Day03 = Day03()
    private val testInputPath = "src/test/resources/day03.txt"
    private val testSample = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010"
    )

    @Test
    fun readInput() {
        val lines = day03.readInput(testInputPath)
        assertEquals(listOf(0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0), lines[0])
    }

    @Test
    fun binToInt() {
        assertEquals(9, day03.binToInt(listOf(0, 1, 0, 0, 1)))
    }

    @Test
    fun calculatePowerConsumption() {
        val input = testSample.map { it -> it.toList().map { Character.getNumericValue(it) } }
        val powerConsumption = day03.calculatePowerConsumption(input)
        assertEquals(198, powerConsumption)
    }

    @Test
    fun transposeMatrix() {
        val matrix = day03.transposeMatrix(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
        val expected = listOf(listOf(1, 4), listOf(2, 5), listOf(3, 6))
        assertEquals(expected, matrix)
    }

    @Test
    fun filterByRule() {
        val input = testSample.map { it -> it.toList().map { Character.getNumericValue(it) } }
        val result = day03.filterByRule(0, input, true, 1)
        assertEquals(23, result)
        val result2 = day03.filterByRule(0, input, false, 0)
        assertEquals(10, result2)
    }

    @Test
    fun calculateLifeSupportRating() {
        val input = testSample.map { it -> it.toList().map { Character.getNumericValue(it) } }
        val lifeSupportRating = day03.calculateLifeSupportRating(input)
        assertEquals(230, lifeSupportRating)
    }

    @Test
    fun solution1a() {
        assertEquals(4191876, day03.solution1a(testInputPath))
    }

    @Test
    fun solution1b() {
        assertEquals(3414905, day03.solution1b(testInputPath))
    }
}