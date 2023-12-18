package com.grahamedgecombe.advent2023.day18

data class Instruction(val direction: Direction, val length: Int, val color: Int) {
    companion object {
        private val REGEX = Regex("([UDLR]) ([0-9]+) \\(#([0-9a-f]{6})\\)")

        fun parse(s: String): Instruction {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (direction, length, color) = m.destructured
            return Instruction(Direction.parse(direction.single()), length.toInt(), color.toInt(16))
        }
    }
}
