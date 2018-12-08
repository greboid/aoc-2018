import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Node(private val children: List<Node>, private val metaData: List<Int>) {
    fun sumMeta(): Int {
        return metaData.sum() + children.sumBy { it.sumMeta() }
    }
    fun value(): Int {
        return if (children.isEmpty()) {
            metaData.sum()
        } else {
            metaData.sumBy { children.getOrNull(it - 1)?.value() ?: 0 }
        }
    }
}

class Solver(private val input: Iterator<Int>) {
    fun parseNode(): Node {
        val childCount = input.next()
        val metadataCount = input.next()
        val children = (0 until childCount).map { parseNode() }
        val metadata = (0 until metadataCount).map { input.next() }
        return Node(children, metadata)
    }
}

fun main() {
    val ms = measureTimeMillis {
        val answer = Solver(
                File("input.txt")
                        .readLines()
                        [0]
                        .split(" ")
                        .map {
                            it.toInt()
                        }.iterator()
        ).parseNode()
        println("Part 1: ${answer.sumMeta()}")
        println("Part 2: ${answer.value()}")
    }
    println("${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
