package com.aoc2021.day9

import com.aoc2021.util.Utils

class Day9 {
  companion object {
    private lateinit var caveFloor: List<List<Int>>

    fun main() {
      val input = Utils.readFileAsMutableList("day9/Input")
//      val input = Utils.readFileAsMutableList("day9/ExampleInput")

      caveFloor = readInputToCaveFloor(input)
      println(partOne())
    }

    private fun partOne(): Int {
      val lowPoints = mutableListOf<Int>()

      for ((rowIndex, row) in caveFloor.withIndex()) {
        for ((pointIndex, point) in row.withIndex()) {
          // assume this is a low point – if proven otherwise, move on to next point

          if (pointIndex > 0) {
            // if left is smaller than point, false
            if (row[pointIndex - 1] <= point) {
              continue
            }
          }

          if (pointIndex < row.lastIndex) {
            // if right is smaller than point, false
            if (row[pointIndex + 1] <= point) {
              continue
            }
          }

          if (rowIndex > 0) {
            // if above is smaller than point, false
            if (caveFloor[rowIndex - 1][pointIndex] <= point) {
              continue
            }
          }

          if (rowIndex < caveFloor.lastIndex) {
            // if below is smaller than point, false
            if (caveFloor[rowIndex + 1][pointIndex] <= point) {
              continue
            }
          }

          // if we got here, true
          lowPoints += point
        }
      }

//      println("count: ${lowPoints.size}")
//      println("points: $lowPoints")
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