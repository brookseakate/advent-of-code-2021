package com.aoc2021.day4.processors

import com.aoc2021.day4.Board

interface BingoProcessor {
  /**
   * Marks boards and checks for win; returns the specified winning board
   */
  fun processDraw(
    drawValue: Int,
    boards: MutableList<Board>,
  ): Board?

  fun markBoard(
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

  fun Board.hasWon(): Boolean {
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
}
