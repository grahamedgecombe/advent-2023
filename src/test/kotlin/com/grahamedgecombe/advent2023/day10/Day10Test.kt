package com.grahamedgecombe.advent2023.day10

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {
    @Test
    fun testPart1() {
        assertEquals(4, Day10.solvePart1(TEST_INPUT_1))
        assertEquals(8, Day10.solvePart1(TEST_INPUT_2))
        assertEquals(6768, Day10.solvePart1(PROD_INPUT))
    }

    @Test
    fun testPart2() {
        assertEquals(4, Day10.solvePart2(TEST_INPUT_3))
        assertEquals(4, Day10.solvePart2(TEST_INPUT_4))
        assertEquals(8, Day10.solvePart2(TEST_INPUT_5))
        assertEquals(10, Day10.solvePart2(TEST_INPUT_6))
        assertEquals(351, Day10.solvePart2(PROD_INPUT))
    }

    private companion object {
        private val TEST_INPUT_1 = Day10.parse("""
            .....
            .S-7.
            .|.|.
            .L-J.
            .....
        """.trimIndent())
        private val TEST_INPUT_2 = Day10.parse("""
            ..F7.
            .FJ|.
            SJ.L7
            |F--J
            LJ...
        """.trimIndent())
        private val TEST_INPUT_3 = Day10.parse("""
            ...........
            .S-------7.
            .|F-----7|.
            .||.....||.
            .||.....||.
            .|L-7.F-J|.
            .|..|.|..|.
            .L--J.L--J.
            ...........
        """.trimIndent())
        private val TEST_INPUT_4 = Day10.parse("""
            ..........
            .S------7.
            .|F----7|.
            .||....||.
            .||....||.
            .|L-7F-J|.
            .|..||..|.
            .L--JL--J.
            ..........
        """.trimIndent())
        private val TEST_INPUT_5 = Day10.parse("""
            .F----7F7F7F7F-7....
            .|F--7||||||||FJ....
            .||.FJ||||||||L7....
            FJL7L7LJLJ||LJ.L-7..
            L--J.L7...LJS7F-7L7.
            ....F-J..F7FJ|L7L7L7
            ....L7.F7||L7|.L7L7|
            .....|FJLJ|FJ|F7|.LJ
            ....FJL-7.||.||||...
            ....L---J.LJ.LJLJ...
        """.trimIndent())
        private val TEST_INPUT_6 = Day10.parse("""
            FF7FSF7F7F7F7F7F---7
            L|LJ||||||||||||F--J
            FL-7LJLJ||||||LJL-77
            F--JF--7||LJLJ7F7FJ-
            L---JF-JLJ.||-FJLJJ7
            |F|F-JF---7F7-L7L|7|
            |FFJF7L7F-JF7|JL---7
            7-L-JL7||F7|L7F-7F7|
            L.L7LFJ|||||FJL7||LJ
            L7JLJL-JLJLJL--JLJ.L
        """.trimIndent())
        private val PROD_INPUT = Day10.parse()
    }
}
