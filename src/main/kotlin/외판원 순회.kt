import java.lang.Math.pow
import java.util.PriorityQueue
import java.util.Scanner
import kotlin.math.min

class `외판원 순회` {
    data class Node(
        val current: Int,
        val checked: Int,
        val cost: Int,
    )

    class Solution {
        private var answer = Int.MAX_VALUE
        private val dp = Array((pow(2.toDouble(), 16.toDouble()) + 1).toInt()) { IntArray(16) { -1 } }
        val queue = PriorityQueue<Node>(compareBy<Node> { it.cost })

        private fun setBit(next: Int, checked: Int): Int {
            return checked or (1 shl next)
        }

        private fun isSetted(next: Int, checked: Int): Boolean {
            if (checked and (1 shl next) != 0) {
                return true
            }

            return false
        }

        private fun isEnded(n: Int, checked: Int): Boolean {
            if (checked == (1 shl n) - 1) {
                return true
            }

            return false
        }

        private fun dfs(current: Int, start: Int, visitied: Int, graph: Array<MutableList<Pair<Int, Int>>>, cost: Int) {
            if (current == start && visitied != 0) {
                if (isEnded(graph.size, visitied)) {
                    answer = min(answer, cost)
                }
                return
            }

            if (answer != Int.MAX_VALUE && cost >= answer) {
                return
            }

            for (i in 0 until graph[current].size) {
                val next = graph[current][i].first
                val nextCost = graph[current][i].second

                if (cost + nextCost >= answer) {
                    return
                }


                if (!isSetted(next, visitied)) {
                    val nextVisitied = setBit(next, visitied)

                    if (dp[nextVisitied][next] != -1 && dp[nextVisitied][next] <= cost + nextCost) {
                        continue
                    }

                    dp[nextVisitied][next] = cost + nextCost
                    dfs(next, start, nextVisitied, graph, cost + nextCost)

                }
            }
        }

        fun solution(graph: Array<MutableList<Pair<Int, Int>>>): Int {
            dfs(0, 0, 0, graph, 0)
            return answer
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val graph = Array(n) { mutableListOf<Pair<Int, Int>>() }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val cost = nextInt()

            if (cost != 0) {
                graph[i].add(Pair(j, cost))
            }
        }
    }

    for (i in 0 until graph.size) {
        graph[i].sortWith(compareBy { it.second })
    }

    val solution = `외판원 순회`.Solution()

    println(solution.solution(graph))
}