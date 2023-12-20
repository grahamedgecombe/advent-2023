package com.grahamedgecombe.advent2023.day20

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.UnsolvableException
import com.grahamedgecombe.advent2023.util.lcm

object Day20 : Puzzle<Circuit>(20) {
    override fun parse(input: Sequence<String>): Circuit {
        return Circuit.parse(input)
    }

    override fun solvePart1(input: Circuit): Int {
        val evaluator = CircuitEvaluator(input)
        var lowPulses = 0
        var highPulses = 0

        for (n in 1..1000) {
            evaluator.evaluate { pulse ->
                if (pulse.high) {
                    highPulses++
                } else {
                    lowPulses++
                }
            }
        }

        return lowPulses * highPulses
    }

    override fun solvePart2(input: Circuit): Long {
        val evaluator = CircuitEvaluator(input)
        val periods = mutableMapOf<String, Int>()

        val rxInput = input.modules.values.singleOrNull { "rx" in it.outputs } ?: throw UnsolvableException()
        val counters = input.modules.values.count { rxInput.name in it.outputs }

        var n = 1
        while (periods.size < counters) {
            evaluator.evaluate { pulse ->
                if (pulse.high && pulse.destination == rxInput.name) {
                    periods.putIfAbsent(pulse.source, n)
                }
            }
            n++
        }

        return periods.values.map(Int::toLong).reduce(::lcm)
    }
}
