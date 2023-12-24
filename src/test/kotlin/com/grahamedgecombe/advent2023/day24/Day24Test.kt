package com.grahamedgecombe.advent2023.day24

import kotlin.test.Test
import kotlin.test.assertEquals

class Day24Test {
    @Test
    fun testPart1() {
        assertEquals(2, Day24.solvePart1(TEST_INPUT_1, 7L..27))
        assertEquals(21785, Day24.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(47, Day24.solvePart2(TEST_INPUT_1))
        assertEquals(554668916217145, Day24.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day24.parse("""
            19, 13, 30 @ -2,  1, -2
            18, 19, 22 @ -1, -1, -2
            20, 25, 34 @ -2, -2, -4
            12, 31, 28 @ -1, -2, -1
            20, 19, 15 @  1, -5, -3
        """.trimIndent())
        private val PROD_INPUT = Day24.parse()
    }
}
