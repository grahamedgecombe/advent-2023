package com.grahamedgecombe.advent2023.day19

import com.grahamedgecombe.advent2023.Puzzle

object Day19 : Puzzle<System>(19) {
    override fun parse(input: Sequence<String>): System {
        return System.parse(input)
    }

    override fun solvePart1(input: System): Int {
        return input.sumAcceptedRatings()
    }

    override fun solvePart2(input: System): Long {
        return input.countCombinations()
    }
}
