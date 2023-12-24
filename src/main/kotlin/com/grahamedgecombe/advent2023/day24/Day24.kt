package com.grahamedgecombe.advent2023.day24

import com.grahamedgecombe.advent2023.Puzzle

object Day24 : Puzzle<List<Hailstone>>(24) {
    override fun parse(input: Sequence<String>): List<Hailstone> {
        return input.map(Hailstone::parse).toList()
    }

    override fun solvePart1(input: List<Hailstone>): Int {
        return solvePart1(input, 200000000000000..400000000000000)
    }

    fun solvePart1(input: List<Hailstone>, testArea: LongRange): Int {
        var count = 0

        for ((i, a) in input.withIndex()) {
            for (b in input.subList(0, i)) {
                val ma = a.velocity.y.toDouble() / a.velocity.x
                val mb = b.velocity.y.toDouble() / b.velocity.x
                if (ma == mb) {
                    continue // parallel
                }

                val ca = a.position.y - a.velocity.y * (a.position.x.toDouble() / a.velocity.x)
                val cb = b.position.y - b.velocity.y * (b.position.x.toDouble() / b.velocity.x)

                val x = (cb - ca) / (ma - mb)
                if (x < testArea.first || x > testArea.last) {
                    continue // x outside test area
                }

                val y = ma * x + ca
                if (y < testArea.first || y > testArea.last) {
                    continue // y outside test area
                }

                val ta = (x - a.position.x) / a.velocity.x
                if (ta <= 0) {
                    continue // a didn't cross in the future
                }

                val tb = (x - b.position.x) / b.velocity.x
                if (tb <= 0) {
                    continue // b didn't cross in the future
                }

                count++
            }
        }

        return count
    }
}
