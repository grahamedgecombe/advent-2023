package com.grahamedgecombe.advent2023.day17

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.UnsolvableException
import com.grahamedgecombe.advent2023.util.Dijkstra
import com.grahamedgecombe.advent2023.util.CharGrid

object Day17 : Puzzle<CharGrid>(17) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), ' ')
    }

    override fun solvePart1(input: CharGrid): Int {
        return solve(input, 1, 3)
    }

    override fun solvePart2(input: CharGrid): Int {
        return solve(input, 4, 10)
    }

    private fun solve(grid: CharGrid, minLen: Int, maxLen: Int): Int {
        val path = Dijkstra.search(
            Node(grid, 0, 0, 1, 0, minLen, maxLen),
            Node(grid, 0, 0, 0, 1, minLen, maxLen),
        ).firstOrNull() ?: throw UnsolvableException()
        return path.distance
    }
}
