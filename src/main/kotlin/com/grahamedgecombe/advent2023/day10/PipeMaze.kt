package com.grahamedgecombe.advent2023.day10

import com.grahamedgecombe.advent2023.UnsolvableException
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

class PipeMaze private constructor(
    private val grid: CharGrid,
    private val start: Vector2,
) {
    fun countSteps(): Int {
        val visited = mutableSetOf<Vector2>()
        var position = start

        outer@while (true) {
            val tile = grid[position]
            visited += position

            var startAdjacent = false

            for (direction in getDirections(tile)) {
                if (isConnected(grid, position, direction)) {
                    val next = position + direction
                    if (next !in visited) {
                        position = next
                        continue@outer
                    } else if (next == start) {
                        startAdjacent = true
                    }
                }
            }

            if (startAdjacent) {
                break
            } else {
                throw UnsolvableException()
            }
        }

        return (visited.size + 1) / 2
    }

    private fun isConnected(position: Vector2, direction: Vector2): Boolean {
        return -direction in getDirections(grid[position + direction])
    }

    companion object {
        private val NORTH = Vector2(0, -1)
        private val EAST = Vector2(1, 0)
        private val SOUTH = Vector2(0, 1)
        private val WEST = Vector2(-1, 0)

        private val DIRECTIONS = mapOf(
            '|' to setOf(NORTH, SOUTH),
            '-' to setOf(EAST, WEST),
            'L' to setOf(NORTH, EAST),
            'J' to setOf(NORTH, WEST),
            '7' to setOf(SOUTH, WEST),
            'F' to setOf(SOUTH, EAST),
            'S' to setOf(NORTH, EAST, SOUTH, WEST),
        )

        fun parse(input: List<String>): PipeMaze {
            val grid = CharGrid.parse(input.toList(), '.')
            val start = grid.find('S') ?: throw UnsolvableException()
            return PipeMaze(grid, start)
        }

        private fun getDirections(tile: Char): Set<Vector2> {
            return DIRECTIONS.getOrDefault(tile, emptySet())
        }
    }
}
