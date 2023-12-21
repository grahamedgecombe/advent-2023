package com.grahamedgecombe.advent2023.day21

import com.grahamedgecombe.advent2023.util.Bfs
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

data class Node(val grid: CharGrid, val x: Int, val y: Int, val steps: Int) : Bfs.Node<Node> {
    override val isGoal: Boolean
        get() = steps == 0

    override val neighbours: Sequence<Node>
        get() = sequence {
            if (isGoal) {
                return@sequence
            }

            for (direction in DIRECTIONS) {
                if (grid[x + direction.x, y + direction.y] != '#') {
                    yield(Node(grid, x + direction.x, y + direction.y, steps - 1))
                }
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (x != other.x) return false
        if (y != other.y) return false
        if (steps != other.steps) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + steps
        return result
    }

    private companion object {
        private val DIRECTIONS = listOf(
            Vector2(-1, 0),
            Vector2(1, 0),
            Vector2(0, -1),
            Vector2(0, 1),
        )
    }
}
