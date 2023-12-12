package com.grahamedgecombe.advent2023.day12

import kotlin.test.Test
import kotlin.test.assertEquals

class Day12Test {
    @Test
    fun testPart1() {
        assertEquals(21, Day12.solvePart1(TEST_INPUT_1))
        assertEquals(7694, Day12.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(525152, Day12.solvePart2(TEST_INPUT_1))
        assertEquals(5071883216318, Day12.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day12.parse("""
            ???.### 1,1,3
            .??..??...?##. 1,1,3
            ?#?#?#?#?#?#?#? 1,3,1,6
            ????.#...#... 4,1,1
            ????.######..#####. 1,6,5
            ?###???????? 3,2,1
        """.trimIndent())
        private val PROD_INPUT = Day12.parse()
    }
}
