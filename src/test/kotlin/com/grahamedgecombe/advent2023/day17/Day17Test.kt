package com.grahamedgecombe.advent2023.day17

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17Test {
    @Test
    fun testPart1() {
        assertEquals(102, Day17.solvePart1(TEST_INPUT_1))
        assertEquals(847, Day17.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day17.parse("""
            2413432311323
            3215453535623
            3255245654254
            3446585845452
            4546657867536
            1438598798454
            4457876987766
            3637877979653
            4654967986887
            4564679986453
            1224686865563
            2546548887735
            4322674655533
        """.trimIndent())
        private val PROD_INPUT = Day17.parse()
    }
}
