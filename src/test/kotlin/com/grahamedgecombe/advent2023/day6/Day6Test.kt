package com.grahamedgecombe.advent2023.day6

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6Test {
    @Test
    fun testPart1() {
        assertEquals(288, Day6.solvePart1(TEST_INPUT_1))
        assertEquals(4568778, Day6.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(71503, Day6.solvePart2(TEST_INPUT_1))
        assertEquals(28973936, Day6.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day6.parse("""
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent())
        private val PROD_INPUT = Day6.parse()
    }
}
