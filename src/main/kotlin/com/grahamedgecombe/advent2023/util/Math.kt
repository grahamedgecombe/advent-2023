package com.grahamedgecombe.advent2023.util

import kotlin.math.abs

fun lcm(a: Long, b: Long): Long {
    return abs(a * b) / gcd(a, b)
}

tailrec fun gcd(a: Long, b: Long): Long {
    if (b == 0L) {
        return a
    }

    return gcd(b, a % b)
}
