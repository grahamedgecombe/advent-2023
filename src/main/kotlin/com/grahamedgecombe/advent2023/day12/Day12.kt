package com.grahamedgecombe.advent2023.day12

import com.grahamedgecombe.advent2023.Puzzle

object Day12 : Puzzle<List<Row>>(12) {
    override fun parse(input: Sequence<String>): List<Row> {
        return input.map(Row::parse).toList()
    }

    override fun solvePart1(input: List<Row>): Long {
        return input.sumOf(Row::countArrangements)
    }

    override fun solvePart2(input: List<Row>): Long {
        return input.map(Row::unfold).sumOf(Row::countArrangements)
    }
}
