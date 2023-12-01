package com.grahamedgecombe.advent2023.day1

import com.grahamedgecombe.advent2023.Puzzle

object Day1 : Puzzle<List<String>>(1) {
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
}
