package com.grahamedgecombe.advent2023.day20

class CircuitEvaluator(private val circuit: Circuit) {
    private val flipFlopOn = circuit.modules.values.filter { it.type == Type.FLIP_FLOP }
        .associateWith { false }
        .toMutableMap()

    private val conjunctionInputs = circuit.modules.values.filter { it.type == Type.CONJUNCTION }
        .associateWith { conjunction ->
            circuit.modules.values.filter { conjunction.name in it.outputs }
                .map { it.name }
                .associateWith { false }
                .toMutableMap()
        }.toMap()

    fun evaluate(pulseFunc: (Pulse) -> Unit) {
        val pulses = ArrayDeque<Pulse>()
        pulses += Pulse("broadcaster", "broadcaster", false)

        while (true) {
            val pulse = pulses.removeFirstOrNull() ?: break
            pulseFunc(pulse)

            val module = circuit.modules[pulse.destination] ?: continue

            when (module.type) {
                Type.BROADCASTER -> {
                    for (output in module.outputs) {
                        pulses += Pulse(module.name, output, pulse.high)
                    }
                }

                Type.FLIP_FLOP -> {
                    if (!pulse.high) {
                        var on = flipFlopOn[module] ?: throw IllegalStateException()
                        on = !on
                        flipFlopOn[module] = on

                        for (output in module.outputs) {
                            pulses += Pulse(module.name, output, on)
                        }
                    }
                }

                Type.CONJUNCTION -> {
                    val inputs = conjunctionInputs[module] ?: throw IllegalStateException()
                    inputs[pulse.source] = pulse.high

                    val outputHigh = !inputs.values.all { it }

                    for (output in module.outputs) {
                        pulses += Pulse(module.name, output, outputHigh)
                    }
                }
            }
        }
    }
}
