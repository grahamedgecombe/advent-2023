package com.grahamedgecombe.advent2023.day21

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.Bfs
import com.grahamedgecombe.advent2023.util.CharGrid

object Day21 : Puzzle<CharGrid>(21) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        return solvePart1(input, 64)
    }

    fun solvePart1(input: CharGrid, steps: Int): Int {
        val start = input.find('S') ?: throw IllegalArgumentException()
        return Bfs.search(Node(input, start.x, start.y, steps))
            .distinctBy { path -> path.last() }
            .count()
    }
}
