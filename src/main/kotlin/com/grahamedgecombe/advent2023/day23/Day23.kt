package com.grahamedgecombe.advent2023.day23

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Dijkstra
import com.grahamedgecombe.advent2023.util.Vector2

object Day23 : Puzzle<CharGrid>(23) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        val start = Vector2(1, 0)
        return Dijkstra.search(Node(input, start, setOf(start))).maxOf(Dijkstra.Path<Node>::distance)
    }
}
