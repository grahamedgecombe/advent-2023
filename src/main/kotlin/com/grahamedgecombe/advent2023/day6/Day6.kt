package com.grahamedgecombe.advent2023.day6

import com.grahamedgecombe.advent2023.Puzzle

object Day6 : Puzzle<Pair<List<Race>, Race>>(6) {
    private val WHITESPACE = Regex(" +")

    override fun parse(input: Sequence<String>): Pair<List<Race>, Race> {
        val (durations, records) = input.chunked(2).singleOrNull() ?: throw IllegalArgumentException()

        val part1 = parseInts(durations).zip(parseInts(records)).map { (duration, record) -> Race(duration, record) }
        val part2 = Race(parseInt(durations), parseInt(records))

        return Pair(part1, part2)
    }

    override fun solvePart1(input: Pair<List<Race>, Race>): Int {
        return input.first.map(Race::countWins).reduce(Int::times)
    }

    override fun solvePart2(input: Pair<List<Race>, Race>): Int {
        return input.second.countWins()
    }

    private fun parseInts(s: String): List<Long> {
        val (_, list) = s.split(':', limit = 2)

        return list.trim()
            .split(WHITESPACE)
            .map { it.trim().toLong() }
    }

    private fun parseInt(s: String): Long {
        val (_, list) = s.split(':', limit = 2)

        return list.replace(WHITESPACE, "").toLong()
    }
}
