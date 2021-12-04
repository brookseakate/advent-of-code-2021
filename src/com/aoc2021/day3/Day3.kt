package com.aoc2021.day3

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

class Day3 {
  companion object {
    fun main() {
      println(part2())
    }

    fun part2(): Int {
      val inputs = readFileAsMutableList("day3/Input")
      var o2genCandidates = inputs.toList()
      var co2scrubCandidates = inputs.toList()
      var index = 0

      while (o2genCandidates.size > 1) {
        val grouped: Map<String, List<String>> = o2genCandidates.groupBy { it[index].toString() }
        val zeros: List<String> = grouped.get("0")!!
        val ones: List<String> = grouped.get("1")!!

        o2genCandidates = if (zeros.size > ones.size) {
          zeros
        } else {
          ones
        }

        index++
      }

      index = 0
      // TODO: DRY this?
      while (co2scrubCandidates.size > 1) {
        val grouped: Map<String, List<String>> = co2scrubCandidates.groupBy { it[index].toString() }
        val zeros: List<String> = grouped.get("0")!!
        val ones: List<String> = grouped.get("1")!!

        co2scrubCandidates = if (zeros.size <= ones.size) {
          zeros
        } else {
          ones
        }

        index++
      }

      val o2genRating = o2genCandidates.first().toInt(2)
      val co2scrubRating = co2scrubCandidates.first().toInt(2)

      return o2genRating * co2scrubRating
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
