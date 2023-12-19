package com.grahamedgecombe.advent2023.day19

data class System(val workflows: Map<String, Workflow>, val parts: List<Part>) {
    fun sumAcceptedRatings(): Int {
        return parts.filter(::accepts).sumOf { part ->
            part.ratings.values.sum()
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
