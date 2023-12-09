package com.grahamedgecombe.advent2023.day9

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9Test {
    @Test
    fun testPart1() {
        assertEquals(114, Day9.solvePart1(TEST_INPUT_1))
        assertEquals(1789635132, Day9.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(2, Day9.solvePart2(TEST_INPUT_1))
        assertEquals(913, Day9.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day9.parse("""
            0 3 6 9 12 15
            1 3 6 10 15 21
            10 13 16 21 30 45
        """.trimIndent())
        private val PROD_INPUT = Day9.parse()
    }
}
