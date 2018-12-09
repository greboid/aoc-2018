import java.io.File
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

class Marble(var value: Long) {
    var next: Marble = this
    var previous: Marble = this
}

class Board {
    private var current: Marble = Marble(0)

    fun placeNext(value: Long): Long {
        if (value % 23 == 0L) {
            repeat(7) {
                current = current.previous
            }
            val score = current.value + value
            current.previous.next = current.next
            current.next.previous = current.previous
            current = current.next
            return score
        } else {
            current = current.next
            val new = Marble(value)
            new.previous = current
            new.next = current.next
            current.next.previous = new
            current.next = new
            current = new
            return 0
        }
    }
}

class Game(private val numPlayers: Int, lastMarblePoints: Long) {
    private val board = Board()
    private val marbles = (1 until lastMarblePoints).asSequence()
    private val scores = (0 until numPlayers).map { 0L }.toMutableList()
    private var currentPlayer = 1

    fun play(): List<Long> {
        for (marble in marbles) {
            scores[currentPlayer] += board.placeNext(marble)
            currentPlayer = (currentPlayer + 1) % numPlayers
        }
        return scores
    }
}

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().filter {
            !it.isEmpty()
        }.map {
            it.split(" ")
        }.map {
            Pair(it[0].toInt(), it[6].toLong())
        }[0]
        println("Part 1\t: ${Game(input.first, input.second).play().max()}")
        println("Part 2\t: ${Game(input.first, input.second * 100).play().max()}")
    }
    println("Time\t: ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
