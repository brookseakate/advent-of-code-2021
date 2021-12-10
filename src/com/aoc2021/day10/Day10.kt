package com.aoc2021.day10

import com.aoc2021.day10.Day10.Companion.Brace.Companion.getByCloseBrace
import com.aoc2021.day10.Day10.Companion.Brace.Companion.getByOpenBrace
import com.aoc2021.util.Utils.Companion.readFileAsMutableList

typealias Stack = MutableList<Char>

class Day10 {
  companion object {
    private enum class Brace(
      val open: Char,
      val close: Char,
      val syntaxErrorScore: Int,
      val autocompleteScore: Int,
    ) {
      Round('(', ')', 3, 1),
      Square('[', ']', 57, 2),
      Curly('{', '}', 1197, 3),
      Angle('<', '>', 25137, 4);

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
    ): Long {
      val lineScores = mutableListOf<Long>()
      // for each line:
      // - get completion string
      // -- use part one (ignore line if syntax error)
      line@ for (line in lines) {
        var stack = mutableListOf<Char>()

        for (char in line) {
          if (char in Brace.values().map { it.open }) {
            stack.add(char)
          } else {
            val thisBrace = getByCloseBrace(char)
            val poppedOpenBrace = stack.removeLast()

            if (poppedOpenBrace != thisBrace.open) {
              // syntax error!
              continue@line
            }
          }
        }

        // -- after line is iterated, pop each remaining to construct completion string
        var completionString = ""
        for (brace in stack.reversed()) {
          completionString += getByOpenBrace(brace).close
        }

        // - get completion string score
        // - add to lineScores
        lineScores.add(getCompletionStringScore(completionString))
      }

      // sort linesScores
      lineScores.sort()

      // return middle
      return lineScores[lineScores.size/2]
    }

    private fun getCompletionStringScore(
      completionString: String
    ): Long {
      var score = 0L
      for (char in completionString) {
        score *= 5L
        score += getByCloseBrace(char).autocompleteScore
      }

      return score
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
              score += thisBrace.syntaxErrorScore
              break
            }
          }
        }
      }

      return score
    }
  }
}