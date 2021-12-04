package com.aoc2021.day4

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

typealias Row = List<Square>
typealias Board = List<Row>

data class Square(
  val value: Int,
  var marked: Boolean = false,
)

class Day4 {
  companion object {
    fun main() {
      println(partOne())
    }

    fun partOne(): Int {
      val input = readFileAsMutableList("day4/Input")
      val draws = input.removeFirst()

      input.removeFirst() // remove next line (empty string)

      val boards: MutableList<Board> = mutableListOf()

      // get boards
      while (input.isNotEmpty()) {
        val board: Board = input
          .takeWhile { it.isNotEmpty() }
          .let { stringList ->
            stringList
              .map { string ->
                string
                  .split(" ")
                  .map {
                    Square(
                      value = it.toInt()
                    )
                  }
              }
          }

        boards.add(board)
        input.removeFirst() // remove next line (empty string)
      }

      // process draws
        // mark board squares
        // check for win
          // if yes:
            // save winning board
            // save draw value

      // calculate score - input winning board, draw value
        // sum unmarked numbers
        // * draw value

      // return score
    }
  }
}