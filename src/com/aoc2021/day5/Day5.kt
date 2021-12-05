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
//      println(partTwo(inputs))
    }

    private fun partOne(
      coordinates: List<Coordinate>
    ): Int {
      for (c in coordinates) {
        if (c.one.x == c.two.x) {
          if (c.one.y <= c.two.y) {
            for (y in c.one.y..c.two.y) {
              field[y][c.one.x]++
            }
          } else if (c.one.y > c.two.y) {
            for (y in c.one.y downTo c.two.y) {
              field[y][c.one.x]++
            }
          }

        } else if (c.one.y == c.two.y) {
          if (c.one.x <= c.two.x) {
            for (x in c.one.x..c.two.x) {
              field[c.one.y][x]++
            }
          } else if (c.one.x > c.two.x) {
            for (x in c.one.x downTo c.two.x) {
              field[c.one.y][x]++
            }
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
