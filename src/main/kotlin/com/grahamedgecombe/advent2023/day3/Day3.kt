package com.grahamedgecombe.advent2023.day3

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.CharGrid

object Day3 : Puzzle<CharGrid>(3) {
    private val INT_REGEX = Regex("[0-9]+")

    override fun parse(input: Sequence<String>): CharGrid {
        return CharGrid.parse(input.toList(), '.')
    }

    override fun solvePart1(input: CharGrid): Int {
        var sum = 0

        for (y in 0 until input.height) {
            for (match in INT_REGEX.findAll(input.getRow(y))) {
                if (input.isAdjacentToSymbol(match.range, y)) {
                    sum += match.value.toInt()
                }
            }
        }

        return sum
    }

    private fun Char.isSymbol(): Boolean {
        return this != '.' && !this.isDigit()
    }

    private fun CharGrid.isAdjacentToSymbol(xs: IntRange, y: Int): Boolean {
        for (x in xs) {
            if (x == xs.first) {
                for (dy in -1..1) {
                    if (this[x - 1, y + dy].isSymbol()) {
                        return true
                    }
                }
            }

            if (this[x, y - 1].isSymbol() || this[x, y + 1].isSymbol()) {
                return true
            }

            if (x == xs.last) {
                for (dy in -1..1) {
                    if (this[x + 1, y + dy].isSymbol()) {
                        return true
                    }
                }
            }
        }

        return false
    }
}
