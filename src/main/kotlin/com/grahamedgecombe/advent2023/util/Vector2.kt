package com.grahamedgecombe.advent2023.util

data class Vector2(val x: Int, val y: Int) {
    operator fun plus(o: Vector2): Vector2 {
        return Vector2(x + o.x, y + o.y)
    }

    operator fun unaryMinus(): Vector2 {
        return Vector2(-x, -y)
    }
}
