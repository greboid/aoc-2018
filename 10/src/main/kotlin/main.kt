import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Message(private val vectors: List<Vector>) {
    private var extrapolated = false
    var time = 0

    fun extrapolate() {
        if (extrapolated) {
            return
        }
        var oldArea = Long.MAX_VALUE
        var area = area()
        while (area < oldArea) {
            forward()
            oldArea = area
            area = area()
            time++
        }
        backward()
        time--
        extrapolated = true
        return
    }

    private fun forward() {
        vectors.forEach {
            it.forward()
        }
    }

    private fun backward() {
        vectors.forEach {
            it.backward()
        }
    }

    private fun area(): Long {
        return (width().last - width().first) * (height().last - height().first).toLong()
    }

    private fun width(): IntRange {
        return IntRange(vectors.minBy { it.x }!!.x, vectors.maxBy { it.x }!!.x)
    }

    private fun height(): IntRange {
        return IntRange(vectors.minBy { it.y }!!.y, vectors.maxBy { it.y }!!.y)
    }

    override fun toString(): String {
        val lightSet = vectors.map { Pair(it.x, it.y) }.toSet()
        return height().joinToString(separator = "\n") { y ->
            width().map { x ->
                if (Pair(x, y) in lightSet) {
                    '#'
                } else {
                    ' '
                }
            }.joinToString(separator = "")
        }
    }
}

class Vector(var x: Int, var y: Int, val dX: Int, val dY: Int) {
    fun forward() {
        x += dX
        y += dY
    }

    fun backward() {
        x -= dX
        y -= dY
    }
}

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().filter {
            !it.isEmpty()
        }.map {
            it.split(",", "<", ">")
        }.map {
            Vector(it[1].trim().toInt(), it[2].trim().toInt(), it[4].trim().toInt(), it[5].trim().toInt())
        }
        val message = Message(input)
        message.extrapolate()
        println("Part 1:\n${message}")
        println("Part 2: ${message.time}")
    }
    println("Time\t: ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
