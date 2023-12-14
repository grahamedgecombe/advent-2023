package com.grahamedgecombe.advent2023.day14

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

object Day14 : Puzzle<CharGrid>(14) {
    private const val CYCLES = 1000000000

    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        val grid = CharGrid(input)
        tilt(grid, 0, -1)
        return load(grid)
    }

    override fun solvePart2(input: CharGrid): Int {
        var grid = input
        val history = mutableMapOf<CharGrid, Int>()

        var cycle = 0
        while (cycle < CYCLES) {
            val lastSeen = history[grid]
            if (lastSeen != null) {
                val period = cycle - lastSeen
                val remaining = CYCLES - cycle

                cycle += remaining - (remaining % period)
                history.clear()
            }

            history[grid] = cycle

            grid = CharGrid(grid)
            tilt(grid, 0, -1)
            tilt(grid, -1, 0)
            tilt(grid, 0, 1)
            tilt(grid, 1, 0)

            cycle++
        }

        return load(grid)
    }

    private fun tilt(grid: CharGrid, dx: Int, dy: Int) {
        val xRange = if (dx < 0) 0 until grid.width else (grid.width - 1) downTo 0
        val yRange = if (dy < 0) 0 until grid.height else (grid.height - 1) downTo 0

        for (srcX in xRange) {
            for (srcY in yRange) {
                val tile = grid[srcX, srcY]
                if (tile != 'O') {
                    continue
                }

                val dest = roll(grid, srcX, srcY, dx, dy)
                if (srcX != dest.x || srcY != dest.y) {
                    grid[srcX, srcY] = '.'
                    grid[dest] = 'O'
                }
            }
        }
    }

    private fun roll(grid: CharGrid, srcX: Int, srcY: Int, dx: Int, dy: Int): Vector2 {
        var destX = srcX
        var destY = srcY
        while (grid[destX + dx, destY + dy] == '.') {
            destX += dx
            destY += dy
        }
        return Vector2(destX, destY)
    }

    private fun load(grid: CharGrid): Int {
        var sum = 0

        for (x in 0 until grid.width) {
            for (y in 0 until grid.height) {
                if (grid[x, y] == 'O') {
                    sum += grid.height - y
                }
            }
        }

        return sum
    }
}
