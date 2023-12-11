package com.grahamedgecombe.advent2023.day11

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.Vector2

object Day11 : Puzzle<Set<Vector2>>(11) {
    override fun parse(input: Sequence<String>): Set<Vector2> {
        val galaxies = mutableSetOf<Vector2>()

        for ((y, row) in input.withIndex()) {
            for ((x, tile) in row.withIndex()) {
                if (tile == '#') {
                    galaxies += Vector2(x, y)
                }
            }
        }

        return galaxies
    }

    override fun solvePart1(input: Set<Vector2>): Long {
        return solve(input, 2)
    }

    override fun solvePart2(input: Set<Vector2>): Long {
        return solve(input, 1000000)
    }

    fun solve(input: Set<Vector2>, factor: Int): Long {
        return sumDistances(expand(input, factor).toList())
    }

    private fun expand(galaxies: Set<Vector2>, factor: Int): Set<Vector2> {
        return expandX(expandY(galaxies, factor), factor)
    }

    private fun expandX(galaxies: Set<Vector2>, factor: Int): Set<Vector2> {
        val expanded = mutableSetOf<Vector2>()

        val sorted = galaxies.sortedBy(Vector2::x)

        val first = sorted.firstOrNull() ?: return expanded
        expanded += first

        var extraSpace = 0

        for ((a, b) in sorted.zipWithNext()) {
            val diff = b.x - a.x - 1
            if (diff > 0) {
                extraSpace += diff * (factor - 1)
            }

            expanded += Vector2(b.x + extraSpace, b.y)
        }

        return expanded
    }

    private fun expandY(galaxies: Set<Vector2>, factor: Int): Set<Vector2> {
        val expanded = mutableSetOf<Vector2>()

        val sorted = galaxies.sortedBy(Vector2::y)

        val first = sorted.firstOrNull() ?: return expanded
        expanded += first

        var extraSpace = 0

        for ((a, b) in sorted.zipWithNext()) {
            val diff = b.y - a.y - 1
            if (diff > 0) {
                extraSpace += diff * (factor - 1)
            }

            expanded += Vector2(b.x, b.y + extraSpace)
        }

        return expanded
    }

    private fun sumDistances(galaxies: List<Vector2>): Long {
        var sum = 0L

        for ((i, a) in galaxies.withIndex()) {
            for (b in galaxies.subList(0, i)) {
                sum += (a - b).manhattanDistance.toLong()
            }
        }

        return sum
    }
}
