import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

fun play(numPlayers: Int, lastMarblePoints: Int): Long {
    val scores = LongArray(numPlayers)
    val marbles = ArrayDeque<Int>().also { it.add(0) }
    var count = 1

    (1..lastMarblePoints).forEach { marble ->
        when (count) {
            23 -> {
                count = 0
                repeat (7) {
                    marbles.addLast(marbles.removeFirst())
                }
                scores[marble % numPlayers] += marble + marbles.removeFirst().toLong()
                marbles.addFirst(marbles.removeLast())
            }
            else -> {
                marbles.addFirst(marbles.removeLast())
                marbles.addFirst(marble)
            }
        }.also { count++ }
    }
    return scores.max() ?: 0L
}

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().filter {
            !it.isEmpty()
        }.map {
            it.split(" ")
        }.map {
            Pair(it[0].toInt(), it[6].toInt())
        }.first()
        println("Part 1\t: ${play(input.first, input.second)}")
        println("Part 2\t: ${play(input.first, input.second*100)}")
    }
    println("Time\t: ${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
