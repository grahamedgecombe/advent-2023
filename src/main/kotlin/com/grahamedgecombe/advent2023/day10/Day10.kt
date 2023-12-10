package com.grahamedgecombe.advent2023.day10

import com.grahamedgecombe.advent2023.Puzzle

object Day10 : Puzzle<PipeMaze>(10) {
    override fun parse(input: Sequence<String>): PipeMaze {
        return PipeMaze.parse(input.toList())
    }

    override fun solvePart1(input: PipeMaze): Int {
        return input.countSteps()
    }

    override fun solvePart2(input: PipeMaze): Int {
        return input.countEnclosed()
    }
}
