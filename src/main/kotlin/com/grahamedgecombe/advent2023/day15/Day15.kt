package com.grahamedgecombe.advent2023.day15

import com.grahamedgecombe.advent2023.Puzzle

object Day15 : Puzzle<List<String>>(15) {
    private val STEP = Regex("([a-z]+)([=-])([0-9])?")

    override fun parse(input: Sequence<String>): List<String> {
        val line = input.singleOrNull() ?: throw IllegalArgumentException()
        return line.split(',')
    }

    override fun solvePart1(input: List<String>): Int {
        return input.sumOf(::hash)
    }

    override fun solvePart2(input: List<String>): Int {
        val boxes = Array(256) { LinkedHashMap<String, Int>() }

        for (step in input) {
            val m = STEP.matchEntire(step) ?: throw IllegalArgumentException()
            val (label, op, len) = m.destructured

            val box = boxes[hash(label)]

            when (op) {
                "=" -> box[label] = len.toInt()
                "-" -> box.remove(label)
                else -> throw IllegalArgumentException()
            }
        }

        var power = 0
        for ((i, box) in boxes.withIndex()) {
            for ((j, len) in box.values.withIndex()) {
                power += (i + 1) * (j + 1) * len
            }
        }
        return power
    }

    private fun hash(s: String): Int {
        var sum = 0
        for (c in s) {
            sum = ((sum + c.code) * 17) and 0xFF
        }
        return sum
    }
}
