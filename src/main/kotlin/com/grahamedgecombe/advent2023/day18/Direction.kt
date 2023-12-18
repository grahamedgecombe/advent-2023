package com.grahamedgecombe.advent2023.day18

import com.grahamedgecombe.advent2023.util.Vector2

enum class Direction(val vector: Vector2) {
    UP(Vector2(0, -1)),
    DOWN(Vector2(0, 1)),
    LEFT(Vector2(-1, 0)),
    RIGHT(Vector2(1, 0));

    companion object {
        fun parse(c: Char): Direction {
            return when (c) {
                'U', '3' -> UP
                'D', '1' -> DOWN
                'L', '2' -> LEFT
                'R', '0' -> RIGHT
                else -> throw IllegalArgumentException()
            }
        }
    }
}
