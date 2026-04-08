import java.lang.Math.pow
import java.util.Scanner
import kotlin.math.min

class `우주 탐사선` {
    class Solution {
        private var answer = Int.MAX_VALUE

        private val dp = Array((pow(2.toDouble(), 10.toDouble()) + 1).toInt()) {
            Array(10) {
                -1
            }
        }

        private fun setBit(next: Int, checked: Int): Int {
            return checked or (1 shl next)
        }

        private fun isEnded(n: Int, checked: Int): Boolean {
            if (checked == (1 shl n) - 1) {
                return true
            }

            return false
        }

        private fun dfs(graph: Array<MutableList<Pair <Int, Int>>>, current: Int, visited: Int, cost: Int) {
            if (isEnded(graph.size, visited)) {
                answer = min(answer, cost)
                return
            }

            if (answer != Int.MAX_VALUE && cost >= answer) {
                return
            }

            for (next in graph[current]) {
                val nextVisited = setBit(next.first, visited)
                val nextCost = cost + next.second

                if (dp[nextVisited][next.first] == -1 || dp[nextVisited][next.first] > nextCost) {
                    dp[nextVisited][next.first] = nextCost
                    dfs(graph, next.first, nextVisited, nextCost)
                }
            }
        }

        fun solution(graph: Array<MutableList<Pair <Int, Int>>>, start: Int): Int {
            dp[setBit(start, 0)][start] = 0
            dfs(graph, start, setBit(start, 0), 0)
            return answer
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val start = nextInt()

    val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val cost = nextInt()

            if (i != j) {
                graph[i].add(Pair(j, cost))
            }
        }
    }

    for (i in 0 until graph.size) {
        graph[i].sortWith(compareBy { it.second })
    }

    val solution = `우주 탐사선`.Solution()

    println(solution.solution(graph, start))
}