package com.grahamedgecombe.advent2023.day14

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun testPart1() {
        assertEquals(136, Day14.solvePart1(TEST_INPUT_1))
        assertEquals(110565, Day14.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day14.parse("""
            O....#....
            O.OO#....#
            .....##...
            OO.#O....O
            .O.....O#.
            O.#..O.#.#
            ..O..#O..O
            .......O..
            #....###..
            #OO..#....
        """.trimIndent())
        private val PROD_INPUT = Day14.parse()
    }
}
