package com.grahamedgecombe.advent2023.day6

data class Race(val duration: Int, val record: Int) {
    fun countWins(): Int {
        var count = 0

        for (velocity in 0..duration) {
            val distance = (duration - velocity) * velocity
            if (distance > record) {
                count++
            }
        }

        return count
    }
}
