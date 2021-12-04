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
      val draws: List<Int> = input.removeFirst().split(",").map { it.toInt() }

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
                  .filter { it.isNotEmpty() }
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

      for (drawValue in draws) {
        val winningBoard = processDraw(
          drawValue = drawValue,
          boards = boards
        )

        if (winningBoard != null) {
          return calculateScore(
            board = winningBoard,
            drawValue = drawValue
          )
        }
      }

      throw RuntimeException("You oopsed, no winner here")
    }

    private fun processDraw(
      drawValue: Int,
      boards: MutableList<Board>,
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
//    ): Board /* Return this board as a new board so we don't have to handle mutable lists everywhere */{
    ) {
//      val updatedBoard = board
      board
        .map { row ->
          row.map {
            // TODO: prob need to apply this / return as updated board
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