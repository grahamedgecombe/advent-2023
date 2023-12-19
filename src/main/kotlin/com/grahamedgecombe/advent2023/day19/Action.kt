package com.grahamedgecombe.advent2023.day19

sealed interface Action {
    data class Workflow(val name: String) : Action
    data object Reject : Action
    data object Accept : Action

    companion object {
        fun parse(s: String): Action {
            return when (s) {
                "A" -> Accept
                "R" -> Reject
                else -> Workflow(s)
            }
        }
    }
}
