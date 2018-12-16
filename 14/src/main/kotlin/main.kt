import java.io.File
import kotlin.system.measureTimeMillis

fun main() {
    val ms = measureTimeMillis {
        val input = File("input.txt").readLines().first()
        println("Part 1: ${part1(input.toInt())}")
        println("Part 2: ${part2(input)}")
    }
    println(ms)
}

fun part1(input: Int): String {
    val recipes = mutableListOf(3, 7)
    val scores = arrayOf(0, 1)

    while (recipes.size < input + 10) {
        step(recipes, scores)
    }
    return recipes.drop(input).take(10).joinToString("")
}

fun part2(input: String): Int {
    val recipes = mutableListOf(3, 7)
    val scores = arrayOf(0, 1)

    while (checkPart2End(input, recipes)) {
        step(recipes, scores)
    }
    return recipes.joinToString("").indexOf(input)
}

fun step(recipes: MutableList<Int>, score: Array<Int>){
    recipes += (recipes[score.first()] + recipes[score.second()]).toString().map(Character::getNumericValue)
    score[0] = (score.first() + recipes[score.first()] + 1) % recipes.size
    score[1] = (score.second() + recipes[score.second()] + 1) % recipes.size
}

private fun checkPart2End(input: String, recipes: MutableList<Int>): Boolean {
    return input !in recipes.takeLast(11).joinToString("")
}

private fun Array<Int>.second() = this[1]
