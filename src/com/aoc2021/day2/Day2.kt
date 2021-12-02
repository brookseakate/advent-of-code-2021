package com.aoc2021.day2

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

class Day2 {
  companion object {
    fun main() {
      println(part2())
    }

    private fun part2(): Int {
      var aim = 0
      var depth = 0
      var horizontalPosition = 0

      val inputLines = readFileAsMutableList("day2/Input")

      for (line: String in inputLines) {
        val instruction = line.split(" ")
        val direction = instruction[0]
        val distance = instruction[1].toInt()

        when (direction) {
          "forward" -> {
            horizontalPosition += distance
            depth += aim * distance
          }
          "down" -> aim += distance
          "up" -> aim -= distance
        }
      }

      return depth * horizontalPosition
    }

    private fun part1(): Int {
      var depth = 0
      var horizontalPosition = 0

      val inputLines: MutableList<String> = readFileAsMutableList("day2/Input")

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