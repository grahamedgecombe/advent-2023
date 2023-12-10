package com.grahamedgecombe.advent2023.util

class CharGrid private constructor(
    val width: Int,
    val height: Int,
    private val rows: List<String>,
    private val default: Char,
) {
    fun getRow(y: Int): String {
        if (y !in 0 until height) {
            throw IndexOutOfBoundsException()
        }

        return rows[y]
    }

    operator fun get(v: Vector2): Char {
        return get(v.x, v.y)
    }

    operator fun get(x: Int, y: Int): Char {
        if (y !in 0 until height) {
            return default
        }

        val row = rows[y]
        if (x !in row.indices) {
            return default
        }

        return row[x]
    }

    fun find(tile: Char): Vector2? {
        for ((y, row) in rows.withIndex()) {
            val x = row.indexOf(tile)
            if (x != -1) {
                return Vector2(x, y)
            }
        }

        return null
    }

    companion object {
        fun parse(input: List<String>, default: Char): CharGrid {
            val height = input.size
            if (height == 0) {
                return CharGrid(0, 0, emptyList(), default)
            }

            val width = input.maxOf(String::length)

            return CharGrid(width, height, input, default)
        }
    }
}
