import java.io.File

fun main() {
    val part1 = File("16.txt")
            .readLines()
            .windowed(3, 4, false) {
                it.joinToString(" ")
            }.filter {
                it.contains("Before")
            }.map {
                it.split(Regex("[^0-9]+"))
                        .drop(1).dropLast(1)
                        .map(String::toInt)
                        .chunked(4)
            }.map(::validate)
            .filter {
                it >= 3
            }.count()
    println("Part 1: $part1")
}

private fun validate(list: List<List<Int>>): Int {
    val before = Register(list[0][1], list[0][2], list[0][3])
    val after = Register(list[2][1], list[2][2], list[2][3])

    return opcodes.map {
        it(list[1][1], list[1][2], before)
    }.filter {
        before == after
    }.count()
}

private fun addr(a: Int, b: Int, r: Register) {
    r.c = r.a + r.b
}

private fun addi(a: Int, b: Int, r: Register) {
    r.c = r.a + b
}

private fun mulr(a: Int, b: Int, r: Register) {
    r.c = r.a * r.b
}

private fun muli(a: Int, b: Int, r: Register) {
    r.c = r.a * b
}

private fun banr(a: Int, b: Int, r: Register) {
    r.c = r.a and r.b
}

private fun bani(a: Int, b: Int, r: Register) {
    r.c = r.a and b
}

private fun borr(a: Int, b: Int, r: Register) {
    r.c = r.a or r.b
}

private fun bori(a: Int, b: Int, r: Register) {
    r.c = r.a or b
}

private fun setr(a: Int, b: Int, r: Register) {
    r.c = r.a
}

private fun seti(a: Int, b: Int, r: Register) {
    r.c = a
}

private fun gtir(a: Int, b: Int, r: Register) {
    r.c = if (a > r.b) {
        1
    } else {
        0
    }
}

private fun gtri(a: Int, b: Int, r: Register) {
    r.c = if (r.a > b) {
        1
    } else {
        0
    }
}

private fun gtrr(a: Int, b: Int, r: Register) {
    r.c = if (r.a > r.b) {
        1
    } else {
        0
    }
}

private fun eqir(a: Int, b: Int, r: Register) {
    r.c = if (a == r.b) {
        1
    } else {
        0
    }
}

private fun eqri(a: Int, b: Int, r: Register) {
    r.c = if (r.a == b) {
        1
    } else {
        0
    }
}

private fun eqrr(a: Int, b: Int, r: Register) {
    r.c = if (r.a == r.b) {
        1
    } else {
        0
    }
}

private val opcodes = listOf(::addr, ::addi, ::mulr, ::muli, ::banr, ::bani, ::bori, ::borr, ::setr, ::seti, ::gtir, ::gtri, ::gtrr, ::eqir, ::eqri, ::eqrr)

private data class Register(var a: Int = 0, var b: Int = 0, var c: Int = 0) {
    override fun toString(): String {
        return "[A: $a B: $b C: $c]"
    }
}
