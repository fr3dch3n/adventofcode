package year2021

import java.io.File

class Day05 {
    class Line(start: Pair<Int, Int>, end: Pair<Int, Int>) {
        val startCoordinate = start
        val endCoordinate = end

        fun calculateListOfPoints(): List<Pair<Int, Int>> {
            fun recursiveStuff(
                listOfCoordinates: List<Pair<Int, Int>>,
                current: Pair<Int, Int>,
                end: Pair<Int, Int>
            ): List<Pair<Int, Int>> {
                if (current == end) {
                    return listOfCoordinates
                }

                val nextCoordinate: Pair<Int, Int>
                if (current.first != end.first && current.second != end.second) {
                    nextCoordinate = if (current.first < end.first && current.second < end.second) {
                        Pair(current.first + 1, current.second + 1)
                    } else if (current.first < end.first && current.second > end.second) {
                        Pair(current.first + 1, current.second - 1)
                    } else if (current.first > end.first && current.second < end.second) {
                        Pair(current.first - 1, current.second + 1)
                    } else {
                        Pair(current.first - 1, current.second - 1)
                    }
                } else {
                    nextCoordinate = if (current.first < end.first) {
                        Pair(current.first + 1, current.second)
                    } else if (current.first > end.first) {
                        Pair(current.first - 1, current.second)
                    } else if (current.second < end.second) {
                        Pair(current.first, current.second + 1)
                    } else {
                        Pair(current.first, current.second - 1)
                    }
                }

                return recursiveStuff(listOfCoordinates + nextCoordinate, nextCoordinate, end)
            }
            return recursiveStuff(listOf(startCoordinate), startCoordinate, endCoordinate)
        }
    }

    fun readInput(path: String): List<Line> {
        val lines = File(path).readLines()
        return lines.map { l ->
            val match = Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)").find(l)!!
            val (x1, y1, x2, y2) = match.destructured
            Line(Pair(x1.toInt(), y1.toInt()), Pair(x2.toInt(), y2.toInt()))
        }
    }

    fun calculateOverlap(input: List<Line>, keepDiagonals: Boolean = false): Int {
        val relevantLines = if (keepDiagonals) {
            input
        } else {
            input.filter { l -> (l.startCoordinate.first == l.endCoordinate.first || l.startCoordinate.second == l.endCoordinate.second) }
        }
        val allCoordinates = relevantLines.flatMap { l -> l.calculateListOfPoints() }
        val filter = allCoordinates.groupingBy { it }.eachCount().filter { it.value > 1 }
        return filter.count()
    }

    fun solutionA(path: String): Int {
        val input = readInput(path)
        return calculateOverlap(input)
    }

    fun solutionB(path: String): Int {
        val input = readInput(path)
        return calculateOverlap(input, true)
    }
}
