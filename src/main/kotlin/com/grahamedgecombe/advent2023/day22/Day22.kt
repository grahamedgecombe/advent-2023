package com.grahamedgecombe.advent2023.day22

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.Vector3

object Day22 : Puzzle<List<Brick>>(22) {
    override fun parse(input: Sequence<String>): List<Brick> {
        return input.map(Brick::parse).toList()
    }

    override fun solvePart1(input: List<Brick>): Int {
        val bricks = input.toMutableList()
        bricks.sortBy(Brick::zMin)
        fall(bricks)

        var safe = 0

        for (i in bricks.indices) {
            val newBricks = ArrayList(bricks)
            newBricks.removeAt(i)

            if (fall(newBricks).isEmpty()) {
                safe++
            }
        }

        return safe
    }

    override fun solvePart2(input: List<Brick>): Int {
        val bricks = input.toMutableList()
        bricks.sortBy(Brick::zMin)
        fall(bricks)

        var sum = 0

        for (i in bricks.indices) {
            val newBricks = ArrayList(bricks)
            newBricks.removeAt(i)

            sum += fall(newBricks).size
        }

        return sum
    }

    private fun fall(bricks: MutableList<Brick>): Set<Int> {
        val falling = mutableSetOf<Int>()

        for (i in bricks.indices) {
            while (true) {
                val next = bricks[i] + Vector3(0, 0, -1)
                if (next.zMin <= 0 || bricks.withIndex().any { (j, other) -> i != j && next.intersects(other) }) {
                    break
                }
                bricks[i] = next
                falling += i
            }
        }

        return falling
    }
}
