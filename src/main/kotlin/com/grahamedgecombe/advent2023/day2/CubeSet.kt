package com.grahamedgecombe.advent2023.day2

data class CubeSet(val red: Int, val green: Int, val blue: Int) {
    companion object {
        fun parse(s: String): CubeSet {
            var red = 0
            var green = 0
            var blue = 0

            for (cube in s.split(", ")) {
                val (number, colour) = cube.split(' ', limit = 2)

                val n = number.toInt()

                when (colour) {
                    "red" -> red = n
                    "green" -> green = n
                    "blue" -> blue = n
                    else -> throw IllegalArgumentException()
                }
            }

            return CubeSet(red, green, blue)
        }
    }
}
