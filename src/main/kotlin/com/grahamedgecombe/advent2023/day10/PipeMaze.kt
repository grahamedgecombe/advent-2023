package com.grahamedgecombe.advent2023.day10

import com.grahamedgecombe.advent2023.UnsolvableException
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

class PipeMaze private constructor(
    private val grid: CharGrid,
    private val start: Vector2,
) {
    private fun loop(): Set<Vector2> {
        val visited = mutableSetOf<Vector2>()
        var position = start

        outer@while (true) {
            val tile = grid[position]
            visited += position

            var startAdjacent = false

            for (direction in getDirections(tile)) {
                val next = position + direction
                if (next !in visited) {
                    position = next
                    continue@outer
                } else if (next == start) {
                    startAdjacent = true
                }
            }

            if (startAdjacent) {
                break
            } else {
                throw UnsolvableException()
            }
        }

        return visited
    }

    fun countSteps(): Int {
        return (loop().size + 1) / 2
    }

    fun countEnclosed(): Int {
        val loopOnlyGrid = CharGrid(grid.width, grid.height, '.')
        for (v in loop()) {
            loopOnlyGrid[v] = grid[v]
        }

        var count = 0
        for (y in 0 until loopOnlyGrid.height) {
            count += countEnclosedRow(loopOnlyGrid, y)
        }
        return count
    }

    private fun countEnclosedRow(grid: CharGrid, y: Int): Int {
        var count = 0
        var enclosed = false

        var x = 0
        while (x < grid.width) {
            val tile = grid[x, y]

            if (tile == '|') {
                enclosed = !enclosed
            } else if (tile != '.') {
                var last: Char
                do {
                    last = grid[++x, y]
                } while (last == '-')

                if (crossesLoop(tile, last)) {
                    enclosed = !enclosed
                }
            } else if (enclosed) { // tile == '.'
                count++
            }

            x++
        }

        return count
    }

    private fun crossesLoop(start: Char, end: Char): Boolean {
        return when {
            start == 'F' && end == 'J' -> true
            start == 'L' && end == '7' -> true
            else -> false
        }
    }

    companion object {
        private val NORTH = Vector2(0, -1)
        private val EAST = Vector2(1, 0)
        private val SOUTH = Vector2(0, 1)
        private val WEST = Vector2(-1, 0)

        private val ALL_DIRECTIONS = setOf(NORTH, EAST, SOUTH, WEST)
        private val DIRECTIONS = mapOf(
            '|' to setOf(NORTH, SOUTH),
            '-' to setOf(EAST, WEST),
            'L' to setOf(NORTH, EAST),
            'J' to setOf(NORTH, WEST),
            '7' to setOf(SOUTH, WEST),
            'F' to setOf(SOUTH, EAST),
        )

        fun parse(input: List<String>): PipeMaze {
            val grid = CharGrid.parse(input.toList(), '.')

            val start = grid.find('S') ?: throw UnsolvableException()
            grid[start] = getStartTile(grid, start)

            return PipeMaze(grid, start)
        }

        private fun getDirections(tile: Char): Set<Vector2> {
            return DIRECTIONS.getOrDefault(tile, emptySet())
        }

        private fun getStartTile(grid: CharGrid, position: Vector2): Char {
            val directions = mutableSetOf<Vector2>()

            for (direction in ALL_DIRECTIONS) {
                if (isConnected(grid, position, direction)) {
                    directions += direction
                }
            }

            return DIRECTIONS.entries.singleOrNull { (_, v) -> v == directions }?.key
                ?: throw UnsolvableException()
        }

        private fun isConnected(grid: CharGrid, position: Vector2, direction: Vector2): Boolean {
            return -direction in getDirections(grid[position + direction])
        }
    }
}
