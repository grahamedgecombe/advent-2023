package com.grahamedgecombe.advent2023.day8

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8Test {
    @Test
    fun testPart1() {
        assertEquals(2, Day8.solvePart1(TEST_INPUT_1))
        assertEquals(6, Day8.solvePart1(TEST_INPUT_2))
        assertEquals(17873, Day8.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day8.parse("""
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent())
        private val TEST_INPUT_2 = Day8.parse("""
            LLR

            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent())
        private val PROD_INPUT = Day8.parse()
    }
}
