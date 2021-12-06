package com.aoc2021.day6

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

/**
 * Lanternfish exponential population growth simulation
 */
class Day6 {
  companion object {
    lateinit var lanternFishPopulation: MutableList<Long>

    fun main() {
      val input = readFileAsMutableList("day6/Input")
      lanternFishPopulation = input
        .first()
        .split(",")
        .map { it.toLong() }
        .toMutableList()

      println(partOneAndTwo(256))
    }

    private fun partOneAndTwo(
      dayCount: Int // partOne: 80, partTwo: 256
    ): Long {
      for (day in 1..dayCount) {
        var updatedPop = mutableListOf<Long>()

        lanternFishPopulation.forEachIndexed { i, fish ->
          if (fish > 0) {
            updatedPop.add(fish - 1)
          } else if (fish == 0L) {
            // this modifies initial order here, we don't care about order (at least right now)
            updatedPop.add(6)
            updatedPop.add(8)
          }
        }

        lanternFishPopulation = updatedPop
      }

      return lanternFishPopulation.size.toLong() // this obv won't work, but force compilation to see what happens
    }
  }
}
