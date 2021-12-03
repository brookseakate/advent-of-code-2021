package com.aoc2021.day3

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

class Day3 {
  companion object {
    fun main() {
      println(part1())
    }

    fun part1(): Int {
      val inputs = readFileAsMutableList("day3/Input")
      var gammaRateString = ""
      var epsilonRateString = ""

      inputs[0].forEachIndexed { index, _ ->
        val grouped: Map<String, List<String>> = inputs.groupBy { it[index].toString() }
        val zeros: List<String> = grouped.get("0")!!
        val ones: List<String> = grouped.get("1")!!

        if (zeros.size > ones.size) {
          gammaRateString += "0"
          epsilonRateString += "1"
        } else {
          gammaRateString += "1"
          epsilonRateString += "0"
        }
      }

      println("gamma: $gammaRateString")
      println("epsilon: $epsilonRateString")

      val gammaRate = gammaRateString.toInt(2)
      val epsilonRate = epsilonRateString.toInt(2)

      return gammaRate * epsilonRate
    }
  }
}
