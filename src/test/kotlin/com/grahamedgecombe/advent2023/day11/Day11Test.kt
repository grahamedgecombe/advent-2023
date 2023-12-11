package com.grahamedgecombe.advent2023.day11

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {
    @Test
    fun testPart1() {
        assertEquals(374, Day11.solvePart1(TEST_INPUT_1))
        assertEquals(9795148, Day11.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(1030, Day11.solve(TEST_INPUT_1, 10))
        assertEquals(8410, Day11.solve(TEST_INPUT_1, 100))
        assertEquals(650672493820, Day11.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day11.parse("""
            ...#......
            .......#..
            #.........
            ..........
            ......#...
            .#........
            .........#
            ..........
            .......#..
            #...#.....
        """.trimIndent())
        private val PROD_INPUT = Day11.parse()
    }
}
