package com.grahamedgecombe.advent2023.day3

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid
import com.grahamedgecombe.advent2023.util.Vector2

object Day3 : Puzzle<CharGrid>(3) {
    private val INT_REGEX = Regex("[0-9]+")

    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '.')
    }

    override fun solvePart1(input: CharGrid): Int {
        var sum = 0

        for (y in 0 until input.height) {
            for (match in INT_REGEX.findAll(input.getRow(y))) {
                if (input.getAdjacentSymbols(match.range, y).isNotEmpty()) {
                    sum += match.value.toInt()
                }
            }
        }

        return sum
    }

    override fun solvePart2(input: CharGrid): Int {
        val gears = mutableMapOf<Vector2, MutableList<Int>>()

        for (y in 0 until input.height) {
            for (match in INT_REGEX.findAll(input.getRow(y))) {
                val part = match.value.toInt()

                val positions = input.getAdjacentSymbols(match.range, y)
                    .filter { (_, symbol) -> symbol == '*' }
                    .map { (position, _) -> position }

                for (position in positions) {
                    val parts = gears.computeIfAbsent(position) { mutableListOf() }
                    parts += part
                }
            }
        }

        return gears.values
            .filter { parts -> parts.size == 2 }
            .sumOf { parts -> parts[0] * parts[1] }
    }

    private fun Char.isSymbol(): Boolean {
        return this != '.' && !this.isDigit()
    }

    private fun CharGrid.getAdjacentSymbols(xs: IntRange, y: Int): List<Pair<Vector2, Char>> {
        val adjacentSymbols = mutableListOf<Pair<Vector2, Char>>()

        fun checkTile(x: Int, y: Int) {
            val c = this[x, y]
            if (c.isSymbol()) {
                adjacentSymbols += Pair(Vector2(x, y), c)
            }
        }

        for (x in xs) {
            if (x == xs.first) {
                for (dy in -1..1) {
                    checkTile(x - 1, y + dy)
                }
            }

            checkTile(x, y - 1)
            checkTile(x, y + 1)

            if (x == xs.last) {
                for (dy in -1..1) {
                    checkTile(x + 1, y + dy)
                }
            }
        }

        return adjacentSymbols
    }
}
