import java.io.File

class Node(val children: List<Node>, val metaData: List<Int>) {
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

class Solver(val input: Iterator<Int>) {
    fun parseNode(): Node {
        val childCount = input.next()
        val metadataCount = input.next()
        val children = (0 until childCount).map { parseNode() }
        val metadata = (0 until metadataCount).map { input.next() }
        return Node(children, metadata)
    }
}

fun main() {
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
