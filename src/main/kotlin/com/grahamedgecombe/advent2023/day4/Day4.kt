package com.grahamedgecombe.advent2023.day4

import com.grahamedgecombe.advent2023.Puzzle

object Day4 : Puzzle<List<Card>>(4) {
    override fun parse(input: Sequence<String>): List<Card> {
        return input.map(Card::parse).toList()
    }

    override fun solvePart1(input: List<Card>): Int {
        return input.sumOf { card ->
            1 shl ((card.winning intersect card.have).size - 1)
        }
    }
}
