package com.grahamedgecombe.advent2023.day16

import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

class Contraption private constructor(private val grid: CharGrid) {
    fun countEnergised(): Int {
        val seen = mutableSetOf<Pair<Vector2, Direction>>()
        explore(Vector2(-1, 0), Direction.RIGHT, seen)
        return seen.asSequence()
            .map(Pair<Vector2, Direction>::first)
            .distinct()
            .count()
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
