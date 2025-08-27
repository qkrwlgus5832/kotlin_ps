import kotlin.math.min

class 합승택시요금 {
    class Solution {
        private val graph = Array(201) {
            IntArray(201) {
                Int.MAX_VALUE
            }
        }

        private fun floydWarshall(n: Int) {
            for (i in 1..n) {
                for (i1 in 1..n) {
                    for (i2 in 1..n) {
                        if (i1 == i2) {
                            continue
                        }
                        if (graph[i1][i] != Int.MAX_VALUE && graph[i][i2] != Int.MAX_VALUE) {
                            graph[i1][i2] = min(graph[i1][i2], graph[i1][i] + graph[i][i2])
                        }
                    }
                }
            }
        }

        private fun getMinCost(n: Int, s: Int, a: Int, b :Int): Int {
            var result = Int.MAX_VALUE

            for (i in 1..n) {
                if (graph[s][i] != Int.MAX_VALUE && graph[i][a] != Int.MAX_VALUE && graph[i][b] != Int.MAX_VALUE) {
                    result = min(result, graph[s][i] + graph[i][a] + graph[i][b])
                }
            }

            return result
        }

        fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
            for (i in 1..n) {
                graph[i][i] = 0
            }

            fares.forEach { fare ->
                graph[fare[0]][fare[1]] = fare[2]
                graph[fare[1]][fare[0]] = fare[2]
            }

            floydWarshall(n)

            return getMinCost(n, s, a, b)
        }
    }
}

fun main() {
    val solution = 합승택시요금.Solution()
    println(solution.solution(6, 4, 5, 6, arrayOf(
        intArrayOf(2, 6, 6),
        intArrayOf(6, 3, 7),
        intArrayOf(4, 6, 7),
        intArrayOf(6, 5, 11),
        intArrayOf(2, 5, 12),
        intArrayOf(5, 3, 20),
        intArrayOf(2, 4, 8),
        intArrayOf(4, 3, 9)
    )))
}