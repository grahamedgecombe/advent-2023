package com.grahamedgecombe.advent2023.day19

enum class Category {
    X, M, A, S;

    companion object {
        fun parse(c: Char): Category {
            return when (c) {
                'x' -> X
                'm' -> M
                'a' -> A
                's' -> S
                else -> throw IllegalArgumentException()
            }
        }
    }
}
