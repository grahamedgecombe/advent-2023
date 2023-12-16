package com.grahamedgecombe.advent2023.day16

import com.grahamedgecombe.advent2023.util.Vector2

enum class Direction(val vector: Vector2) {
    UP(Vector2(0, -1)),
    RIGHT(Vector2(1, 0)),
    DOWN(Vector2(0, 1)),
    LEFT(Vector2(-1, 0))
}
