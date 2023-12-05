package com.grahamedgecombe.advent2023.day5

import com.grahamedgecombe.advent2023.UnsolvableException

data class Almanac(val seeds: List<Long>, val maps: Map<String, AlmanacMap>) {
    fun transform(seed: Long): Long {
        var type = "seed"
        var number = seed

        while (type != "location") {
            val map = maps[type] ?: throw UnsolvableException()
            type = map.destination
            number = map.transform(number)
        }

        return number
    }

    companion object {
        private const val SEEDS_PREFIX = "seeds: "
        private val MAP_PREFIX = Regex("([a-z]+)-to-([a-z]+) map:")

        fun parse(input: Sequence<String>): Almanac {
            val it = input.iterator()
            require(it.hasNext())

            val line = it.next()
            require(line.startsWith(SEEDS_PREFIX))

            val seeds = line.substring(SEEDS_PREFIX.length)
                .split(' ')
                .map(String::toLong)

            require(it.hasNext() && it.next().isEmpty())

            val maps = mutableMapOf<String, AlmanacMap>()

            while (it.hasNext()) {
                val match = MAP_PREFIX.matchEntire(it.next()) ?: throw IllegalArgumentException()
                val (_, source, dest) = match.groupValues

                val ranges = mutableListOf<RangeMap>()

                while (it.hasNext()) {
                    val s = it.next()
                    if (s.isEmpty()) {
                        break
                    }

                    ranges += RangeMap.parse(s)
                }

                maps[source] = AlmanacMap(dest, ranges)
            }

            return Almanac(seeds, maps)
        }
    }
}
