package com.grahamedgecombe.advent2023.util

class CharGrid private constructor(
    val width: Int,
    val height: Int,
    private val tiles: CharArray,
    private val default: Char,
) {
    constructor(width: Int, height: Int, default: Char) : this(width, height, CharArray(width * height), default) {
        tiles.fill(default)
    }

    constructor(other: CharGrid) : this(other.width, other.height, other.tiles.copyOf(), other.default)

    fun getRow(y: Int): String {
        if (y !in 0 until height) {
            throw IndexOutOfBoundsException()
        }

        return String(CharArray(width) { x ->
            tiles[y * width + x]
        })
    }

    operator fun get(v: Vector2): Char {
        return get(v.x, v.y)
    }

    operator fun get(x: Int, y: Int): Char {
        if (x !in 0 until width || y !in 0 until height) {
            return default
        }

        return tiles[y * width + x]
    }

    operator fun set(v: Vector2, tile: Char) {
        set(v.x, v.y, tile)
    }

    operator fun set(x: Int, y: Int, tile: Char) {
        require(x in 0 until width && y in 0 until height)
        tiles[y * width + x] = tile
    }

    fun find(tile: Char): Vector2? {
        var index = 0
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (tiles[index++] == tile) {
                    return Vector2(x, y)
                }
            }
        }

        return null
    }

    companion object {
        fun parse(input: List<String>, default: Char): CharGrid {
            val height = input.size
            if (height == 0) {
                return CharGrid(0, 0, CharArray(0), default)
            }

            val width = input.maxOf(String::length)
            val tiles = CharArray(width * height)

            var index = 0
            for (row in input) {
                for (x in 0 until width) {
                    tiles[index++] = if (x < row.length) {
                        row[x]
                    } else {
                        default
                    }
                }
            }

            return CharGrid(width, height, tiles, default)
        }
    }
}
