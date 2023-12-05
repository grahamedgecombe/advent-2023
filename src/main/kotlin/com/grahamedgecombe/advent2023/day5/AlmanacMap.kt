package com.grahamedgecombe.advent2023.day5

data class AlmanacMap(val destination: String, val maps: List<RangeMap>) {
    fun transform(n: Long): Long {
        for (map in maps) {
            if (n >= map.sourceStart && n <= map.sourceEnd) {
                return map.transform(n)
            }
        }

        return n
    }
}
