package com.grahamedgecombe.advent2023.day23

import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {
    @Test
    fun testPart1() {
        assertEquals(94, Day23.solvePart1(TEST_INPUT_1))
        assertEquals(1966, Day23.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(154, Day23.solvePart2(TEST_INPUT_1))
        assertEquals(6286, Day23.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day23.parse("""
            #.#####################
            #.......#########...###
            #######.#########.#.###
            ###.....#.>.>.###.#.###
            ###v#####.#v#.###.#.###
            ###.>...#.#.#.....#...#
            ###v###.#.#.#########.#
            ###...#.#.#.......#...#
            #####.#.#.#######.#.###
            #.....#.#.#.......#...#
            #.#####.#.#.#########v#
            #.#...#...#...###...>.#
            #.#.#v#######v###.###v#
            #...#.>.#...>.>.#.###.#
            #####v#.#.###v#.#.###.#
            #.....#...#...#.#.#...#
            #.#########.###.#.#.###
            #...###...#...#...#.###
            ###.###.#.###v#####v###
            #...#...#.#.>.>.#.>.###
            #.###.###.#.###.#.#v###
            #.....###...###...#...#
            #####################.#
        """.trimIndent())
        private val PROD_INPUT = Day23.parse()
    }
}
