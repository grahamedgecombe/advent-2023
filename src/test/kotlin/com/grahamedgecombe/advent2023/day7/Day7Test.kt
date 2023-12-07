package com.grahamedgecombe.advent2023.day7

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7Test {
    @Test
    fun testPart1() {
        assertEquals(6440, Day7.solvePart1(TEST_INPUT_1))
        assertEquals(250370104, Day7.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(5905, Day7.solvePart2(TEST_INPUT_1))
        assertEquals(251735672, Day7.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day7.parse("""
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent())
        private val PROD_INPUT = Day7.parse()
    }
}
