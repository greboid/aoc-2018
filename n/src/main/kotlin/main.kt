fun main() {
    val ms = measureTimeMillis {
        val answer 0
        println("Part 1: ${answer}")
        println("Part 2: ${answer}")
    }
    println("${TimeUnit.MILLISECONDS.toMinutes(ms)}m${TimeUnit.MILLISECONDS.toSeconds(ms)}.${TimeUnit.MILLISECONDS.toMillis(ms).toString().padStart(3, '0')}s")
}
