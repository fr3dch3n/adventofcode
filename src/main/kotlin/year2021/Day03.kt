package year2021

import java.io.File

class Day03 {

    fun readInput(path: String): List<List<Int>> {
        val lines = File(path).readLines()
        return lines.map { it -> it.toList().map { Character.getNumericValue(it) } }

    }

    fun transposeMatrix(input: List<List<Int>>): List<List<Int>> {
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

    fun binToInt(input: List<Int>): Int {
        return input
            .map { it.toString() }
            .toString()
            .replace("[", "")
            .replace("]", "")
            .replace(" ", "")
            .replace(",", "")
            .toInt(2)

    }

    fun calculatePowerConsumption(input: List<List<Int>>): Int {
        val matrix = transposeMatrix(input)
        val gamma = mutableListOf<Int>()
        val epsilon = mutableListOf<Int>()
        for (line in matrix) {
            val zeros = line.count { it == 0 }
            val ones = line.count { it == 1 }
            if (zeros > ones) {
                gamma.add(gamma.size, 0)
                epsilon.add(epsilon.size, 1)
            } else {
                gamma.add(gamma.size, 1)
                epsilon.add(epsilon.size, 0)
            }
        }

        return binToInt(gamma) * binToInt(epsilon)
    }

    fun filterByRule(position: Int, input: List<List<Int>>, mostCommon: Boolean, keep: Int): Int {
        if (input.size == 1) {
            return binToInt(input[0])
        }
        val numbers = input.map { it[position] }
        val zeros = numbers.count { it == 0 }
        val ones = numbers.count { it == 1 }
        if (mostCommon) {
            return if (ones > zeros) {
                filterByRule(position + 1, input.filter { it[position] == 1 }, mostCommon, keep)
            } else if (zeros > ones) {
                filterByRule(position + 1, input.filter { it[position] == 0 }, mostCommon, keep)
            } else {
                filterByRule(position + 1, input.filter { it[position] == keep }, mostCommon, keep)
            }
        } else {
            return if (zeros > ones) {
                filterByRule(position + 1, input.filter { it[position] == 1 }, mostCommon, keep)
            } else if (ones > zeros) {
                filterByRule(position + 1, input.filter { it[position] == 0 }, mostCommon, keep)
            } else {
                filterByRule(position + 1, input.filter { it[position] == keep }, mostCommon, keep)
            }

        }
    }

    fun calculateLifeSupportRating(input: List<List<Int>>): Int {
        val oxygenGeneratorRating = filterByRule(0, input, true, 1)
        val co2ScrubberRating = filterByRule(0, input, false, 0)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    fun solution1a(path: String): Int {
        val binaries = readInput(path)
        return calculatePowerConsumption(binaries)
    }

    fun solution1b(path: String): Int {
        val binaries = readInput(path)
        return calculateLifeSupportRating(binaries)
    }
}