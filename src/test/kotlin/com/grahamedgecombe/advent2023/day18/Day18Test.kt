package com.grahamedgecombe.advent2023.day18

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {
    @Test
    fun testPart1() {
        assertEquals(62, Day18.solvePart1(TEST_INPUT_1))
        assertEquals(50465, Day18.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(952408144115, Day18.solvePart2(TEST_INPUT_1))
        assertEquals(82712746433310, Day18.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day18.parse("""
            R 6 (#70c710)
            D 5 (#0dc571)
            L 2 (#5713f0)
            D 2 (#d2c081)
            R 2 (#59c680)
            D 2 (#411b91)
            L 5 (#8ceee2)
            U 2 (#caa173)
            L 1 (#1b58a2)
            U 2 (#caa171)
            R 2 (#7807d2)
            U 3 (#a77fa3)
            L 2 (#015232)
            U 2 (#7a21e3)
        """.trimIndent())
        private val PROD_INPUT = Day18.parse()
    }
}
