package com.grahamedgecombe.advent2023.day16

import com.grahamedgecombe.advent2023.Puzzle

object Day16 : Puzzle<Contraption>(16) {
    override fun parse(input: Sequence<String>): Contraption {
        return Contraption.parse(input.toList())
    }

    override fun solvePart1(input: Contraption): Int {
        return input.countEnergised()
    }

    override fun solvePart2(input: Contraption): Int {
        return input.countMaxEnergised()
    }
}
