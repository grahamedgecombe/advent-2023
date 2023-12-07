package com.grahamedgecombe.advent2023.day7

import com.grahamedgecombe.advent2023.Puzzle

object Day7 : Puzzle<List<Hand>>(7) {
    override fun parse(input: Sequence<String>): List<Hand> {
        return input.map(Hand::parse).toList()
    }

    override fun solvePart1(input: List<Hand>): Int {
        return solve(input)
    }

    override fun solvePart2(input: List<Hand>): Int {
        return solve(input, wildcard = true)
    }

    private fun solve(input: List<Hand>, wildcard: Boolean = false): Int {
        return input.sortedWith(HandComparator(wildcard).reversed())
            .withIndex()
            .sumOf { (rank, hand) -> (rank + 1) * hand.bid }
    }
}
