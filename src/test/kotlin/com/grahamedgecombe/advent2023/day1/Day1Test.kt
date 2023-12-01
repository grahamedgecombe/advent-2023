package com.grahamedgecombe.advent2023.day1

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1Test {
    @Test
    fun testPart1() {
        assertEquals(142, Day1.solvePart1(TEST_INPUT_1))
        assertEquals(54940, Day1.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(281, Day1.solvePart2(TEST_INPUT_2))
        assertEquals(54208, Day1.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day1.parse("""
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent())
        private val TEST_INPUT_2 = Day1.parse("""
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent())
        private val PROD_INPUT = Day1.parse()
    }

}
