package com.grahamedgecombe.advent2023.day16

import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

class Contraption private constructor(private val grid: CharGrid) {
    fun countEnergised(): Int {
        return countEnergised(Vector2(-1, 0), Direction.RIGHT)
    }

    private fun countEnergised(position: Vector2, direction: Direction): Int {
        val seen = mutableSetOf<Pair<Vector2, Direction>>()
        explore(position, direction, seen)
        return seen.asSequence()
            .map(Pair<Vector2, Direction>::first)
            .distinct()
            .count()
    }

    fun countMaxEnergised(): Int {
        var max = 0

        for (x in 0 until grid.width) {
            max = maxOf(max, countEnergised(Vector2(x, -1), Direction.DOWN))
            max = maxOf(max, countEnergised(Vector2(x, grid.height), Direction.UP))
        }

        for (y in 0 until grid.height) {
            max = maxOf(max, countEnergised(Vector2(-1, y), Direction.RIGHT))
            max = maxOf(max, countEnergised(Vector2(grid.width, y), Direction.LEFT))
        }

        return max
    }

    private fun explore(origin: Vector2, direction: Direction, seen: MutableSet<Pair<Vector2, Direction>>) {
        val position = origin + direction.vector
        if (position.x !in 0 until grid.width || position.y !in 0 until grid.height) {
            return
        }

        if (!seen.add(Pair(position, direction))) {
            return
        }

        val tile = grid[position]
        when (tile) {
            '.' -> explore(position, direction, seen)
            '/' -> when (direction) {
                Direction.UP -> explore(position, Direction.RIGHT, seen)
                Direction.RIGHT -> explore(position, Direction.UP, seen)
                Direction.DOWN -> explore(position, Direction.LEFT, seen)
                Direction.LEFT -> explore(position, Direction.DOWN, seen)
            }
            '\\' -> when (direction) {
                Direction.UP -> explore(position, Direction.LEFT, seen)
                Direction.RIGHT -> explore(position, Direction.DOWN, seen)
                Direction.DOWN -> explore(position, Direction.RIGHT, seen)
                Direction.LEFT -> explore(position, Direction.UP, seen)
            }
            '-' -> when (direction) {
                Direction.LEFT, Direction.RIGHT -> explore(position, direction, seen)
                else -> {
                    explore(position, Direction.LEFT, seen)
                    explore(position, Direction.RIGHT, seen)
                }
            }
            '|' -> when (direction) {
                Direction.UP, Direction.DOWN -> explore(position, direction, seen)
                else -> {
                    explore(position, Direction.UP, seen)
                    explore(position, Direction.DOWN, seen)
                }
            }
            else -> throw IllegalArgumentException()
        }
    }

    companion object {
        fun parse(input: List<String>): Contraption {
            return Contraption(CharGrid.parse(input, '.'))
        }
    }
}
