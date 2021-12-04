package com.aoc2021.day4.processors

import com.aoc2021.day4.Board

class FirstWinBingoProcessor : BingoProcessor {
  override fun processDraw(
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
}