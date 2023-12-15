package com.grahamedgecombe.advent2023.day15

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {
    @Test
    fun testPart1() {
        assertEquals(1320, Day15.solvePart1(TEST_INPUT_1))
        assertEquals(512283, Day15.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day15.parse("""
            rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7
        """.trimIndent())
        private val PROD_INPUT = Day15.parse()
    }
}
