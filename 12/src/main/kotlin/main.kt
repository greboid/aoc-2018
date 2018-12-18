import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().filter {
            !it.isEmpty()
        }
        val initialState = input.first().split(": ").last().trim()
        val rules = input.drop(1).map { it -> it.split("=>").map { it.trim() } }.map { Pair(it[0], it[1].toCharArray()[0]) }.toMap()

        val sums = mutableListOf<Int>()

        var offset = 0
        var state = initialState
        for (step in 0..200) {
            sums.add(state.withIndex().filter { it.value == '#'}.sumBy { it.index + offset} )

            while (!state.startsWith(".....")) {
                state = ".$state"
                offset -= 1
            }
            while (!state.endsWith(".....")) {
                state = "$state."
            }
            val newState = mutableListOf<Char>()
            for (i in 0 .. state.length - 5) {
                newState.add(rules[state.substring(i..i + 4)]!!)
            }
            state = newState.joinToString("")
            offset += 2
        }
        println("part 1: ${sums[20]}")
        println("part 2: ${sums[200] + (sums[200] - sums[199]) * (50000000000 - 200)}")
    }
    println("Time\t: ${ms}s")
}
