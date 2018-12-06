import java.io.File
import kotlin.streams.asStream
import kotlin.streams.toList
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val num = run {
        val lineList = File("input.txt").useLines { lines -> lines.map { s -> s.toInt() }.asStream().toList() }
        println("Part 1: ${lineList.sum()}")
        val seen = mutableSetOf<Int>()
        var sum = 0
        while (true) {
            lineList.forEach { line ->
                    sum += line
                    if (sum in seen) {
                        return@run sum
                    }
                    seen.add(sum)
            }
        }
    }
    println("Part 2: $num")
}
