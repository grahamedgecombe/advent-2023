package com.grahamedgecombe.advent2023.day20

import com.grahamedgecombe.advent2023.Puzzle

object Day20 : Puzzle<Circuit>(20) {
    override fun parse(input: Sequence<String>): Circuit {
        return Circuit.parse(input)
    }

    override fun solvePart1(input: Circuit): Int {
        return input.evaluate()
    }
}
