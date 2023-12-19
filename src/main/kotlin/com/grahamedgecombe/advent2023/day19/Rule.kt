package com.grahamedgecombe.advent2023.day19

data class Rule(val condition: Condition, val action: Action) {
    companion object {
        fun parse(s: String): Rule {
            val parts = s.split(':', limit = 2)
            return if (parts.size == 1) {
                Rule(Condition.Always, Action.parse(parts[0]))
            } else {
                Rule(Condition.parse(parts[0]), Action.parse(parts[1]))
            }
        }
    }
}
