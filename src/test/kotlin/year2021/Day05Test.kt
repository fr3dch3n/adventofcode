package year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day05Test {
    private val day05: Day05 = Day05()
    private val testInputPath = "src/test/resources/day05.txt"
    private val testSample = listOf(
        Day05.Line(Pair(0, 9), Pair(5, 9)),
        Day05.Line(Pair(8, 0), Pair(0, 8)),
        Day05.Line(Pair(9, 4), Pair(3, 4)),
        Day05.Line(Pair(2, 2), Pair(2, 1)),
        Day05.Line(Pair(7, 0), Pair(7, 4)),
        Day05.Line(Pair(6, 4), Pair(2, 0)),
        Day05.Line(Pair(0, 9), Pair(2, 9)),
        Day05.Line(Pair(3, 4), Pair(1, 4)),
        Day05.Line(Pair(0, 0), Pair(8, 8)),
        Day05.Line(Pair(5, 5), Pair(8, 2)),
    )

    @Test
    fun readInput() {
        val l = day05.readInput(testInputPath)
        assertEquals(Pair(348, 742), l[0].startCoordinate)
        assertEquals(Pair(620, 742), l[0].endCoordinate)
    }

    @Test
    fun calculateListOfPoints() {
        val l = Day05.Line(Pair(1, 1), Pair(1, 3))
        assertEquals(listOf(Pair(1, 1), Pair(1, 2), Pair(1, 3)), l.calculateListOfPoints())
        val l2 = Day05.Line(Pair(9, 7), Pair(7, 7))
        assertEquals(listOf(Pair(9, 7), Pair(8, 7), Pair(7, 7)), l2.calculateListOfPoints())
        val l4 = Day05.Line(Pair(1, 1), Pair(3, 3))
        assertEquals(listOf(Pair(1, 1), Pair(2, 2), Pair(3, 3)), l4.calculateListOfPoints())
        val l3 = Day05.Line(Pair(9, 7), Pair(7, 9))
        assertEquals(listOf(Pair(9, 7), Pair(8, 8), Pair(7, 9)), l3.calculateListOfPoints())
    }

    @Test
    fun calculateOverlap() {
        assertEquals(5, day05.calculateOverlap(testSample))
    }

    @Test
    fun solutionA() {
        assertEquals(4826, day05.solutionA(testInputPath))
    }

    @Test
    fun solutionB() {
        assertEquals(16793, day05.solutionB(testInputPath))
    }
}