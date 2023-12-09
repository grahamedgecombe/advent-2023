package com.grahamedgecombe.advent2023.day9

import com.grahamedgecombe.advent2023.Puzzle

object Day9 : Puzzle<List<List<Int>>>(9) {
    override fun parse(input: Sequence<String>): List<List<Int>> {
        return input.map { line ->
            line.splitToSequence(' ').map { n ->
                n.toInt()
            }.toList()
        }.toList()
    }

    override fun solvePart1(input: List<List<Int>>): Int {
        return input.sumOf(::getNext)
    }

    override fun solvePart2(input: List<List<Int>>): Int {
        return input.sumOf(::getPrevious)
    }

    private fun getNext(history: List<Int>): Int {
        var seq = history
        var next = seq.last()

        while (seq.any { it != 0 }) {
            seq = seq.zipWithNext { a, b -> b - a }.toList()
            next += seq.last()
        }

        return next
    }

    private fun getPrevious(history: List<Int>): Int {
        if (history.all { it == 0 }) {
            return 0
        }

        val diffs = history.zipWithNext { a, b -> b - a }.toList()
        return history.first() - getPrevious(diffs)
    }
}
