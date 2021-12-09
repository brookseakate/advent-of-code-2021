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

      println(partOne(patterns))
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
      // figure out the patterns
      // map the output vals -> digitStrings -> ints
      // sum output vals & return
    }

    private fun figureOutNumbers(
      sigPatterns: List<String>
    ): List<String> {
      // index = digit
      val digitStrings = MutableList(10) { "" }

      // TODO
      // if length 2: 1
      // if length 3: 7
      // l 4: 4
      // l 7: 8
      // l 5:
      // if contains 7's letters: 3
      // else if contains 3 of 4's letters: 5
      // else: 2
      // l 6:
      // if not both of 1's letters: 6
      // if not all of 4's letters: 0
      // else: 9

      return digitStrings
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