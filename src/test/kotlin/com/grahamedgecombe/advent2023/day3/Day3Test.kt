package com.grahamedgecombe.advent2023.day3

import kotlin.test.Test
import kotlin.test.assertEquals

class Day3Test {
    @Test
    fun testPart1() {
        assertEquals(4361, Day3.solvePart1(TEST_INPUT_1))
        assertEquals(544664, Day3.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day3.parse("""
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent())
        private val PROD_INPUT = Day3.parse()
    }
}
