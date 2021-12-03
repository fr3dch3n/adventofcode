package year2021

import java.io.File

class Day01 {

    fun readInput(path: String): List<Int> {
        val lines = File(path).readLines()
        return lines.map { it.toInt() }
    }

    fun countIncreasingSteps(current: Int, rest: List<Int>, sum: Int): Int {
        if (rest.isEmpty()) {
            return sum
        }
        return if (rest[0] > current) {
            countIncreasingSteps(rest[0], rest.drop(1), sum + 1)
        } else {
            countIncreasingSteps(rest[0], rest.drop(1), sum)
        }
    }

    fun countIncreasingWindows(input: List<Int>): Int {
        val windows = input.windowed(3, 1)
        val windowSums = windows.map { it.sum() }
        return countIncreasingSteps(windowSums[0], windowSums.drop(1), 0)
    }

    fun solutionA(path: String): Int {
        val ints = readInput(path)
        return countIncreasingSteps(ints[0], ints.drop(1), 0)
    }

    fun solutionB(path: String): Int {
        val ints = readInput(path)
        return countIncreasingWindows(ints)
    }
}