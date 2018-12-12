import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Day11(private val serial: Int, private val gridSize: Int) {

    private val summedGrid = Array(gridSize) { IntArray(gridSize) }

    init {
        populateGrid()
    }

    private class Result(val x: Int, val y: Int, val power: Int, val size: Int)

    fun bestSquare(smallest: Int, largest: Int): Triple<Int, Int, Int> {
        val results: MutableList<Result> = mutableListOf()
        (smallest..largest).forEach { size ->
            (size until gridSize).forEach { y ->
                (size until gridSize).forEach { x ->
                    results.add(Result(x - size + 1, y - size + 1, getSum(x, y, size), size))
                }
            }

        }
        val max = results.maxBy { it.power }!!
        return Triple(max.x, max.y, max.size)
    }

    private fun getSum(x: Int, y: Int, size: Int): Int {
        val upperLeft = if (x - size >= 0 && y - size >= 0) {
            summedGrid[y - size][x - size]
        } else {
            0
        }
        val upperRight = if (y - size >= 0) {
            summedGrid[y - size][x]
        } else {
            0
        }
        val lowerLeft = if (x - size >= 0) {
            summedGrid[y][x - size]
        } else {
            0
        }
        val lowerRight = summedGrid[y][x]
        return lowerRight + upperLeft - upperRight - lowerLeft
    }

    private fun populateGrid() {
        (0 until gridSize).forEach { y ->
            (0 until gridSize).forEach { x ->
                val upperLeft = if (x == 0 || y == 0) {
                    0
                } else {
                    summedGrid[y - 1][x - 1]
                }
                val left = if (x == 0) {
                    0
                } else {
                    summedGrid[y][x - 1]
                }
                val up = if (y == 0) {
                    0
                } else {
                    summedGrid[y - 1][x]
                }
                val me = powerLevel(x, y)
                summedGrid[y][x] = me + up + left - upperLeft
            }
        }
    }

    private fun powerLevel(x: Int, y: Int): Int {
        val rackId = x + 10
        var powerLevel = rackId * y
        powerLevel += serial
        powerLevel *= rackId
        powerLevel = (powerLevel / 100) % 10
        powerLevel -= 5
        return powerLevel
    }
}

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().filter {
            !it.isEmpty()
        }.map {
            it.toInt()
        }.first()
        val solver = Day11(input, 300)
        val part1 = solver.bestSquare(3, 3)
        println("Part 1: ${part1.first},${part1.second}")
        val part2 = solver.bestSquare(1, 300)
        println("Part 2: ${part2.first},${part2.second},${part2.third}")
    }
    println("Time\t: ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
