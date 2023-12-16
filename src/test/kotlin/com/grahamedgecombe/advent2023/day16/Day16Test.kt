package com.grahamedgecombe.advent2023.day16

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {
    @Test
    fun testPart1() {
        assertEquals(46, Day16.solvePart1(TEST_INPUT_1))
        assertEquals(8125, Day16.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(51, Day16.solvePart2(TEST_INPUT_1))
        assertEquals(8489, Day16.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day16.parse("""
            .|...\....
            |.-.\.....
            .....|-...
            ........|.
            ..........
            .........\
            ..../.\\..
            .-.-/..|..
            .|....-|.\
            ..//.|....
        """.trimIndent())
        private val PROD_INPUT = Day16.parse()
    }
}
