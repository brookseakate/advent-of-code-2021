package com.aoc2021.day6

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

/**
 * Lanternfish exponential population growth simulation
 *
 * Favorite/funnest ever?
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

//      println(partOne(80))
      println(partTwo(256))
    }

    private fun partTwo(
      dayCount: Int
    ): Long {
      val popTracker = MutableList(9) { 0L }

      // set initial values
      for (dayValue in lanternFishPopulation) {
        popTracker[dayValue.toInt()] = popTracker[dayValue.toInt()] + 1
      }

      // simulate pop growth
      for (day in 1..dayCount) {
        val birthingTodayCount = popTracker.removeAt(0) // remove 0-index (day) count

        popTracker.add(birthingTodayCount) // add 8-index (day) count
        popTracker[6] = popTracker[6] + birthingTodayCount // update 6-index (day) count
      }

      return popTracker.sum()
    }

    private fun partOne(
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
