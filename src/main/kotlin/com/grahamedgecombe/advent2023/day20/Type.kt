package com.grahamedgecombe.advent2023.day20

enum class Type {
    BROADCASTER,
    FLIP_FLOP,
    CONJUNCTION;

    fun toPrefix(): String {
        return when (this) {
            BROADCASTER -> ""
            FLIP_FLOP -> "%"
            CONJUNCTION -> "&"
        }
    }

    companion object {
        fun parse(c: Char): Type {
            return when (c) {
                '%' -> FLIP_FLOP
                '&' -> CONJUNCTION
                else -> throw IllegalArgumentException()
            }
        }
    }
}
