package com.grahamedgecombe.advent2023.day19

data class System(val workflows: Map<String, Workflow>, val parts: List<Part>) {
    fun sumAcceptedRatings(): Int {
        return parts.filter(::accepts).sumOf { part ->
            part.ratings.values.sum()
        }
    }

    fun countCombinations(): Long {
        val workflow = workflows["in"] ?: throw IllegalArgumentException()
        val constraints = Category.entries.associateWith { 1..4000 }
        return countCombinations(workflow.rules, constraints)
    }

    private fun countCombinations(rules: List<Rule>, constraints: Map<Category, IntRange>): Long {
        val rule = rules.firstOrNull() ?: throw IllegalArgumentException()

        return when (val condition = rule.condition) {
            Condition.Always -> countCombinations(rule.action, constraints)
            is Condition.Compare -> {
                val constraint = constraints[condition.category] ?: throw IllegalStateException()
                val operand = condition.operand

                val firstTrue = when (condition.operator) {
                    Operator.GreaterThan -> maxOf(constraint.first, operand + 1)
                    Operator.LessThan -> constraint.first
                }

                val lastTrue = when (condition.operator) {
                    Operator.GreaterThan -> constraint.last
                    Operator.LessThan -> minOf(constraint.last, operand - 1)
                }

                val firstFalse = when (condition.operator) {
                    Operator.GreaterThan -> constraint.first
                    Operator.LessThan -> maxOf(constraint.first, operand)
                }

                val lastFalse = when (condition.operator) {
                    Operator.GreaterThan -> minOf(constraint.last, operand)
                    Operator.LessThan -> constraint.last
                }

                var combinations = 0L

                if (firstTrue <= lastTrue) {
                    combinations += countCombinations(rule.action, constraints + Pair(condition.category, firstTrue..lastTrue))
                }

                if (firstFalse <= lastFalse) {
                    combinations += countCombinations(rules.subList(1, rules.size), constraints + Pair(condition.category, firstFalse..lastFalse))
                }

                combinations
            }
        }
    }

    private fun countCombinations(action: Action, constraints: Map<Category, IntRange>): Long {
        return when (action) {
            Action.Accept -> constraints.values.map { range -> range.last - range.first + 1L }.reduce(Long::times)
            Action.Reject -> 0
            is Action.Workflow -> {
                val workflow = workflows[action.name] ?: throw IllegalStateException()
                return countCombinations(workflow.rules, constraints)
            }
        }
    }

    private fun accepts(part: Part): Boolean {
        var workflow = workflows["in"] ?: throw IllegalArgumentException()

        while (true) {
            when (val action = workflow.evaluate(part)) {
                is Action.Workflow -> workflow = workflows[action.name] ?: throw IllegalStateException()
                is Action.Accept -> return true
                is Action.Reject -> return false
            }
        }
    }

    companion object {
        fun parse(input: Sequence<String>): System {
            val it = input.iterator()

            val workflows = mutableMapOf<String, Workflow>()
            while (it.hasNext()) {
                val s = it.next()
                if (s.isEmpty()) {
                    break
                }

                val workflow = Workflow.parse(s)
                workflows[workflow.name] = workflow
            }

            val parts = mutableListOf<Part>()
            while (it.hasNext()) {
                parts += Part.parse(it.next())
            }

            return System(workflows, parts)
        }
    }
}
