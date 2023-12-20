package com.grahamedgecombe.advent2023.day20

import kotlin.test.Test
import kotlin.test.assertEquals

class Day20Test {
    @Test
    fun testPart1() {
        assertEquals(32000000, Day20.solvePart1(TEST_INPUT_1))
        assertEquals(11687500, Day20.solvePart1(TEST_INPUT_2))
        assertEquals(861743850, Day20.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day20.parse("""
            broadcaster -> a, b, c
            %a -> b
            %b -> c
            %c -> inv
            &inv -> a
        """.trimIndent())
        private val TEST_INPUT_2 = Day20.parse("""
            broadcaster -> a
            %a -> inv, con
            &inv -> b
            %b -> con
            &con -> output
        """.trimIndent())
        private val PROD_INPUT = Day20.parse()
    }
}
