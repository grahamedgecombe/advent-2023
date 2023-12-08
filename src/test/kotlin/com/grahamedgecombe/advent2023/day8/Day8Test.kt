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

    @Test
    fun testPart2() {
        assertEquals(6, Day8.solvePart2(TEST_INPUT_3))
        assertEquals(15746133679061, Day8.solvePart2(PROD_INPUT))
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
        private val TEST_INPUT_3 = Day8.parse("""
            LR

            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent())
        private val PROD_INPUT = Day8.parse()
    }
}
