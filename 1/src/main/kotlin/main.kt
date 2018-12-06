import java.io.File
import kotlin.streams.asStream
import kotlin.streams.toList
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val num = measureTimeMillis {
        val lineList = File("input.txt").useLines { lines -> lines.map { s -> s.toInt() }.asStream().toList() }
        println(lineList.sum())
        val seen = mutableSetOf<Int>()
        var sum = 0
        while (true) {
            lineList.forEach { line ->
                run {
                    sum += line
                    if (sum in seen) {
                        println(sum)
                        return@measureTimeMillis
                    }
                    seen.add(sum)
                }
            }
        }
    }
    println(num)
}
