package year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day02Test {
    private val day02: Day02 = Day02()
    private val testInputPath = "src/test/resources/day02.txt"
    private val testSample = listOf(
        Pair("forward", 5), Pair("down", 5), Pair("forward", 8), Pair("up", 3), Pair("down", 8), Pair("forward", 2)
    )

    @Test
    fun readInput() {
        val lines = day02.readInput(testInputPath)
        assertEquals(Pair("forward", 5), lines[0])
    }

    @Test
    fun moveSubmarine() {
        assertEquals(150, day02.moveSubmarine(testSample))
    }

    @Test
    fun moveSubmarineComplicated() {
        assertEquals(900, day02.moveSubmarineComplicated(testSample))
    }

    @Test
    fun solution1a() {
        assertEquals(1636725, day02.solution1a(testInputPath))
    }

    @Test
    fun solution1b() {
        assertEquals(1872757425, day02.solution1b(testInputPath))
    }
}