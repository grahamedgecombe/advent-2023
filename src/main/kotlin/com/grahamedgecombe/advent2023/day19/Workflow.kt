package com.grahamedgecombe.advent2023.day19

data class Workflow(val name: String, val rules: List<Rule>) {
    fun evaluate(part: Part): Action {
        for (rule in rules) {
            if (rule.condition.matches(part)) {
                return rule.action
            }
        }

        throw IllegalStateException()
    }

    companion object {
        private val REGEX = Regex("([a-z]+)\\{(.*)\\}")

        fun parse(s: String): Workflow {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (name, rules) = m.destructured
            return Workflow(name, rules.split(',').map(Rule::parse))
        }
    }
}
