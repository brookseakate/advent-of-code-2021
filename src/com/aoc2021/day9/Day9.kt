package com.aoc2021.day9

import com.aoc2021.util.Utils

class Day9 {
  companion object {
    private lateinit var caveFloor: List<List<Int>>

    fun main() {
//      val input = Utils.readFileAsMutableList("day9/Input")
      val input = Utils.readFileAsMutableList("day9/ExampleInput")

      caveFloor = readInputToCaveFloor(input)
      println(partOne())
    }

    private fun partOne(): Int {
      val lowPoints = mutableListOf<Int>()

      for ((rowIndex, row) in caveFloor.withIndex()) {
        for ((pointIndex, point) in row.withIndex()) {
          var isLowPoint = false

          if (pointIndex > 0) {
            isLowPoint = row[pointIndex - 1] > point
          }

          if (pointIndex < row.lastIndex) {
            isLowPoint = row[pointIndex + 1] > point
          }

          if (rowIndex > 0) {
            isLowPoint = caveFloor[rowIndex - 1][pointIndex] > point
          }

          if (rowIndex < caveFloor.lastIndex) {
            isLowPoint = caveFloor[rowIndex + 1][pointIndex] > point
          }

          if (isLowPoint) {
            lowPoints += point
          }
        }

      }
      println("count: ${lowPoints.size}")
      println("points: $lowPoints")
      return lowPoints.sum() + lowPoints.size
    }

    private fun readInputToCaveFloor(
      input: MutableList<String>
    ): List<List<Int>> {
      return input
        .map { string ->
          string
            .split("")
            .filter { it.isNotEmpty() }
            .map { it.toInt() }
        }
    }
  }
}