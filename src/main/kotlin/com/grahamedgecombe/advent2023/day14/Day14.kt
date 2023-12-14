package com.grahamedgecombe.advent2023.day14

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid

object Day14 : Puzzle<CharGrid>(14) {
    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        val grid = CharGrid(input)
        tilt(grid)
        return load(grid)
    }

    private fun tilt(grid: CharGrid) {
        for (x in 0 until grid.width) {
            for (srcY in 0 until grid.height) {
                val tile = grid[x, srcY]
                if (tile != 'O') {
                    continue
                }

                val destY = roll(grid, x, srcY)
                if (srcY != destY) {
                    grid[x, srcY] = '.'
                    grid[x, destY] = 'O'
                }
            }
        }
    }

    private fun roll(grid: CharGrid, x: Int, srcY: Int): Int {
        var destY = srcY
        while (grid[x, destY - 1] == '.') {
            destY--
        }
        return destY
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
