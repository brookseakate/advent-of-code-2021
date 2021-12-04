package com.aoc2021.day4

import com.aoc2021.day4.processors.BingoProcessor
import com.aoc2021.day4.processors.FirstWinBingoProcessor
import com.aoc2021.day4.processors.LastWinBingoProcessor
import com.aoc2021.util.Utils.Companion.readFileAsMutableList

typealias Row = List<Square>
typealias Board = List<Row>

data class Square(
  val value: Int,
  var marked: Boolean = false,
)

/**
 * Bingo
 */
class Day4 {
  companion object {
    fun main() {
      println(partOne())
      println(partTwo())
    }

    fun partTwo(): Int {
      return doTheGame(
        processor = LastWinBingoProcessor()
      )
    }

    fun partOne(): Int {
      return doTheGame(
        processor = FirstWinBingoProcessor()
      )
    }

    private fun doTheGame(
      processor: BingoProcessor
    ): Int {
      val input = readFileAsMutableList("day4/Input")

      // get draws
      val draws: List<Int> = input.removeFirst().split(",").map { it.toInt() }
      input.removeFirst() // remove next line (empty string)

      // get boards
      val boards: MutableList<Board> = readInputToBoards(input).toMutableList()

      // process draws
      for (drawValue in draws) {
        // mark boards and check for our win
        val winningBoard = processor.processDraw(
          drawValue = drawValue,
          boards = boards
        )

        // if our win, calculate & return score
        if (winningBoard != null) {
          return calculateScore(
            board = winningBoard,
            drawValue = drawValue
          )
        }
      }

      throw RuntimeException("You oopsed, no winner here")
    }

    private fun readInputToBoards(
      input: MutableList<String>,
    ): List<Board> {
      return input.chunked(6) { list ->
        list.takeWhile { it.isNotEmpty() }
          .let { stringList ->
            stringList
              .map { string ->
                string
                  .split(" ")
                  .filter { it.isNotEmpty() }
                  .map {
                    Square(
                      value = it.toInt()
                    )
                  }
              }
          }
      }
    }

    private fun calculateScore(
      board: Board,
      drawValue: Int,
    ): Int {
      return drawValue * board
        .flatten()
        .filter { !it.marked }
        .sumOf { it.value }
    }
  }
}