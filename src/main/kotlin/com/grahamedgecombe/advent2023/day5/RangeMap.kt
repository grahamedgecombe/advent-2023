package com.grahamedgecombe.advent2023.day5

data class RangeMap(val destStart: Long, val sourceStart: Long, val length: Long) {
    val sourceEnd = sourceStart + length - 1
    val destEnd = destStart + length - 1

    fun transform(n: Long): Long {
        return n - sourceStart + destStart
    }

    companion object {
        fun parse(s: String): RangeMap {
            val (destStart, sourceStart, length) = s.split(' ', limit = 3)
            return RangeMap(destStart.toLong(), sourceStart.toLong(), length.toLong())
        }
    }
}
