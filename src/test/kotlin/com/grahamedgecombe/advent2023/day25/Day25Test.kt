package com.grahamedgecombe.advent2023.day25

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {
    @Test
    fun testPart1() {
        assertEquals(54, Day25.solvePart1(TEST_INPUT_1))
        assertEquals(533628, Day25.solvePart1(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day25.parse("""
            jqt: rhn xhk nvd
            rsh: frs pzl lsr
            xhk: hfx
            cmg: qnr nvd lhk bvb
            rhn: xhk bvb hfx
            bvb: xhk hfx
            pzl: lsr hfx nvd
            qnr: nvd
            ntq: jqt hfx bvb xhk
            nvd: lhk
            lsr: lhk
            rzs: qnr cmg lsr rsh
            frs: qnr lhk lsr
        """.trimIndent())
        private val PROD_INPUT = Day25.parse()
    }
}
