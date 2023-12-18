package com.grahamedgecombe.advent2023.day18

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.Vector2
import kotlin.math.abs

object Day18 : Puzzle<List<Instruction>>(18) {
    override fun parse(input: Sequence<String>): List<Instruction> {
        return input.map(Instruction::parse).toList()
    }

    override fun solvePart1(input: List<Instruction>): Long {
        return solve(input, Instruction::direction1, Instruction::length1)
    }

    override fun solvePart2(input: List<Instruction>): Long {
        return solve(input, Instruction::direction2, Instruction::length2)
    }

    private fun solve(
        input: List<Instruction>,
        directionFunc: (Instruction) -> Direction,
        lengthFunc: (Instruction) -> Int,
    ): Long {
        val vertices = mutableListOf<Vector2>()

        var position = Vector2.ZERO
        vertices += position

        for (instruction in input) {
            position += directionFunc(instruction).vector * lengthFunc(instruction)
            vertices += position
        }

        var sum = 0L
        val perimeter = 1 + input.sumOf { instruction ->
            lengthFunc(instruction).toLong()
        }

        for ((i, a) in vertices.withIndex()) {
            val b = vertices[(i + 1) % vertices.size]
            sum += (a.x.toLong() * b.y) - (b.x.toLong() * a.y)
        }

        /*
         * See:
         *
         *   https://en.wikipedia.org/wiki/Shoelace_formula
         *   https://en.wikipedia.org/wiki/Pick%27s_theorem
         *
         * A = i + b/2 - 1 = 1/2 * |S| where:
         *
         *   A is the area
         *   i is the number of integer points inside the polygon
         *   b is the number of integer points on its perimeter
         *   S is the summation in the shoelace formula
         *
         * We know S and b, and want to calculate i + b, so re-arrange to:
         *
         *   i = |S|/2 + 1 - b/2
         *
         * and then:
         *
         *   i + b = (|S| + b)/2 + 1
         */

        return (abs(sum) + perimeter) / 2 + 1
    }
}
