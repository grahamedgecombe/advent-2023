package com.grahamedgecombe.advent2023

import com.grahamedgecombe.advent2023.day1.Day1
import kotlin.time.Duration
import kotlin.time.measureTimedValue

fun main(args: Array<String>) {
    val puzzles = mutableListOf<Puzzle<*>>(
        Day1,
    )

    val day = args.firstOrNull()?.toIntOrNull()
    if (day != null) {
        puzzles.removeIf { puzzle -> puzzle.number != day }
    }

    for (puzzle in puzzles) {
        solve(puzzle)
    }
}

private fun <T> solve(puzzle: Puzzle<T>) {
    val input = measureTimedValue {
        puzzle.parse()
    }

    val solutionPart1 = measureTimedValue {
        puzzle.solvePart1(input.value)
    }
    printSolution(puzzle.number, 1, solutionPart1.value, solutionPart1.duration, input.duration)

    val solutionPart2 = measureTimedValue {
        puzzle.solvePart2(input.value)
    }
    if (solutionPart2.value != null) {
        printSolution(puzzle.number, 2, solutionPart2.value!!, solutionPart2.duration)
    }
}

private fun printSolution(day: Int, part: Int, solution: Any, duration: Duration, parseDuration: Duration? = null) {
    val suffix = if (parseDuration != null) {
        " + $parseDuration parsing"
    } else {
        ""
    }

    val value = solution.toString()
    if (value.contains('\n')) {
        println("Day $day Part $part: ($duration$suffix)")
        println(value.trim())
    } else {
        println("Day $day Part $part: $value ($duration$suffix)")
    }
}
