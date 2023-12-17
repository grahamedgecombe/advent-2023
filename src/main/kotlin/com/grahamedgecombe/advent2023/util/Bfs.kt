package com.grahamedgecombe.advent2023.util

object Bfs {
    interface Node<T : Node<T>> {
        val isGoal: Boolean
        val neighbours: Sequence<T>
    }

    fun <T : Node<T>> search(vararg roots: T): Sequence<List<T>> {
        return search(roots.toSet())
    }

    fun <T : Node<T>> search(roots: Set<T>): Sequence<List<T>> {
        val queue = ArrayDeque<T>()
        val parents = mutableMapOf<T, T>()

        queue.addAll(roots)

        return sequence {
            while (true) {
                val current = queue.removeFirstOrNull() ?: break
                if (current.isGoal) {
                    val path = mutableListOf<T>()

                    var node: T? = current
                    while (node != null) {
                        path += node
                        node = parents[node]
                    }

                    path.reverse()
                    yield(path)
                }

                for (neighbour in current.neighbours) {
                    if (parents.containsKey(neighbour) || neighbour in roots) {
                        continue
                    }

                    queue.addLast(neighbour)
                    parents[neighbour] = current
                }
            }
        }
    }
}
