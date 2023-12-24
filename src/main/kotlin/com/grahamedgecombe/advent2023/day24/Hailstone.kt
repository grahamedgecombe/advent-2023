package com.grahamedgecombe.advent2023.day24

import com.grahamedgecombe.advent2023.util.Vector3l

data class Hailstone(
    val position: Vector3l,
    val velocity: Vector3l,
) {
    override fun toString(): String {
        return "${position.x}, ${position.y}, ${position.z} @ ${velocity.x}, ${velocity.y}, ${velocity.z}"
    }

    companion object {
        private val REGEX = Regex("([0-9]+),\\s*([0-9]+),\\s*([0-9]+)\\s*@\\s*(-?[0-9]+),\\s*(-?[0-9]+),\\s*(-?[0-9]+)")

        fun parse(s: String): Hailstone {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (px, py, pz, vx, vy, vz) = m.destructured

            val position = Vector3l(px.toLong(), py.toLong(), pz.toLong())
            val velocity = Vector3l(vx.toLong(), vy.toLong(), vz.toLong())

            return Hailstone(position, velocity)
        }
    }
}
