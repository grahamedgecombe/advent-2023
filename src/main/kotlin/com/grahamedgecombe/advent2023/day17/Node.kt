package com.grahamedgecombe.advent2023.day17

import com.grahamedgecombe.advent2023.util.Dijkstra
import com.grahamedgecombe.advent2023.util.CharGrid

data class Node(
    val grid: CharGrid,
    val x: Int,
    val y: Int,
    val dx: Int,
    val dy: Int,
) : Dijkstra.Node<Node> {
    override val isGoal: Boolean
        get() = x == (grid.width - 1) && y == (grid.height - 1)

    override val neighbours: Sequence<Dijkstra.Neighbour<Node>>
        get() = sequence {
            var cost = 0
            var x0 = x
            var y0 = y

            for (i in 1..3) {
                x0 += dx
                y0 += dy

                val tile = grid[x0, y0]
                if (tile == ' ') {
                    break
                }

                cost += tile.digitToInt()

                yield(Dijkstra.Neighbour(Node(grid, x0, y0, dy, -dx), cost))
                yield(Dijkstra.Neighbour(Node(grid, x0, y0, -dy, dx), cost))
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (x != other.x) return false
        if (y != other.y) return false
        if (dx != other.dx) return false
        if (dy != other.dy) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + dx
        result = 31 * result + dy
        return result
    }
}
