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
      println(partTwo())
    }

    // TODO: DRY all up (BoardProcessor interface)
    fun partTwo(): Int {
      val input = readFileAsMutableList("day4/Input")

      // get draws
      val draws: List<Int> = input.removeFirst().split(",").map { it.toInt() }
      input.removeFirst() // remove next line (empty string)

      // get boards
      val boards: MutableList<Board> = readInputToBoards(input).toMutableList()

      // process draws
      for (drawValue in draws) {
        // mark boards and check for win
        val lastWinningBoard = processDrawAndReturnLastWin(
          drawValue = drawValue,
          boards = boards
        )

        // if win, calculate & return score
        if (lastWinningBoard != null) {
          return calculateScore(
            board = lastWinningBoard,
            drawValue = drawValue
          )
        }
      }

      throw RuntimeException("You oopsed, no winner here")
    }

    fun partOne(): Int {
      val input = readFileAsMutableList("day4/Input")

      // get draws
      val draws: List<Int> = input.removeFirst().split(",").map { it.toInt() }
      input.removeFirst() // remove next line (empty string)

      // get boards
      val boards: List<Board> = readInputToBoards(input)

      // process draws
      for (drawValue in draws) {
        // mark boards and check for win
        val winningBoard = processDrawAndReturnFirstWin(
          drawValue = drawValue,
          boards = boards
        )

        // if win, calculate & return score
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

    private fun processDrawAndReturnLastWin(
      drawValue: Int,
      boards: MutableList<Board>,
    ): Board? /* Returns last-winning board */ {
      var winningBoards = mutableListOf<Board>()

      for (board in boards) {
        markBoard(
          drawValue = drawValue,
          board = board
        )

        if (board.hasWon()) {
          winningBoards.add(board) // can't modify boards in-line while being accessed, so handle in 2 steps like this
        }
      }

      for (win in winningBoards) {
        if (boards.size > 1) {
          boards.remove(win)
        } else {
          return win
        }
      }

      return null
    }

    private fun processDrawAndReturnFirstWin(
      drawValue: Int,
      boards: List<Board>,
    ): Board? /* Returns winning board, if any */ {
      for (board in boards) {
        markBoard(
          drawValue = drawValue,
          board = board
        )

        if (board.hasWon()) {
          return board
        }
      }

      return null
    }

    private fun markBoard(
      drawValue: Int,
      board: Board,
    ) {
      board
        .map { row ->
          row.map {
            if (it.value == drawValue) {
              it.marked = true
            }
          }
        }
    }

    private fun Board.hasWon(): Boolean {
      for (row in this) {
        if (row.all { it.marked }) {
          return true
        }
      }

      val rowSize = this.first().size
      for (index in 0 until rowSize) {
        val column = this.map { row ->
          row[index]
        }

        if (column.all { it.marked }) {
          return true
        }
      }

      return false
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