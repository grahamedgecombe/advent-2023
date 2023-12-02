package com.grahamedgecombe.advent2023.day2

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Test {
    @Test
    fun testPart1() {
        assertEquals(8, Day2.solvePart1(TEST_INPUT_1))
        assertEquals(2348, Day2.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(2286, Day2.solvePart2(TEST_INPUT_1))
        assertEquals(76008, Day2.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day2.parse("""
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent())
        private val PROD_INPUT = Day2.parse()
    }
}
