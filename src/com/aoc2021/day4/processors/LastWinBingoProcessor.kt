package com.aoc2021.day4.processors

import com.aoc2021.day4.Board

class LastWinBingoProcessor : BingoProcessor {
  override fun processDraw(
    drawValue: Int,
    boards: MutableList<Board>,
  ): Board? /* Returns last-winning board, if found */ {
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
}