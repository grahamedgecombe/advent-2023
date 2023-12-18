package com.grahamedgecombe.advent2023.day18

data class Instruction(
    val direction1: Direction,
    val length1: Int,
    val direction2: Direction,
    val length2: Int,
) {
    companion object {
        private val REGEX = Regex("([UDLR]) ([0-9]+) \\(#([0-9a-f]{6})\\)")

        fun parse(s: String): Instruction {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (direction, length, color) = m.destructured

            val direction1 = Direction.parse(direction.single())
            val length1 = length.toInt()

            val direction2 = Direction.parse(color.last())
            val length2 = color.substring(0, 5).toInt(16)

            return Instruction(direction1, length1, direction2, length2)
        }
    }
}
