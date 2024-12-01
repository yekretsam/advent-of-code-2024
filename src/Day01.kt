import kotlin.math.absoluteValue

fun main() {
    fun readInputLists(filename: String): Pair<MutableList<Int>, MutableList<Int>> {
        val left = mutableListOf<Int>()
        val right = mutableListOf<Int>()
        readInput(filename).map {
            it.split("   ").forEachIndexed { index, s ->
                if (index == 0)
                    left.add(s.toInt())
                else
                    right.add(s.toInt())
            }
        }

        return Pair(left, right)
    }

    fun part1(inputs: Pair<MutableList<Int>, MutableList<Int>>): Int {
        val (left, right) = inputs

        left.sort()
        right.sort()

        val distances = left.zip(right).map { (it.first - it.second).absoluteValue }

        return distances.sum()
    }

    fun part2(inputs: Pair<MutableList<Int>, MutableList<Int>>): Int {
        val (left, right) = inputs

        val occurrences = right.groupingBy { it }.eachCount()

        return left.sumOf {
            it * (occurrences[it] ?: 0)
        }
    }

    var inputs = readInputLists("Day01_test")
    check(part1(inputs) == 11)
    check(part2(inputs) == 31)

    inputs = readInputLists("Day01")
    part1(inputs).println()
    part2(inputs).println()
}
