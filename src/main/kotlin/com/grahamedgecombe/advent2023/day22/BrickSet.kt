package com.grahamedgecombe.advent2023.day22

import com.grahamedgecombe.advent2023.util.Vector3

class BrickSet(
    val bricks: List<Brick>,
    val supportedBy: Map<Int, Set<Int>>,
    val supports: Map<Int, Set<Int>>,
) {
    fun chainReaction(start: Int): Int {
        val falling = mutableSetOf<Int>()
        falling += start

        val queue = ArrayDeque<Int>()
        queue += start

        while (true) {
            val brick = queue.removeFirstOrNull() ?: break
            for (brickAbove in supports[brick]!!) {
                val otherBricksBelow = supportedBy[brickAbove]!! - falling
                if (otherBricksBelow.isEmpty() && falling.add(brickAbove)) {
                    queue += brickAbove
                }
            }
        }

        return falling.size - 1
    }

    companion object {
        fun parse(input: Sequence<String>): BrickSet {
            val bricks = input.map(Brick::parse).toMutableList()
            fall(bricks)

            val supportedBy = mutableMapOf<Int, MutableSet<Int>>()
            val supports = mutableMapOf<Int, MutableSet<Int>>()

            for (i in bricks.indices) {
                supportedBy[i] = mutableSetOf()
                supports[i] = mutableSetOf()
            }

            for ((i, brick) in bricks.withIndex()) {
                for ((j, supporter) in bricks.subList(0, i).withIndex()) {
                    if (supporter.supports(brick)) {
                        supportedBy[i]!! += j
                        supports[j]!! += i
                    }
                }
            }

            return BrickSet(bricks, supportedBy, supports)
        }

        private fun fall(bricks: MutableList<Brick>) {
            bricks.sortBy(Brick::zMin)

            for (i in bricks.indices) {
                while (true) {
                    val next = bricks[i] + Vector3(0, 0, -1)
                    if (next.zMin <= 0 || bricks.subList(0, i).any { other -> next.intersects(other) }) {
                        break
                    }
                    bricks[i] = next
                }
            }
        }
    }
}
