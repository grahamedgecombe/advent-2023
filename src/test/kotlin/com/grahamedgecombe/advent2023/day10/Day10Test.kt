package com.grahamedgecombe.advent2023.day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun testPart1() {
        assertEquals(4, Day10.solvePart1(TEST_INPUT_1))
        assertEquals(8, Day10.solvePart1(TEST_INPUT_2))
        assertEquals(6768, Day10.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day10.parse("""
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent())
        private val TEST_INPUT_2 = Day10.parse("""
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent())
        private val PROD_INPUT = Day10.parse()
    }
}
