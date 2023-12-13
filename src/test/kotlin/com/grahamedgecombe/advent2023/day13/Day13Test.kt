package com.grahamedgecombe.advent2023.day13

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun testPart1() {
        assertEquals(405, Day13.solvePart1(TEST_INPUT_1))
        assertEquals(30487, Day13.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day13.parse("""
            #.##..##.
            ..#.##.#.
            ##......#
            ##......#
            ..#.##.#.
            ..##..##.
            #.#.##.#.

            #...##..#
            #....#..#
            ..##..###
            #####.##.
            #####.##.
            ..##..###
            #....#..#
        """.trimIndent())
        private val PROD_INPUT = Day13.parse()
    }
}
