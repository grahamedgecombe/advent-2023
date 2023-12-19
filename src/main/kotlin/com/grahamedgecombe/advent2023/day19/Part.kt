package com.grahamedgecombe.advent2023.day19

data class Part(val ratings: Map<Category, Int>) {
    companion object {
        fun parse(line: String): Part {
            require(line.startsWith('{') && line.endsWith('}'))

            val ratings = mutableMapOf<Category, Int>()
            for (s in line.substring(1, line.length - 1).split(',')) {
                val (k, v) = s.split('=', limit = 2)
                ratings[Category.parse(k.single())] = v.toInt()
            }
            return Part(ratings)
        }
    }
}
