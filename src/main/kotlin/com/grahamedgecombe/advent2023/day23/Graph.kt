package com.grahamedgecombe.advent2023.day23

import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2
import kotlin.math.max

class Graph(
    private val nodes: MutableMap<Vector2, MutableMap<Vector2, Int>>,
    private val height: Int,
) {
    fun longestPath(): Int {
        return dfs(START, 0, mutableSetOf(START))
    }

    private fun dfs(current: Vector2, len: Int, visited: MutableSet<Vector2>): Int {
        if (current.y == height - 1) {
            return len
        }

        var best = Int.MIN_VALUE

        for ((next, weight) in nodes[current]!!) {
            if (next in visited) {
                continue
            }

            visited += next
            best = max(best, dfs(next, len + weight, visited))
            visited -= next
        }

        return best
    }

    companion object {
        private val START = Vector2(1, 0)
        private val SLOPES = mapOf(
            '^' to Vector2(0, -1),
            'v' to Vector2(0, 1),
            '<' to Vector2(-1, 0),
            '>' to Vector2(1, 0),
        )

        fun compress(grid: CharGrid, enableSlopes: Boolean): Graph {
            val nodes = mutableMapOf<Vector2, MutableMap<Vector2, Int>>()
            val start = Vector2(1, 0)
            compress(grid, enableSlopes, start, start, nodes, mutableSetOf())
            return Graph(nodes, grid.height)
        }

        private fun compress(
            grid: CharGrid,
            enableSlopes: Boolean,
            previous: Vector2,
            current: Vector2,
            nodes: MutableMap<Vector2, MutableMap<Vector2, Int>>,
            visited: MutableSet<Pair<Vector2, Vector2>>,
        ) {
            if (!visited.add(Pair(previous, current))) {
                return
            }

            val start = previous

            var previous = previous
            var current = current

            var tile = grid[current]
            var len = if (previous != current) 1 else 0

            val neighbours = mutableListOf<Vector2>()

            while (true) {
                neighbours.clear()

                for ((slope, delta) in SLOPES) {
                    if (enableSlopes && tile != slope && tile != '.') {
                        continue
                    }

                    val next = current + delta
                    if (next == previous || grid[next] == '#') {
                        continue
                    }

                    neighbours += next
                }

                if (enableSlopes && tile != '.') {
                    break // can't compress slopes as we may have missed inbound edges above
                } else if (current.y == grid.height - 1) {
                    break // can't compress goal
                }

                val neighbour = neighbours.singleOrNull() ?: break // can't compress tiles with multiple edges

                previous = current
                current = neighbour
                tile = grid[current]
                len++
            }

            nodes.getOrPut(start, ::mutableMapOf)[current] = len

            if (!enableSlopes || tile == '.') {
                nodes.getOrPut(current, ::mutableMapOf)[start] = len
            }

            for (neighbour in neighbours) {
                compress(grid, enableSlopes, current, neighbour, nodes, visited)
            }
        }
    }
}
