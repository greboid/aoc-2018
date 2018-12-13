import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().first()
        println("Part 1\t: ${react(input).length}")
        println("Part 2\t: ${fullyReact(input).length}")
    }
    println("Time\t: ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}

fun react(input: String): String {
    with(Stack<Char>()) {
        input.toCharArray().forEach {
            if (!empty() && peek().isOppositeCase(it)) {
                pop()
            } else {
                push(it)
            }
        }
        return joinToString("")
    }
}

fun fullyReact(input: String): String {
    return ('a' until 'z').map {
        input.replace(it.toString(), "", true)
    }.map {
        react(it)
    }.minBy {
        it.length
    } ?: ""
}

private fun Char.isOppositeCase(c: Char): Boolean {
    return this != c && toLowerCase() == c.toLowerCase()
}
