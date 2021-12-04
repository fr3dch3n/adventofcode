package year2021

import java.io.File

class Day04 {
    class BingoBoard(input: List<String>) {
        var matrix = parseInput(input)

        private val calledNumbers = mutableListOf<Int>()

        private fun parseInput(input: List<String>): List<List<Int>> {
            return input.map { it.split(" ").filter { x -> x != "" }.map { x -> x.toInt() } }
        }

        private fun transposeMatrix(input: List<List<Int>>): List<List<Int>> {
            val m: Int = input.size
            val n: Int = input[0].size

            val transposedMatrix = Array(n) { IntArray(m) }

            for (x in 0 until n) {
                for (y in 0 until m) {
                    transposedMatrix[x][y] = input[y][x]
                }
            }
            return transposedMatrix.map { it.toList() }.toList()
        }

        fun callNumber(n: Int) {
            calledNumbers.add(calledNumbers.size, n)
        }

        fun callNumber(n: List<Int>) {
            calledNumbers.addAll(calledNumbers.size, n)
        }

        fun isWinner(): Pair<Boolean, Int> {
            val transposedMatrix = transposeMatrix(matrix)

            val pairedMatrix = matrix.map { r -> r.map { c -> Pair(c, false) } }
            val transposeddPairedMatrix = transposedMatrix.map { r -> r.map { c -> Pair(c, false) } }

            val markedMatrix: List<List<Pair<Int, Boolean>>> = pairedMatrix.map { row ->
                row.map { c ->
                    if (calledNumbers.contains(c.first)) {
                        Pair(c.first, true)
                    } else {
                        c
                    }

                }
            }
            val transposedMarkedMatrix: List<List<Pair<Int, Boolean>>> = transposeddPairedMatrix.map { row ->
                row.map { c ->
                    if (calledNumbers.contains(c.first)) {
                        Pair(c.first, true)
                    } else {
                        c
                    }
                }
            }

            if (hasCheckedRow(markedMatrix) || hasCheckedRow(transposedMarkedMatrix)) {
                val sumUnchecked = markedMatrix.sumOf { it.filter { x -> !x.second }.sumOf { x -> x.first } }
                return Pair(true, sumUnchecked * calledNumbers.last())

            }

            return Pair(false, -1)
        }

        private fun hasCheckedRow(matrix: List<List<Pair<Int, Boolean>>>): Boolean {
            for (line in matrix) {
                val rest = line.filter { !it.second }
                if (rest.isEmpty()) {
                    return true
                }
            }
            return false
        }
    }

    fun parseInput(input: List<String>): Pair<List<Int>, List<BingoBoard>> {
        val scores = input[0].split(",").map { it.toInt() }
        val boards = input.drop(1).filter { it != "" }.windowed(5, 5).map { BingoBoard(it) }
        return Pair(scores, boards)
    }

    fun readInput(path: String): Pair<List<Int>, List<BingoBoard>> {
        val lines = File(path).readLines()
        return parseInput(lines)

    }

    fun play(numbersToCall: List<Int>, boards: List<BingoBoard>): Int {
        for (n in numbersToCall) {
            for (b in boards) {
                b.callNumber(n)
                val maybeWinner = b.isWinner()
                if (maybeWinner.first) {
                    return maybeWinner.second
                }
            }
        }
        return -1
    }

    fun playWinLast(numbersToCall: List<Int>, boards: List<BingoBoard>): Int {
        for (n in numbersToCall) {
            for (b in boards) {
                b.callNumber(n)
                val maybeWinner = b.isWinner()
                if (maybeWinner.first && boards.none { !it.isWinner().first }) {
                    return maybeWinner.second
                }
            }
        }
        return -1
    }

    fun solutionA(path: String): Int {
        val input = readInput(path)
        return play(input.first, input.second)
    }

    fun solutionB(path: String): Int {
        val input = readInput(path)
        return playWinLast(input.first, input.second)
    }
}
