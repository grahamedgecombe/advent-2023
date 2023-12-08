package com.grahamedgecombe.advent2023.day8

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.UnsolvableException

object Day8 : Puzzle<Input>(8) {
    override fun parse(input: Sequence<String>): Input {
        return Input.parse(input)
    }

    override fun solvePart1(input: Input): Int {
        var node = "AAA"
        var count = 0

        while (node != "ZZZ") {
            val direction = input.directions[count % input.directions.size]
            val (left, right) = input.nodes[node] ?: throw UnsolvableException()

            node = when (direction) {
                Direction.LEFT -> left
                Direction.RIGHT -> right
            }
            count++
        }

        return count
    }
}
