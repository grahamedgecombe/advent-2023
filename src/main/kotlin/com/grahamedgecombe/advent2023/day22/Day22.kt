package com.grahamedgecombe.advent2023.day22

import com.grahamedgecombe.advent2023.Puzzle

object Day22 : Puzzle<BrickSet>(22) {
    override fun parse(input: Sequence<String>): BrickSet {
        return BrickSet.parse(input)
    }

    override fun solvePart1(input: BrickSet): Int {
        return input.bricks.indices.count { i ->
            input.supports[i]!!.all { j ->
                input.supportedBy[j]!!.size > 1
            }
        }
    }

    override fun solvePart2(input: BrickSet): Int {
        return input.bricks.indices.sumOf(input::chainReaction)
    }
}
