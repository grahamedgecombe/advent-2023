package com.grahamedgecombe.advent2023.day21

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

object Day21 : Puzzle<CharGrid>(21) {
    private const val STEPS_PART_2 = 26501365
    private val DIRECTIONS = listOf(
        Vector2(-1, 0),
        Vector2(1, 0),
        Vector2(0, -1),
        Vector2(0, 1),
    )

    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '#')
    }

    override fun solvePart1(input: CharGrid): Int {
        return solvePart1(input, 64)
    }

    fun solvePart1(input: CharGrid, steps: Int): Int {
        return bfs(input, steps).values.count { d -> (d and 1) == (steps and 1) }
    }

    override fun solvePart2(input: CharGrid): Long {
        require(input.width == input.height)

        val x0 = STEPS_PART_2 % input.width
        val x1 = x0 + input.width
        val x2 = x1 + input.width

        val distances = bfs(input, x2).values

        val y0 = distances.count { d -> (d and 1) == (x0 and 1) && d <= x0 }.toLong()
        val y1 = distances.count { d -> (d and 1) == (x1 and 1) && d <= x1 }.toLong()
        val y2 = distances.count { d -> (d and 1) == (x2 and 1) && d <= x2 }.toLong()

        val d1 = y1 - y0
        val d2 = (y2 - y1) - d1

        val x = STEPS_PART_2.toLong() / input.width
        return y0 + (d1 * x) + (d2 * (x * (x - 1) / 2))
    }

    fun solvePart2(input: CharGrid, steps: Int): Int {
        return bfs(input, steps).values.count { d -> (d and 1) == (steps and 1) && d <= steps }
    }

    private fun bfs(grid: CharGrid, steps: Int): Map<Vector2, Int> {
        val queue = ArrayDeque<Vector2>()
        val visited = mutableSetOf<Vector2>()
        val distances = mutableMapOf<Vector2, Int>()

        val start = grid.find('S') ?: throw IllegalArgumentException()
        queue += start
        visited += start
        distances[start] = 0

        while (true) {
            val u = queue.removeFirstOrNull() ?: break

            for (d in DIRECTIONS) {
                val v = u + d
                if (v in visited || grid[v.x umod grid.width, v.y umod grid.height] == '#') {
                    continue
                }

                val next = distances[u]!! + 1
                if (next <= steps) {
                    queue += v
                    visited += v
                    distances[v] = next
                }
            }
        }

        return distances
    }

    private infix fun Int.umod(b: Int): Int {
        val m = this % b
        return if (m < 0) {
            m + b
        } else {
            m
        }
    }
}
