package com.grahamedgecombe.advent2023.day8

enum class Direction {
    LEFT,
    RIGHT;

    companion object {
        fun parse(c: Char): Direction {
            return when (c) {
                'L' -> LEFT
                'R' -> RIGHT
                else -> throw IllegalArgumentException()
            }
        }
    }
}
