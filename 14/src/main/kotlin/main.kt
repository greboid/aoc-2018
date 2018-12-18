import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().first()
        println("Part 1: ${scores.drop(input.toInt()).take(10).joinToString("")}")
        println("Part 2: ${scores.windowed(input.length).indexOf(input.toCharArray().map(Character::getNumericValue))}")
    }
    println(ms)
}

private val scores = sequence {
    val score = arrayOf(0, 1)
    val recipes = mutableListOf(3, 7)
    yieldAll(recipes)
    while (true) {
        val digits = (recipes[score.first()] + recipes[score.second()]).toString().map(Character::getNumericValue)
        recipes.addAll(digits)
        yieldAll(digits)
        score[0] = (score.first() + recipes[score.first()] + 1) % recipes.size
        score[1] = (score.second() + recipes[score.second()] + 1) % recipes.size
    }
}

private fun Array<Int>.second() = this[1]
