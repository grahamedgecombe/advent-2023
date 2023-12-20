package com.grahamedgecombe.advent2023.day20

data class Module(val type: Type, val name: String, val outputs: List<String>) {
    companion object {
        fun parse(s: String): Module {
            val (src, outputString) = s.split(" -> ", limit = 2)
            val outputs = outputString.split(", ")

            return if (src == "broadcaster") {
                Module(Type.BROADCASTER, "broadcaster", outputs)
            } else {
                require(src.isNotEmpty())

                val type = Type.parse(src.first())
                val name = src.substring(1, src.length)

                Module(type, name, outputs)
            }
        }
    }
}
