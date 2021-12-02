package com.aoc2021.day1

import java.nio.file.Files
import java.nio.file.Paths

class Day1 {
  companion object {
    fun part2() {
      var increases = 0

      try {
        val inputLines: MutableList<String> = Files.readAllLines(Paths.get("/Users/kates/learning/advent-of-code-2021/src/com/aoc2021/day1/Part1Input"))

        var firstWindowVals: MutableList<Int> = mutableListOf(
          inputLines.removeAt(0).toInt(),
          inputLines.removeAt(0).toInt(),
          inputLines.removeAt(0).toInt()
        )
//    println("firstWindowVals: " + firstWindowVals)

        var lastWindow: MutableList<Int> = firstWindowVals

        while (inputLines.isNotEmpty()) {

          var currentWindow: MutableList<Int> = mutableListOf(
            lastWindow[1],
            lastWindow[2],
            inputLines.removeAt(0).toInt()
          )

          if (currentWindow.sum() > lastWindow.sum()) {
            increases++
          }
          lastWindow = currentWindow
        }

      } catch (ex: Exception) {
        print(ex)
      }

      println(increases)
    }

    fun part1() {
      var increases = 0

      try {
        val inputLines = Files.readAllLines(Paths.get("/Users/kates/learning/advent-of-code-2021/src/com/aoc2021/day1/Part1Input"))

        var last: Int? = null

        for (line: String in inputLines) {
          var value = line.toInt()

          if (last != null) {
            if (value > last) {
              increases++
            }
          }

          last = value
        }

      } catch (ex: Exception) {
        print(ex)
      }

      println(increases)
    }
  }
}
