package com.aoc2021.day5

import com.aoc2021.util.Utils.Companion.readFileAsMutableList

data class Point(
  val x: Int,
  val y: Int,
)

data class Coordinate(
  val one: Point,
  val two: Point
)

class Day5 {
  companion object {
    private val field = MutableList(1000) { MutableList(1000) { 0 } }
//    private val field = MutableList(10) { MutableList(10) { 0 } }

    fun main() {
      val input = readFileAsMutableList("day5/Input")
//      val input = readFileAsMutableList("day5/ExampleInput")
      val coordinates: List<Coordinate> = readInputAsCoordinates(input)
//      println("input line count: ${input.size}")
//      println("coordinate count: ${coordinates.size}")

      println(partOne(coordinates))
      println(partTwo(coordinates))
    }

    private fun partTwo(
      coordinates: List<Coordinate>
    ): Int {
      for (c in coordinates) {
        val yProgression = if (c.one.y <= c.two.y) {
          c.one.y..c.two.y
        } else {
          c.one.y downTo c.two.y
        }

        val xProgression = if (c.one.x <= c.two.x) {
          c.one.x..c.two.x
        } else {
          c.one.x downTo c.two.x
        }

        if (c.one.x == c.two.x) {
          for (y in yProgression) {
            field[y][c.one.x]++
          }
        } else if (c.one.y == c.two.y) {
          for (x in xProgression) {
            field[c.one.y][x]++
          }
        } else {
          val xProgressionList = xProgression.toList()

          if (yProgression.toList().size != xProgressionList.size) {
            throw RuntimeException("Bug! Progression lengths don't match. " +
              "y: $yProgression, yLength: ${yProgression.toList().size}," +
              "x: $xProgression, xProgressionList: $xProgressionList, xLength: ${xProgressionList.size}")
          }

          var xIndex = 0
          for (y in yProgression) {
            field[y][xProgressionList[xIndex]]++
            xIndex++
          }
        }
      }

//      println("Final field: ${fieldToString(field)}")
      return field.flatten().filter { it >= 2 }.size
    }

    private fun fieldToString(
      field: MutableList<MutableList<Int>>
    ): String {
      return "[\n" + field.map {
        "$it\n"
      } +
        "]"
    }

    private fun partOne(
      coordinates: List<Coordinate>
    ): Int {
      for (c in coordinates) {
        if (c.one.x == c.two.x) {
          val yProgression = if (c.one.y <= c.two.y) {
            c.one.y..c.two.y
          } else {
            c.one.y downTo c.two.y
          }

          for (y in yProgression) {
            field[y][c.one.x]++
          }

        } else if (c.one.y == c.two.y) {
          val xProgression = if (c.one.x <= c.two.x) {
            c.one.x..c.two.x
          } else {
            c.one.x downTo c.two.x
          }

          for (x in xProgression) {
            field[c.one.y][x]++
          }
        }
      }

//      println("Final field: $field")
      return field.flatten().filter { it >= 2 }.size
    }

    private fun readInputAsCoordinates(
      input: MutableList<String>
    ): List<Coordinate> {
      return input.map { it ->
        val pieces = it
          .split(",", " -> ")
          .map { it.toInt() }

        if (pieces.any { it > 999 }) {
          throw RuntimeException("Nope, need a bigger field. At least: $it")
        }

        Coordinate(
          one = Point(
            x = pieces[0],
            y = pieces[1]
          ),
          two = Point(
            x = pieces[2],
            y = pieces[3]
          )
        )
      }
    }
  }
}
