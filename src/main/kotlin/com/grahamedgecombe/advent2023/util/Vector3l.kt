package com.grahamedgecombe.advent2023.util

data class Vector3l(val x: Long, val y: Long, val z: Long) {
    override fun toString(): String {
        return "($x, $y, $z)"
    }
}
