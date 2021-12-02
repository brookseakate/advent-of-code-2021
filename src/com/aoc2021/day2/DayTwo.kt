package com.aoc2021.day2

import java.nio.file.Files
import java.nio.file.Paths

class DayTwo {
  companion object {
    fun main() {
      println(partOne())
    }

    private fun partOne(): Int {
      var depth = 0
      var horizontalPosition = 0

      val inputLines: MutableList<String> = Files.readAllLines(Paths.get("./src/com/aoc2021/day2/Input"))

      for (line: String in inputLines) {
        val instruction = line.split(" ")
        val direction = instruction[0]
        val distance = instruction[1].toInt()

        when (direction) {
          "forward" -> horizontalPosition += distance
          "down" -> depth += distance
          "up" -> depth -= distance
        }
      }

      return depth * horizontalPosition
    }
  }
}