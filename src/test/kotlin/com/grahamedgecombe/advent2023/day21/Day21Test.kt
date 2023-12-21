package com.grahamedgecombe.advent2023.day21

import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    @Test
    fun testPart1() {
        assertEquals(16, Day21.solvePart1(TEST_INPUT_1, 6))
        assertEquals(3814, Day21.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(16, Day21.solvePart2(TEST_INPUT_1, 6))
        assertEquals(50, Day21.solvePart2(TEST_INPUT_1, 10))
        assertEquals(1594, Day21.solvePart2(TEST_INPUT_1, 50))
        assertEquals(6536, Day21.solvePart2(TEST_INPUT_1, 100))
        assertEquals(167004, Day21.solvePart2(TEST_INPUT_1, 500))
        // these are quite slow but pass if uncommented:
        // assertEquals(668697, Day21.solvePart2(TEST_INPUT_1, 1000))
        // assertEquals(16733044, Day21.solvePart2(TEST_INPUT_1, 5000))
        assertEquals(632257949158206, Day21.solvePart2(PROD_INPUT))
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
