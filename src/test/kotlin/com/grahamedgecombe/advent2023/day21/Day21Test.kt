package com.grahamedgecombe.advent2023.day21

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    @Test
    fun testPart1() {
        assertEquals(16, Day21.solvePart1(TEST_INPUT_1, 6))
        assertEquals(3814, Day21.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day21.parse("""
            ...........
            .....###.#.
            .###.##..#.
            ..#.#...#..
            ....#.#....
            .##..S####.
            .##..#...#.
            .......##..
            .##.#.####.
            .##..##.##.
            ...........
        """.trimIndent())
        private val PROD_INPUT = Day21.parse()
    }
}
