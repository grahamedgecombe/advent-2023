package com.grahamedgecombe.advent2023.day22

import com.grahamedgecombe.advent2023.util.Vector3

data class Brick(val start: Vector3, val end: Vector3) {
    val xMin = minOf(start.x, end.x)
    val xMax = maxOf(start.x, end.x)

    val yMin = minOf(start.y, end.y)
    val yMax = maxOf(start.y, end.y)

    val zMin = minOf(start.z, end.z)
    val zMax = maxOf(start.z, end.z)

    fun intersects(other: Brick): Boolean {
        return xMax >= other.xMin && xMin <= other.xMax &&
            yMax >= other.yMin && yMin <= other.yMax &&
            zMax >= other.zMin && zMin <= other.zMax
    }

    operator fun plus(v: Vector3): Brick {
        return Brick(start + v, end + v)
    }

    override fun toString(): String {
        return "${start.x},${start.y},${start.z}~${end.x},${end.y},${end.z}"
    }

    companion object {
        private val REGEX = Regex("([0-9]+),([0-9]+),([0-9]+)~([0-9]+),([0-9]+),([0-9]+)")

        fun parse(s: String): Brick {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (x1, y1, z1, x2, y2, z2) = m.destructured

            return Brick(
                Vector3(x1.toInt(), y1.toInt(), z1.toInt()),
                Vector3(x2.toInt(), y2.toInt(), z2.toInt())
            )
        }
    }
}
