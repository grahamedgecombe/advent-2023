package com.grahamedgecombe.advent2023.day2

data class Game(val id: Int, val sets: List<CubeSet>) {
    companion object {
        private const val PREFIX = "Game "

        fun parse(s: String): Game {
            val (prefix, sets) = s.split(": ", limit = 2)

            if (!prefix.startsWith(PREFIX)) {
                throw IllegalArgumentException()
            }

            return Game(
                prefix.substring(PREFIX.length).toInt(),
                sets.split("; ").map(CubeSet::parse)
            )
        }
    }
}
