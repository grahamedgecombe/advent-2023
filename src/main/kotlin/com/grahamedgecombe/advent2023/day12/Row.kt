package com.grahamedgecombe.advent2023.day12

class Row(private val springs: List<Spring>, private val damaged: List<Int>) {
    private val cache = mutableMapOf<State, Long>()

    fun countArrangements(): Long {
        return countArrangements(springs, damaged, false)
    }

    fun unfold(): Row {
        val unfoldedSprings = mutableListOf<Spring>()
        val unfoldedDamaged = mutableListOf<Int>()

        for (i in 0 until 5) {
            unfoldedSprings += springs
            if (i != 4) {
                unfoldedSprings += Spring.UNKNOWN
            }

            unfoldedDamaged += damaged
        }

        return Row(unfoldedSprings, unfoldedDamaged)
    }

    private fun countArrangements(
        springs: List<Spring>,
        damaged: List<Int>,
        prevDamaged: Boolean,
    ): Long = cache.getOrPut(State(springs, damaged, prevDamaged)) {
        // base case
        val spring = springs.firstOrNull()
        if (spring == null) {
            return@getOrPut if (damaged.isEmpty()) 1 else 0
        }

        var count = 0L

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

        return@getOrPut count
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
