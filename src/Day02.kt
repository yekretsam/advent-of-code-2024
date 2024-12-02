import kotlin.math.abs

fun main() {
    fun String.toIntReport() = this.split(" ").map { it.toInt() }

    fun List<Int>.removeAt(index: Int) = toMutableList().apply { removeAt(index) }.toList()

    fun checkReport(report: List<Int>, dampener: Boolean = false): Boolean {
        var increasing = false
        val zippedList = report.zipWithNext()

        zippedList.forEachIndexed { index, pair ->
            try {
                val diff = pair.first - pair.second

                if (index == 0) {
                    increasing = diff > 0
                } else if (increasing && diff < 0 || !increasing && diff > 0) {
                    throw IllegalArgumentException("Not only increasing or decreasing")
                }

                if (abs(diff) !in 1..3) {
                    throw IllegalArgumentException("Difference is not in range 1..3")
                }
            } catch(_: Exception) {
                return if(dampener) {
                    (index > 0 && checkReport(report.removeAt(index - 1))) ||
                            checkReport(report.removeAt(index)) ||
                            (index+1 < report.size && checkReport(report.removeAt(index + 1)))
                } else {
                    false
                }
            }
        }

        return true
    }

    fun part1(input: List<String>) = input.map { line ->
            if(checkReport(line.toIntReport())) 1 else 0
        }.sum()

    fun part2(input: List<String>)= input.map { line ->
            if(checkReport(line.toIntReport(), true)) 1 else 0
        }.sum()

    var input = readInput("Day02_test")
    check(part1(input) == 2)
    check(part2(input) == 4)

    input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
