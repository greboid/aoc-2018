import java.io.File
import java.util.*

fun main() {
    val input = File("input.txt").readLines().first()
    println("Part 1\t: ${react(input).length}")
    println("Part 2\t: ${fullyReact(input).length}")
}

fun react(input: String): String {
    with(Stack<Char>()) {
        input.toCharArray().forEach {
            if (!empty() && peek().toInt().xor(it.toInt()) == 32) {
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
