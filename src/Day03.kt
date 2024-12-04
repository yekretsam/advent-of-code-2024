fun main() {
    fun solution(inputs: List<String>, guards: Boolean = false): Int {
        var doIt = true
        return inputs.sumOf { line ->
            "(mul\\(\\d{1,3},\\d{1,3}\\))|(don't)|(do)".toRegex().findAll(line).map(MatchResult::value).map { match ->
                if (doIt && match.startsWith("mul")) {
                    match.removeSurrounding("mul(", ")")
                        .split(",")
                        .map(String::toInt)
                        .reduce(Int::times)
                } else {
                    if (guards) doIt = match == "do"
                    0
                }
            }.sum()
        }
    }

    var inputs = readInput("Day03_test")
    check(solution(inputs) == 161)
    check(solution(inputs, true) == 48)

    inputs = readInput("Day03")
    solution(inputs).println()
    solution(inputs, true).println()
}
