package com.grahamedgecombe.advent2023.util

import kotlin.math.abs

data class Vector2(val x: Int, val y: Int) {
    val manhattanDistance: Int
        get() = abs(x) + abs(y)

    operator fun plus(o: Vector2): Vector2 {
        return Vector2(x + o.x, y + o.y)
    }

    operator fun minus(o: Vector2): Vector2 {
        return Vector2(x - o.x, y - o.y)
    }

    operator fun unaryMinus(): Vector2 {
        return Vector2(-x, -y)
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}
