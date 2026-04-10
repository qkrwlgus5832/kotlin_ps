import java.lang.Math.pow
import java.util.Scanner
import kotlin.math.min

class 발전소 {
    class Solution {
        private var answer = Int.MAX_VALUE

        private fun setBit(next: Int, checked: Int): Int {
            return checked or (1 shl next)
        }

        private fun isSetted(next: Int, checked: Int): Boolean {
            return checked and (1 shl next) != 0
        }

        private val dp = Array((pow(2.toDouble(), 16.toDouble())).toInt() + 1) {
            Array(16) {
                -1
            }
        }

        fun dfs(graph: Array<MutableList<Pair<Int, Int>>>, current: Int, currentVisited: Int, count: Int, cost: Int) {
            if (count == 0) {
                answer = min(answer, cost)
                return
            }

            if (answer != Int.MAX_VALUE && cost >= answer) {
                return
            }

            for (next in graph[current]) {
                val nextVisited = setBit(next.first, currentVisited)

                if (!isSetted(next.first, currentVisited)) {
                    if (dp[nextVisited][next.first] == -1 || dp[nextVisited][next.first] > next.second + cost) {
                        dp[nextVisited][next.first] = next.second + cost
                        dfs(graph, next.first, nextVisited,
                            count - 1,
                            next.second + cost)
                    }
                } else {
                    if (dp[nextVisited][next.first] == -1 || dp[nextVisited][next.first] > cost) {
                        dp[nextVisited][next.first] = cost
                        dfs(graph, next.first, nextVisited,
                            count,
                            cost)
                    }
                }
            }
        }

        fun solution(graph: Array<MutableList<Pair <Int,Int>>>, powerStation: String, p: Int): Int {
            var visitied = 0
            var count = 0

            for (i in 0 until powerStation.length) {
                if (powerStation[i] == 'Y') {
                    visitied = setBit(i, visitied)
                    count++
                }
            }

            if (p <= count) {
                return 0
            }

            for (i in 0 until powerStation.length) {
                if (powerStation[i] == 'Y') {
                    dp[visitied][i] = 0
                    dfs(graph, i, visitied, p - count, 0)
                    dp[visitied][i] = -1
                }
            }

            return if (this.answer == Int.MAX_VALUE) -1 else this.answer
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

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

    nextLine()

    val powerStation = nextLine()

    val p = nextInt()

    val solution = `발전소`.Solution()

    println(solution.solution(graph, powerStation, p))
}