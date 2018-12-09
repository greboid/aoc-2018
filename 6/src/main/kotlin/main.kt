import java.io.File
import java.lang.Math.abs
import java.lang.RuntimeException
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Coord(val x: Int, val y: Int) {
    fun distance(x: Int, y: Int) = abs(this.x - x) + abs(this.y - y)
    override fun toString(): String = "[${x},${y}]"
}

fun main() {
    val ms = measureTimeMillis {
        part1()
        part2()
    }
    println("      : ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}

fun part2() {
    val input = File("input.txt").readLines().map {
        it.split(", ").map { it.toInt() }
    }.map {
        Coord(it[0], it[1])
    }
    val xMin = input.map{ it.x }.min() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val xMax = input.map{ it.x }.max() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val yMin = input.map{ it.y }.min() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val yMax = input.map{ it.y }.max() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val sum = (xMin until xMax).asSequence().flatMap { x ->
        (yMin until yMax).asSequence().map { y ->
            input.map { it.distance(x, y) }.sum()
        }
    }.filter { it < 10000 }.count()

    println("Part 2: ${sum}")
}

fun part1() {
    val input = File("input.txt").readLines().map {
        it.split(", ").map { it.toInt() }
    }.map {
        Coord(it[0], it[1])
    }
    val xMin = input.map{ it.x }.min() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val xMax = input.map{ it.x }.max() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val yMin = input.map{ it.y }.min() ?: throw RuntimeException("¯\\_(ツ)_/¯")
    val yMax = input.map{ it.y }.max() ?: throw RuntimeException("¯\\_(ツ)_/¯")

    val infinites = mutableSetOf<Coord>()
    val areas = mutableMapOf<Coord, Int>()
    for (x in xMin..xMax) {
        for (y in yMin until yMax) {
            val one = input.minBy { it.distance(x, y) } ?: throw RuntimeException("¯\\_(ツ)_/¯")
            val two = input.reversed().minBy { it.distance(x, y) } ?: throw RuntimeException("¯\\_(ツ)_/¯")
            if (one != two) {
                continue;
            }
            if (x == xMin || x == xMax || y == yMin || y == yMax) {
                infinites.add(one)
            }
            areas.merge(one, 1) { a, b -> a + b}
        }
    }
    println("Part 1: ${areas.filterKeys { !infinites.contains(it) }.values.max()}")
}
