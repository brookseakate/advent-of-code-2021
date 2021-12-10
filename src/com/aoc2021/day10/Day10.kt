package com.aoc2021.day10

import com.aoc2021.day10.Day10.Companion.Brace.Companion.getByCloseBrace
import com.aoc2021.util.Utils.Companion.readFileAsMutableList

typealias Stack = MutableList<Char>

class Day10 {
  companion object {
    private enum class Brace(
      val open: Char,
      val close: Char,
      val score: Int
    ) {
      Round('(', ')', 3),
      Square('[', ']', 57),
      Curly('{', '}', 1197),
      Angle('<', '>', 25137);

      companion object {
        fun getByOpenBrace(openBrace: Char): Brace {
          return values().find { it.open == openBrace }!!
        }

        fun getByCloseBrace(closeBrace: Char): Brace {
          return values().find { it.close == closeBrace }!!
        }
      }
    }

    fun main() {
      val input = readFileAsMutableList("day10/Input")
//      val input = readFileAsMutableList("day10/ExampleInput")
      val lines = input.map { it.toCharArray() }

      println(partTwo(lines))
    }

    private fun partTwo(
      lines: List<CharArray>
    ): Int {
      val lineScores = mutableListOf<Int>()
      // for each line:
      // - get completion string
      // -- use part one (ignore line if syntax error)
      // -- after line is iterated, pop each remaining to construct completion string
      // - get completion string score
      // - add to lineScores

      // sort linesScores
      return lineScores[lineScores.size/2 + 1]
    }

    private fun partOne(
      lines: List<CharArray>
    ): Int {
      var score = 0

      for (line in lines) {
        var stack = mutableListOf<Char>()

        for (char in line) {
          if (char in Brace.values().map { it.open }) {
            stack.add(char)
          } else {
            val thisBrace = getByCloseBrace(char)
            val poppedOpenBrace = stack.removeLast()

            if (poppedOpenBrace != thisBrace.open) {
              // syntax error!
              score += thisBrace.score
              break
            }
          }
        }
      }

      return score
    }
  }
}