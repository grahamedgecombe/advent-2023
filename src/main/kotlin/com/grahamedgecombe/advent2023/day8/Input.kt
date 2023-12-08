package com.grahamedgecombe.advent2023.day8

data class Input(
    val directions: List<Direction>,
    val nodes: Map<String, Node>,
) {
    companion object {
        private val NODE_REGEX = Regex("([A-Z]{3}) = \\(([A-Z]{3}), ([A-Z]{3})\\)")

        fun parse(input: Sequence<String>): Input {
            val it = input.iterator()
            require(it.hasNext())

            val directions = it.next().map(Direction::parse)

            require(it.hasNext() && it.next().isEmpty())

            val nodes = mutableMapOf<String, Node>()

            while (it.hasNext()) {
                val match = NODE_REGEX.matchEntire(it.next()) ?: continue
                val (node, left, right) = match.destructured

                nodes[node] = Node(left, right)
            }

            return Input(directions, nodes)
        }
    }
}
