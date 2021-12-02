package year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day01Test {
    private val day01: Day01 = Day01()
    private val testInputPath = "src/test/resources/day01.txt"
    private val testSample = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)

    @Test
    fun readInput() {
        val lines = day01.readInput(testInputPath)
        assertEquals(143, lines[0])
    }

    @Test
    fun countIncreasingSteps() {
        assertEquals(7, day01.countIncreasingSteps(testSample[0], testSample.drop(1), 0))
    }

    @Test
    fun countIncreasingWindows() {
        assertEquals(5, day01.countIncreasingWindows(testSample))
    }

    @Test
    fun solution1a() {
        assertEquals(1532, day01.solution1a(testInputPath))
    }

    @Test
    fun solution1b() {
        assertEquals(1571, day01.solution1b(testInputPath))
    }
}