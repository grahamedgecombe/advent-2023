package com.grahamedgecombe.advent2023.day24

import com.grahamedgecombe.advent2023.Puzzle
import com.grahamedgecombe.advent2023.UnsolvableException
import io.ksmt.KContext
import io.ksmt.solver.KSolverStatus
import io.ksmt.solver.z3.KZ3Solver
import io.ksmt.utils.getValue
import kotlin.time.Duration

object Day24 : Puzzle<List<Hailstone>>(24) {
    override fun parse(input: Sequence<String>): List<Hailstone> {
        return input.map(Hailstone::parse).toList()
    }

    override fun solvePart1(input: List<Hailstone>): Int {
        return solvePart1(input, 200000000000000..400000000000000)
    }

    fun solvePart1(input: List<Hailstone>, testArea: LongRange): Int {
        var count = 0

        for ((i, a) in input.withIndex()) {
            for (b in input.subList(0, i)) {
                val ma = a.velocity.y.toDouble() / a.velocity.x
                val mb = b.velocity.y.toDouble() / b.velocity.x
                if (ma == mb) {
                    continue // parallel
                }

                val ca = a.position.y - a.velocity.y * (a.position.x.toDouble() / a.velocity.x)
                val cb = b.position.y - b.velocity.y * (b.position.x.toDouble() / b.velocity.x)

                val x = (cb - ca) / (ma - mb)
                if (x < testArea.first || x > testArea.last) {
                    continue // x outside test area
                }

                val y = ma * x + ca
                if (y < testArea.first || y > testArea.last) {
                    continue // y outside test area
                }

                val ta = (x - a.position.x) / a.velocity.x
                if (ta <= 0) {
                    continue // a didn't cross in the future
                }

                val tb = (x - b.position.x) / b.velocity.x
                if (tb <= 0) {
                    continue // b didn't cross in the future
                }

                count++
            }
        }

        return count
    }

    override fun solvePart2(input: List<Hailstone>): Long {
        with(KContext()) {
            KZ3Solver(this).use { solver ->
                val px by intSort
                val py by intSort
                val pz by intSort

                val vx by intSort
                val vy by intSort
                val vz by intSort

                for ((i, hailstone) in input.withIndex()) {
                    val t = mkConst("t$i", intSort)
                    solver.assert(t ge 0.expr)
                    solver.assert((px + vx * t) eq (hailstone.position.x.expr + hailstone.velocity.x.expr * t))
                    solver.assert((py + vy * t) eq (hailstone.position.y.expr + hailstone.velocity.y.expr * t))
                    solver.assert((pz + vz * t) eq (hailstone.position.z.expr + hailstone.velocity.z.expr * t))
                }

                val sat = solver.check(Duration.INFINITE)
                if (sat != KSolverStatus.SAT) {
                    throw UnsolvableException()
                }

                val model = solver.model()
                return model.eval(px + py + pz).toString().toLong()
            }
        }
    }
}
