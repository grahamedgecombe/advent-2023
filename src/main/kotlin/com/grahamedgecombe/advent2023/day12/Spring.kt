package com.grahamedgecombe.advent2023.day12

enum class Spring {
    OPERATIONAL,
    DAMAGED,
    UNKNOWN;

    companion object {
        fun parse(c: Char): Spring {
            return when (c) {
                '.' -> OPERATIONAL
                '#' -> DAMAGED
                '?' -> UNKNOWN
                else -> throw IllegalArgumentException()
            }
        }
    }
}
