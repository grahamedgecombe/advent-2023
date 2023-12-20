package com.grahamedgecombe.advent2023.day8

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.lcm

object Day8 : Puzzle<Input>(8) {
    override fun parse(input: Sequence<String>): Input {
        return Input.parse(input)
    }

    override fun solvePart1(input: Input): Int {
        return input.solve("AAA") { it == "ZZZ" }
    }

    override fun solvePart2(input: Input): Long {
        return input.nodes.keys.filter { it.endsWith("A") }
            .map { start -> input.solve(start) { it.endsWith("Z") }.toLong() }
            .reduce(::lcm)
    }
}
