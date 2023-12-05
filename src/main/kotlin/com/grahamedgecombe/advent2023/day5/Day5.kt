package com.grahamedgecombe.advent2023.day5

import com.grahamedgecombe.advent2023.Puzzle

object Day5 : Puzzle<Almanac>(5) {
    override fun parse(input: Sequence<String>): Almanac {
        return Almanac.parse(input)
    }

    override fun solvePart1(input: Almanac): Long {
        return input.seeds.minOf { seed -> input.transform(seed) }
    }
}
