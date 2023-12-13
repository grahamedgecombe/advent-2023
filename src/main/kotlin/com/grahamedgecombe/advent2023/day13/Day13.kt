package com.grahamedgecombe.advent2023.day13

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid

object Day13 : Puzzle<List<CharGrid>>(13) {
    override fun parse(input: Sequence<String>): List<CharGrid> {
        val mirrors = mutableListOf<CharGrid>()
        val it = input.iterator()

        while (true) {
            val mirror = mutableListOf<String>()

            while (it.hasNext()) {
                val line = it.next()
                if (line.isEmpty()) {
                    break
                }
                mirror += line
            }

            if (mirror.isNotEmpty()) {
                mirrors += CharGrid.parse(mirror, '.')
            } else {
                break
            }
        }

        return mirrors
    }

    override fun solvePart1(input: List<CharGrid>): Int {
        return input.sumOf { mirror ->
            val cols = reflectVertical(mirror)
            val rows = reflectHorizontal(mirror)
            (rows * 100) + cols
        }
    }

    private fun reflectVertical(mirror: CharGrid): Int {
        for (x in 1 until mirror.width) {
            if (reflectVertical(mirror, x)) {
                return x
            }
        }

        return 0
    }

    private fun reflectVertical(mirror: CharGrid, x: Int): Boolean {
        for (dx in 0 until x) {
            val x0 = x - dx - 1
            val x1 = x + dx

            if (x1 >= mirror.width) {
                break
            }

            for (y in 0 until mirror.height) {
                if (mirror[x0, y] != mirror[x1, y]) {
                    return false
                }
            }
        }

        return true
    }

    private fun reflectHorizontal(mirror: CharGrid): Int {
        for (y in 1 until mirror.height) {
            if (reflectHorizontal(mirror, y)) {
                return y
            }
        }

        return 0
    }

    private fun reflectHorizontal(mirror: CharGrid, y: Int): Boolean {
        for (dy in 0 until y) {
            val y0 = y - dy - 1
            val y1 = y + dy

            if (y1 >= mirror.height) {
                break
            }

            for (x in 0 until mirror.width) {
                if (mirror[x, y0] != mirror[x, y1]) {
                    return false
                }
            }
        }

        return true
    }
}
