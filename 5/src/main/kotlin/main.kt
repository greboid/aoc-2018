import java.io.File
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().first()
        println("Part 1\t: ${react(input, null)}")
        println("Part 2\t: ${fullyReact(input)}")
    }
    println(ms)
}

fun react(input: String, skip: Char?): Int {
    val stack = Stack<Char>()
    input.toCharArray().forEach {
        if (skip != null && (skip == it || skip.toInt().xor(it.toInt()) == 32)) {
            //Skip
        } else {
            if (!stack.empty() && stack.peek().toInt().xor(it.toInt()) == 32) {
                stack.pop()
            } else {
                stack.push(it)
            }
        }
    }
    return stack.size
}

fun fullyReact(input: String): Int {
    return ('a' until 'z').asSequence().map { react(input, it) }.min()!!
}
