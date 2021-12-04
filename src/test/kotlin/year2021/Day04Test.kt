package year2021

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day04Test {
    private val day04: Day04 = Day04()
    private val testInputPath = "src/test/resources/day04.txt"
    private val testSample = listOf(
        "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
        "",
        "22 13 17 11  0",
        " 8  2 23  4 24",
        "21  9 14 16  7",
        " 6 10  3 18  5",
        " 1 12 20 15 19",
        "",
        " 3 15  0  2 22",
        " 9 18 13 17  5",
        "19  8  7 25 23",
        "20 11 10 24  4",
        "14 21 16 12  6",
        "",
        "14 21 17 24  4",
        "10 16 15  9 19",
        "18  8 23 26 20",
        "22 11 13  6  5",
        " 2  0 12  3  7"
    )

    @Test
    fun readInput() {
        val (scores, boards) = day04.readInput(testInputPath)
        assertEquals(listOf(76, 69, 38), scores.subList(0, 3))
        val firstBoards = boards[0]

        assertEquals(5, firstBoards.matrix.size)
        assertEquals(5, firstBoards.matrix[0].size)
        assertEquals(17, firstBoards.matrix[0][0])
        assertEquals(39, firstBoards.matrix[1][0])
    }

    @Test
    fun isWinner() {
        val parsed: Pair<List<Int>, List<Day04.BingoBoard>> = day04.parseInput(testSample)
        val board = parsed.second.last()

        board.callNumber(parsed.first.take(11))
        assertEquals(Pair(false, -1), board.isWinner())
        board.callNumber(parsed.first[11])
        assertEquals(Pair(true, 4512), board.isWinner())
    }

    @Test
    fun play() {
        val parsed: Pair<List<Int>, List<Day04.BingoBoard>> = day04.parseInput(testSample)
        assertEquals(4512, day04.play(parsed.first, parsed.second))
    }

    @Test
    fun playWinLast() {
        val parsed: Pair<List<Int>, List<Day04.BingoBoard>> = day04.parseInput(testSample)
        assertEquals(1924, day04.playWinLast(parsed.first, parsed.second))
    }

    @Test
    fun solutionA() {
        assertEquals(2496, day04.solutionA(testInputPath))
    }

    @Test
    fun solutionB() {
        assertEquals(25925, day04.solutionB(testInputPath))
    }
}