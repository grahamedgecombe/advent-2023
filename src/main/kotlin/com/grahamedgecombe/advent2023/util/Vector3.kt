package com.grahamedgecombe.advent2023.util

data class Vector3(val x: Int, val y: Int, val z: Int) {
    operator fun plus(v: Vector3): Vector3 {
        return Vector3(x + v.x, y + v.y, z + v.z)
    }

    override fun toString(): String {
        return "($x, $y, $z)"
    }
}
