import java.io.File
import kotlin.streams.asStream
import kotlin.streams.toList
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val lineList = File("input.txt").useLines { lines ->
        lines.map { s -> s.toInt() }.asStream().toList()
    }
    println("Part 1: ${lineList.sum()}")
    val seen = mutableSetOf<Int>()
    var sum = 0
    generateSequence { lineList }.flatten().forEach {
        sum += it
        if (sum in seen) {
            println("Part 2: $sum")
            return
        }
        seen.add(sum)
    }
}
