package com.grahamedgecombe.advent2023.day23

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid

object Day23 : Puzzle<CharGrid>(23) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        return Graph.compress(input, enableSlopes = true).longestPath()
    }

    override fun solvePart2(input: CharGrid): Int {
        return Graph.compress(input, enableSlopes = false).longestPath()
    }
}
