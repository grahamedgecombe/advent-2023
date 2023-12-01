package com.grahamedgecombe.advent2023.day1

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.UnsolvableException

object Day1 : Puzzle<List<String>>(1) {
    private val WORDS = listOf(
        "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "1", "2", "3", "4", "5", "6", "7", "8", "9"
    )

    override fun parse(input: Sequence<String>): List<String> {
        return input.toList()
    }

    override fun solvePart1(input: List<String>): Int {
        return input.sumOf { line ->
            val first = line.first(Char::isDigit).digitToInt()
            val last = line.last(Char::isDigit).digitToInt()
            first * 10 + last
        }
    }

    override fun solvePart2(input: List<String>): Int {
        return input.sumOf { line ->
            val (_, first) = line.findAnyOf(WORDS) ?: throw UnsolvableException()
            val (_, last) = line.findLastAnyOf(WORDS) ?: throw UnsolvableException()
            wordToInt(first) * 10 + wordToInt(last)
        }
    }

    private fun wordToInt(word: String): Int {
        return word.toIntOrNull() ?: (WORDS.indexOf(word) + 1)
    }
}
