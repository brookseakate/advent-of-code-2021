package com.aoc2021.day8

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

typealias Pattern = Pair<List<String>, List<String>>

class Day8 {
  private enum class SignalDisplay(
    val segmentQuantity: Int
  ) {
    ONE(2),
    FOUR(4),
    SEVEN(3),
    EIGHT(7);
  }

  companion object {

    fun main() {
      val input = readFileAsMutableList("day8/Input")
//      val input = readFileAsMutableList("day8/ExampleInput")
      val patterns = readInputAsPatterns(input)

//      println(partOne(patterns))
      println(partTwo(patterns))
    }

    private fun partOne(
      patterns: List<Pattern>
    ): Int {
      val outputVals = patterns
        .map { it.second }

      return outputVals
        .flatten()
        .filter { outputVal ->
          SignalDisplay
            .values()
            .map { sdVal -> sdVal.segmentQuantity }
            .contains(outputVal.length)
        }
        .size
    }

    private fun partTwo(
      patterns: List<Pattern>
    ): Int {
      var total = 0
      for (pattern in patterns) {
        val digitStrings = figureOutNumbers(pattern.first)

        total += mapOutputVals(
          digitStrings = digitStrings,
          outputVals = pattern.second
        )
      }

      return total
    }

    private fun mapOutputVals(
      digitStrings: List<String>,
      outputVals: List<String>
    ): Int {
      var outputString = ""

      for (oVal in outputVals) {
        val matchingDigit = digitStrings.find { it == oVal.toCharArray().sorted().joinToString("") }!!
        outputString += digitStrings.indexOf(matchingDigit).toString()
      }

      return outputString.toInt()
    }

    private fun figureOutNumbers(
      sigPatterns: List<String>
    ): List<String> {
      // index = digit
      val digitStrings = MutableList(10) { "" }

      // get unique-number digitStrings
      // if length matches, set
      digitStrings[1] = sigPatterns.find { it.length == SignalDisplay.ONE.segmentQuantity }!!
      digitStrings[4] = sigPatterns.find { it.length == SignalDisplay.FOUR.segmentQuantity }!!
      digitStrings[7] = sigPatterns.find { it.length == SignalDisplay.SEVEN.segmentQuantity }!!
      digitStrings[8] = sigPatterns.find { it.length == SignalDisplay.EIGHT.segmentQuantity }!!

      val fiveSegmentStrings = sigPatterns.filter { it.length == 5 }

      for (string in fiveSegmentStrings) {
        val splitString = string.split("")
        if (
          splitString
            .containsAll(
              digitStrings[7].split("")
            )
        ) {
          // if contains 7's letters: 3
          digitStrings[3] = string
        } else if (
          splitString
            .filter {
              it in digitStrings[4]
            }.size == 5 // 3, plus leading/trailing "" I'm too lazy to remove
        ) {
          // else if contains 3 of 4's letters: 5
          digitStrings[5] = string
        } else {
          // else: 2
          digitStrings[2] = string
        }

        val sixSegmentStrings = sigPatterns.filter { it.length == 6 }

        for (string in sixSegmentStrings) {
          val splitString = string.split("")

          if (!splitString.containsAll(digitStrings[1].split(""))) {
            // if not both of 1's letters: 6
            digitStrings[6] = string
          } else if (!splitString.containsAll(digitStrings[4].split(""))) {
            // if not all of 4's letters: 0
            digitStrings[0] = string
          } else {
            // else: 9
            digitStrings[9] = string
          }
        }
      }

      return digitStrings.map { it.toCharArray().sorted().joinToString("") }
    }

    private fun readInputAsPatterns(
      input: MutableList<String>
    ): List<Pattern> /* Pattern = Pair<List<String>, List<String>> */ {
      return input.map { line ->
        val splitLine = line.split(" | ")

        val sigPatterns = splitLine[0].split(" ")
        val outputVals = splitLine[1].split(" ")

        Pair(sigPatterns, outputVals)
      }
    }
  }
}
