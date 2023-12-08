package com.grahamedgecombe.advent2023.day8

import com.grahamedgecombe.advent2023.UnsolvableException

data class Input(
    val directions: List<Direction>,
    val nodes: Map<String, Node>,
) {
    fun solve(start: String, end: (String) -> Boolean): Int {
        var node = start
        var count = 0

        while (!end(node)) {
            val direction = directions[count % directions.size]
            val (left, right) = nodes[node] ?: throw UnsolvableException()

            node = when (direction) {
                Direction.LEFT -> left
                Direction.RIGHT -> right
            }
            count++
        }

        return count
    }

    companion object {
        private val NODE_REGEX = Regex("([A-Z0-9]{3}) = \\(([A-Z0-9]{3}), ([A-Z0-9]{3})\\)")

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
