package com.grahamedgecombe.advent2023.day23

import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Dijkstra
import com.grahamedgecombe.advent2023.util.Vector2

data class Node(val grid: CharGrid, val position: Vector2, val visited: Set<Vector2>) : Dijkstra.Node<Node> {
    override val isGoal: Boolean
        get() = position.y == grid.height - 1

    override val neighbours: Sequence<Dijkstra.Neighbour<Node>>
        get() = sequence {
            var currentTile = grid[position]
            var currentPosition = position
            val currentVisited = visited.toMutableSet()

            val neighbours = mutableListOf<Vector2>()
            var length = 1

            while (true) {
                neighbours.clear()

                for ((slope, direction) in SLOPES) {
                    if (currentTile != slope && currentTile != '.') {
                        continue
                    }

                    val nextPosition = currentPosition + direction
                    if (nextPosition in currentVisited || grid[nextPosition] == '#') {
                        continue
                    }

                    neighbours += nextPosition
                }

                val neighbour = neighbours.singleOrNull()
                if (neighbour != null && neighbour.y != grid.height - 1) {
                    currentTile = grid[neighbour]
                    currentPosition = neighbour
                    currentVisited += neighbour
                    length++
                } else {
                    break
                }
            }

            for (neighbour in neighbours) {
                yield(Dijkstra.Neighbour(Node(grid, neighbour, currentVisited + neighbour), length))
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (position != other.position) return false
        if (visited != other.visited) return false

        return true
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + visited.hashCode()
        return result
    }

    private companion object {
        private val SLOPES = mapOf(
            '^' to Vector2(0, -1),
            'v' to Vector2(0, 1),
            '<' to Vector2(-1, 0),
            '>' to Vector2(1, 0),
        )
    }
}
