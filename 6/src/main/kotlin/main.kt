import java.io.File
import java.lang.Math.abs
import java.lang.RuntimeException

class Coord(val x: Int, val y: Int) {
    fun distance(x: Int, y: Int) = abs(this.x - x) + abs(this.y - y)
    override fun toString(): String = "[${x},${y}]"
}

fun main() {
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
        for (y in yMin..yMax) {
            val one = input.minBy { it.distance(x, y) } ?: throw RuntimeException("¯\\_(ツ)_/¯")
            val two = input.asReversed().minBy { it.distance(x, y) } ?: throw RuntimeException("¯\\_(ツ)_/¯")
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
