package com.grahamedgecombe.advent2023.day19

sealed interface Operator {
    data object GreaterThan : Operator {
        override fun evaluate(a: Int, b: Int): Boolean {
            return a > b
        }
    }

    data object LessThan : Operator {
        override fun evaluate(a: Int, b: Int): Boolean {
            return a < b
        }
    }

    fun evaluate(a: Int, b: Int): Boolean

    companion object {
        fun parse(c: Char): Operator {
            return when (c) {
                '>' -> GreaterThan
                '<' -> LessThan
                else -> throw IllegalArgumentException()
            }
        }
    }
}
