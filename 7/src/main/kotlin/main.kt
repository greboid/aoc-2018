import java.io.File

fun main() {
    val input = File("input.txt").readLines().map {
        it.split(" ")
    }.map {
        it[1] to it[7]
    }
    val instructions: MutableMap<Char, MutableSet<Char>> = mutableMapOf()
    input.forEach {
        instructions.getOrPut(it.second[0]) { mutableSetOf() }.add(it.first[0])
    }
    println("Part 1: ${follow(instructions, 1).first}")
    println("Part 2: ${follow(instructions, 5).second}")
}

fun follow(start_instructions: Map<Char, Set<Char>>, numWorkers: Int): Pair<String, Int> {
    val instructions = start_instructions.toMutableMap().mapValues {
        it.value.toMutableList()
    }
    val steps = instructions.keys.toMutableSet()
    instructions.values.forEach {
        steps.addAll(it)
    }
    val seq: MutableList<Char> = mutableListOf()
    val workers: MutableList<Pair<Char, Int>> = mutableListOf()
    var t = 0
    while (!steps.isEmpty() || !workers.isEmpty()) {
        while (!steps.isEmpty() && workers.size < numWorkers) {
            val step = steps.filter { instructions[it].isNullOrEmpty() }.min() ?: break
            val stepTime = step - 'A' + 61
            workers.add(Pair(step, t + stepTime))
            steps.remove(step)
            seq.add(step)
        }
        if (!workers.isEmpty()) {
            val w = workers.minBy { it.second }!!
            workers.remove(w)
            instructions.values.forEach { it.remove(w.first) }
            t = w.second
        }
    }
    return Pair(seq.joinToString(""), t)
}
