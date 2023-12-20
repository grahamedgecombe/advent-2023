package com.grahamedgecombe.advent2023.day20

data class Circuit(val modules: Map<String, Module>) {
    fun toDot(): String {
        val builder = StringBuilder()
        builder.append("digraph circuit {\n")

        for (module in modules.values) {
            builder.append("  ${module.name} [label=\"${module.type.toPrefix()}${module.name}\"];\n")

            for (output in module.outputs) {
                builder.append("  ${module.name} -> $output;\n")
            }
        }

        builder.append("}\n")
        return builder.toString()
    }

    companion object {
        fun parse(input: Sequence<String>): Circuit {
            return Circuit(input.map(Module::parse).associateBy(Module::name).toMap())
        }
    }
}
