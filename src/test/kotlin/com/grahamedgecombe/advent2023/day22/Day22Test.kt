package com.grahamedgecombe.advent2023.day22

import kotlin.test.Test
import kotlin.test.assertEquals

class Day22Test {
    @Test
    fun testPart1() {
        assertEquals(5, Day22.solvePart1(TEST_INPUT_1))
        assertEquals(418, Day22.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day22.parse("""
            1,0,1~1,2,1
            0,0,2~2,0,2
            0,2,3~2,2,3
            0,0,4~0,2,4
            2,0,5~2,2,5
            0,1,6~2,1,6
            1,1,8~1,1,9
        """.trimIndent())
        private val PROD_INPUT = Day22.parse()
    }
}
