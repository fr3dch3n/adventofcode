package year2021

import java.io.File

class Day02 {

    fun readInput(path: String): List<Pair<String, Int>> {
        val lines = File(path).readLines()
        return lines.map {
            val parts = it.split(" ")
            Pair(parts[0], parts[1].toInt())
        }
    }

    fun moveSubmarine(instructions: List<Pair<String, Int>>): Int {
        var horizontalStart = 0
        var depthStart = 0
        for (instruction in instructions) {
            when (instruction.first) {
                "forward" -> horizontalStart += instruction.second
                "down" -> depthStart += instruction.second
                "up" -> depthStart -= instruction.second
            }
        }
        return horizontalStart * depthStart
    }

    fun moveSubmarineComplicated(instructions: List<Pair<String, Int>>): Int {
        var horizontalStart = 0
        var depthStart = 0
        var aimStart = 0
        for (instruction in instructions) {
            when (instruction.first) {
                "down" -> aimStart += instruction.second
                "up" -> aimStart -= instruction.second
                "forward" -> {
                    horizontalStart += instruction.second
                    depthStart += aimStart * instruction.second
                }
            }
        }
        return horizontalStart * depthStart
    }

    fun solutionA(path: String): Int {
        val instructions = readInput(path)
        return moveSubmarine(instructions)
    }

    fun solutionB(path: String): Int {
        val instructions = readInput(path)
        return moveSubmarineComplicated(instructions)
    }
}