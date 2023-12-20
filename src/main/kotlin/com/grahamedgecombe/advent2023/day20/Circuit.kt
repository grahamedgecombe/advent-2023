package com.grahamedgecombe.advent2023.day20

data class Circuit(val modules: Map<String, Module>) {
    fun evaluate(): Int {
        val pulses = ArrayDeque<Pulse>()

        val flipFlopOn = modules.values.filter { it.type == Type.FLIP_FLOP }
            .associateWith { false }
            .toMutableMap()

        val conjunctionInputs = modules.values.filter { it.type == Type.CONJUNCTION }
            .associateWith { conjunction ->
                modules.values.filter { conjunction.name in it.outputs }
                    .map { it.name }
                    .associateWith { false }
                    .toMutableMap()
            }
            .toMap()

        var lowPulses = 0
        var highPulses = 0

        for (n in 0 until 1000) {
            pulses += Pulse("broadcaster", "broadcaster", false)

            while (true) {
                val pulse = pulses.removeFirstOrNull() ?: break

                if (pulse.high) {
                    highPulses++
                } else {
                    lowPulses++
                }

                val module = modules[pulse.destination] ?: continue

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

        return lowPulses * highPulses
    }

    companion object {
        fun parse(input: Sequence<String>): Circuit {
            return Circuit(input.map(Module::parse).associateBy(Module::name).toMap())
        }
    }
}
