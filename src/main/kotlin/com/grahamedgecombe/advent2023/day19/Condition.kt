package com.grahamedgecombe.advent2023.day19

sealed interface Condition {
    data class Compare(val category: Category, val operator: Operator, val operand: Int) : Condition {
        override fun matches(part: Part): Boolean {
            return operator.evaluate(part.ratings[category]!!, operand)
        }
    }

    data object Always : Condition {
        override fun matches(part: Part): Boolean {
            return true
        }
    }

    fun matches(part: Part) : Boolean

    companion object {
        private val REGEX = Regex("([xmas])([<>])([0-9]+)")

        fun parse(s: String): Condition {
            val m = REGEX.matchEntire(s) ?: throw IllegalArgumentException()
            val (category, operator, operand) = m.destructured
            return Compare(Category.parse(category.single()), Operator.parse(operator.single()), operand.toInt())
        }
    }
}
