fun main() {
    fun List<String>.toCharMatrix() = Array(this[0].length) { CharArray(this.size) }.apply {
        this@toCharMatrix.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                this@apply[x][y] = c
            }
        }
    }

    fun part1(matrix: Array<CharArray>): Int {
        var result = 0
        for(y in matrix.indices) {
            for(x in matrix[y].indices) {
                if(matrix[x][y] == 'X') {
                    // search "MAS" in all directions
                    for(dx in -1..1) {
                        for(dy in -1..1) {
                            var toSearch = "MAS"
                            var posToCheck = Pair(x+dx, y+dy)
                            while(toSearch.isNotEmpty()) {
                                if(posToCheck.first < 0 || posToCheck.first >= matrix.size || posToCheck.second < 0 || posToCheck.second >= matrix[0].size) {
                                    break
                                }
                                if(matrix[posToCheck.first][posToCheck.second] == toSearch[0]) {
                                    toSearch = toSearch.substring(1)
                                    posToCheck = Pair(posToCheck.first+dx, posToCheck.second+dy)
                                } else {
                                    break
                                }
                            }
                            if(toSearch.isEmpty()) {
                                result++
                            }
                        }
                    }
                }
            }
        }
        return result
    }

    fun part2(matrix: Array<CharArray>): Int {
        var result = 0
        for(y in matrix.indices) {
            for(x in matrix[y].indices) {
                if(matrix[x][y] == 'A') {
                    if(x > 0 && x < matrix.size-1 && y > 0 && y < matrix[0].size-1) {
                        // both M left
                        if (matrix[x - 1][y - 1] == 'M' && matrix[x + 1][y + 1] == 'S' && matrix[x - 1][y + 1] == 'M' && matrix[x + 1][y - 1] == 'S') {
                            result++
                        }
                        // both m right
                        else if (matrix[x + 1][y - 1] == 'M' && matrix[x - 1][y + 1] == 'S' && matrix[x + 1][y + 1] == 'M' && matrix[x - 1][y - 1] == 'S') {
                            result++
                        }
                        // both m up
                        else if (matrix[x - 1][y - 1] == 'M' && matrix[x + 1][y + 1] == 'S' && matrix[x + 1][y - 1] == 'M' && matrix[x - 1][y + 1] == 'S') {
                            result++
                        }
                        // both m down
                        else if (matrix[x - 1][y + 1] == 'M' && matrix[x + 1][y - 1] == 'S' && matrix[x + 1][y + 1] == 'M' && matrix[x - 1][y - 1] == 'S') {
                            result++
                        }
                    }
                }
            }
        }
        return result
    }

    var inputs = readInput("Day04_test")
    check(part1(inputs.toCharMatrix()) == 18)
    check(part2(inputs.toCharMatrix()) == 9)

    inputs = readInput("Day04")
    part1(inputs.toCharMatrix()).println()
    part2(inputs.toCharMatrix()).println()
}
