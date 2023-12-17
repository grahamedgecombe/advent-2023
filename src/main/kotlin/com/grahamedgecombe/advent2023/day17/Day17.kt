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
        val path = Dijkstra.search(
            Node(input, 0, 0, 1, 0),
            Node(input, 0, 0, 0, 1),
        ).firstOrNull() ?: throw UnsolvableException()
        return path.distance
    }
}
