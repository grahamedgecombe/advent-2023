package com.grahamedgecombe.advent2023.day6

import com.grahamedgecombe.advent2023.Puzzle

object Day6 : Puzzle<List<Race>>(6) {
    private val WHITESPACE = Regex(" +")

    override fun parse(input: Sequence<String>): List<Race> {
        val (durations, records) = input.chunked(2).singleOrNull() ?: throw IllegalArgumentException()
        return parseInts(durations).zip(parseInts(records)).map { (duration, record) -> Race(duration, record) }
    }

    override fun solvePart1(input: List<Race>): Int {
        return input.map(Race::countWins).reduce(Int::times)
    }

    private fun parseInts(s: String): List<Int> {
        val (_, list) = s.split(':', limit = 2)

        return list.trim()
            .split(WHITESPACE)
            .map { it.trim().toInt() }
    }
}
