package com.grahamedgecombe.advent2023.day15

import com.grahamedgecombe.advent2023.Puzzle

object Day15 : Puzzle<List<String>>(15) {
    override fun parse(input: Sequence<String>): List<String> {
        val line = input.singleOrNull() ?: throw IllegalArgumentException()
        return line.split(',')
    }

    override fun solvePart1(input: List<String>): Int {
        return input.sumOf(::hash)
    }

    private fun hash(s: String): Int {
        var sum = 0
        for (c in s) {
            sum = ((sum + c.code) * 17) and 0xFF
        }
        return sum
    }
}
