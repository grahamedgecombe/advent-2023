package com.grahamedgecombe.advent2023.day2

import com.grahamedgecombe.advent2023.Puzzle

object Day2 : Puzzle<List<Game>>(2) {
    override fun parse(input: Sequence<String>): List<Game> {
        return input.map(Game::parse).toList()
    }

    override fun solvePart1(input: List<Game>): Int {
        return input.filter { game ->
            game.sets.all { set ->
                set.red <= 12 && set.green <= 13 && set.blue <= 14
            }
        }.sumOf(Game::id)
    }
}
