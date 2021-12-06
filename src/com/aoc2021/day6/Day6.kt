package com.aoc2021.day6

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

/**
 * Lanternfish exponential population growth simulation
 */
class Day6 {
  companion object {
    lateinit var lanternFishPopulation: MutableList<Int>

    fun main() {
      val input = readFileAsMutableList("day6/Input")
      lanternFishPopulation = input
        .first()
        .split(",")
        .map { it.toInt() }
        .toMutableList()

      println(partOne())
    }

    fun partOne(
//    population: MutableList<Int>
    ): Int {
      for (day in 1..80) {
        var updatedPop = mutableListOf<Int>()

        lanternFishPopulation.forEachIndexed { i, fish ->
          if (fish > 0) {
            updatedPop.add(fish - 1)
          } else if (fish == 0) {
            // this modifies initial order here, we don't care about order (at least right now)
            updatedPop.add(6)
            updatedPop.add(8)
          }
        }

        lanternFishPopulation = updatedPop
      }

      return lanternFishPopulation.size
    }
  }
}
