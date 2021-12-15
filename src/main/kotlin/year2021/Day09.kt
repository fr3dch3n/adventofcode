package year2021

import java.io.File

typealias Lines = List<String>
typealias Matrix = List<List<Int>>
typealias Coordinate = Pair<Int, Int>

fun Lines.toMatrix(): Matrix {
    return this.map { it.toList().map { c -> c.digitToInt() } }
}

fun isLowest(matrix: Matrix, x: Int, y: Int): Boolean {
    fun Coordinate.isLower(current: Int): Boolean {
        val value = matrix
            .getOrElse(this.first) { emptyList() }
            .getOrElse(this.second) { 99 }
        return value <= current
    }

    val current = matrix[x][y]

    val adjacentCoordinates = listOf(Pair(x, y - 1), Pair(x, y + 1), Pair(x - 1, y), Pair(x + 1, y))
    return !adjacentCoordinates.any { it.isLower(current) }
}

fun Matrix.onlyLowest(): List<Int> {

    val agg = mutableListOf<Int>()
    for (x in this.indices) {
        for (y in this[x].indices) {
            if (isLowest(this, x, y)) {
                agg.add(this[x][y])
            }
        }
    }
    return agg
}

fun Matrix.riskLevelSum(): Int {
    return this.onlyLowest().sumOf { it + 1 }
}

class Day09 {

    fun readInput(path: String): List<String> {
        return File(path).readLines()
    }

    fun solutionA(path: String) =
        readInput(path)
            .toMatrix()
            .riskLevelSum()

//
//    fun solutionB(path: String): Int {
//        val input = readInput(path)
//        return calculateOverlap(input, true)
//    }
}

