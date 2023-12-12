package com.grahamedgecombe.advent2023.day12

class Row(private val springs: List<Spring>, private val damaged: List<Int>) {
    fun countArrangements(): Int {
        return countArrangements(springs, damaged, false)
    }

    private fun countArrangements(springs: List<Spring>, damaged: List<Int>, prevDamaged: Boolean): Int {
        // base case
        val spring = springs.firstOrNull()
        if (spring == null) {
            return if (damaged.isEmpty()) 1 else 0
        }

        var count = 0

        // recursive case 1: operational
        if (spring != Spring.DAMAGED) {
            count += countArrangements(springs.subList(1, springs.size), damaged, false)
        }

        // recursive case 2: damaged
        if (!prevDamaged) {
            val len = damaged.firstOrNull()
            if (len != null && len <= springs.size && springs.subList(0, len).none { it == Spring.OPERATIONAL }) {
                count += countArrangements(springs.subList(len, springs.size), damaged.subList(1, damaged.size), true)
            }
        }

        return count
    }

    companion object {
        fun parse(s: String): Row {
            val (springs, damaged) = s.split(' ', limit = 2)

            return Row(
                springs.map(Spring::parse),
                damaged.splitToSequence(',')
                    .map(String::toInt)
                    .toList(),
            )
        }
    }
}
