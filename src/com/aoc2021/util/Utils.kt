package com.aoc2021.util

import java.nio.file.Files
import java.nio.file.Paths

class Utils {
  companion object {
    fun readFileAsMutableList(relFilePath: String): MutableList<String> {
      return Files
        .readAllLines(
          Paths.get("./src/com/aoc2021/$relFilePath")
        )
        .toMutableList()
    }
  }
}