package com.grahamedgecombe.advent2023.day25

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.util.DisjointSet
import com.grahamedgecombe.advent2023.util.ForestDisjointSet

object Day25 : Puzzle<Set<Pair<String, String>>>(25) {
    override fun parse(input: Sequence<String>): Set<Pair<String, String>> {
        val edges = mutableSetOf<Pair<String, String>>()

        for (line in input) {
            val (u, vs) = line.split(": ", limit = 2)
            for (v in vs.split(' ')) {
                if (Pair(v, u) !in edges) {
                    edges += Pair(u, v)
                }
            }
        }

        return edges
    }

    override fun solvePart1(input: Set<Pair<String, String>>): Int {
        while (true) {
            val disjointSet = ForestDisjointSet<String>()
            for ((u, v) in input) {
                disjointSet.add(u)
                disjointSet.add(v)
            }

            val remainingEdges = input.toMutableSet()
            while (disjointSet.partitions > 2) {
                val (u, v) = remainingEdges.removeRandom()
                disjointSet.union(disjointSet[u]!!, disjointSet[v]!!)
            }

            val cutEdges = input.count { (u, v) -> disjointSet[u]!! != disjointSet[v]!! }
            if (cutEdges == 3) {
                return disjointSet.toSet()
                    .map(DisjointSet.Partition<String>::count)
                    .reduce(Int::times)
            }
        }
    }

    private fun <T> MutableSet<T>.removeRandom(): T {
        val item = random()
        remove(item)
        return item
    }
}
