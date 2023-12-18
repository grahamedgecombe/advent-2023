package com.grahamedgecombe.advent2023.day18

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.Vector2

object Day18 : Puzzle<List<Instruction>>(18) {
    override fun parse(input: Sequence<String>): List<Instruction> {
        return input.map(Instruction::parse).toList()
    }

    override fun solvePart1(input: List<Instruction>): Int {
        val terrain = mutableSetOf<Vector2>()

        var position = Vector2.ZERO
        terrain += position

        for (instruction in input) {
            for (n in 0 until instruction.length) {
                position += instruction.direction.vector
                terrain += position
            }
        }

        val xMin = terrain.minOf(Vector2::x)
        val xMax = terrain.maxOf(Vector2::x)

        val yMin = terrain.minOf(Vector2::y)
        val yMax = terrain.maxOf(Vector2::y)

        var area = 0

        for (y in yMin..yMax) {
            var x = xMin
            var interior = false

            while (x <= xMax) {
                if (Vector2(x, y) in terrain) {
                    val topLeft = Vector2(x, y - 1) in terrain
                    val bottomLeft = Vector2(x, y + 1) in terrain

                    do {
                        x++
                        area++
                    } while (Vector2(x, y) in terrain)

                    val topRight = Vector2(x - 1, y - 1) in terrain
                    val bottomRight = Vector2(x - 1, y + 1) in terrain

                    if (topLeft && topRight && !bottomLeft && !bottomRight) {
                        // #.#
                        // ###
                        // ...
                    } else if (!topLeft && !topRight && bottomLeft && bottomRight) {
                        // ...
                        // ###
                        // #.#
                    } else {
                        interior = !interior
                    }
                }

                if (x++ > xMax) {
                    continue
                }

                if (interior) {
                    area++
                }
            }
        }

        return area
    }
}
