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
      var lowPointCount = 0

      for ((rowIndex, row) in caveFloor.withIndex()) {
        for ((pointIndex, point) in row.withIndex()) {
            try {
              if (
                row[pointIndex - 1] < point &&
                  row[pointIndex + 1] < point &&
                  caveFloor[rowIndex - 1][pointIndex] < point &&
                  caveFloor[rowIndex + 1][pointIndex] < point
              ) {
                lowPointCount++
              }
            } catch (oob: IndexOutOfBoundsException) {
              continue
            }
        }
      }
      return lowPointCount
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