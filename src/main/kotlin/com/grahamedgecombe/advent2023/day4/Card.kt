package com.grahamedgecombe.advent2023.day4

data class Card(val id: Int, val winning: Set<Int>, val have: Set<Int>) {
    companion object {
        private const val PREFIX = "Card "
        private val WHITESPACE = Regex(" +")

        fun parse(s: String): Card {
            val (prefix, winning, have) = s.split(':', '|', limit = 3)

            if (!prefix.startsWith(PREFIX)) {
                throw IllegalArgumentException()
            }

            return Card(
                prefix.substring(PREFIX.length).trim().toInt(),
                winning.trim().split(WHITESPACE).map(String::toInt).toSet(),
                have.trim().split(WHITESPACE).map(String::toInt).toSet(),
            )
        }
    }
}
