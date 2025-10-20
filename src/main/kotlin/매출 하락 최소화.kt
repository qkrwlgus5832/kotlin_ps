import kotlin.math.min

class `매출 하락 최소화` {
    class Solution {
        private val dp = Array(300001) {
            LongArray(2) {
                -1L
            }
        }

        private val graph = Array(300001) {
            mutableListOf<Int>()
        }


        private fun pushGraph(links: Array<IntArray>) {
            for (i in 0 until links.size) {
                graph[links[i][0]].add(links[i][1])
            }
        }

        private fun recursion(current: Int, isContains: Boolean, sales: IntArray) {
            if (isContains) {
                var result = 0L

                for (i in 0 until graph[current].size) {
                    val child = graph[current][i]

                    if (dp[child][0] == -1L) {
                        recursion(child, false, sales)
                    }

                    if (dp[child][1] == -1L) {
                        recursion(child, true, sales)
                    }
                    result += min(dp[child][0], dp[child][1])
                }

                dp[current][1] = sales[current - 1] + result
            }
            else {
                for (i in 0 until graph[current].size) {
                    val child = graph[current][i]

                    if (dp[child][0] == -1L) {
                        recursion(child, false, sales)
                    }

                    if (dp[child][1] == -1L) {
                        recursion(child, true, sales)
                    }
                }

                var minValue = Long.MAX_VALUE

                for (i in 0 until graph[current].size) {
                    val child = graph[current][i]
                    var siblingSum = 0L

                    for (j in 0 until graph[current].size) {
                        if (i == j) {
                            continue
                        }

                        val child = graph[current][j]

                        siblingSum += min(dp[child][0], dp[child][1])
                    }

                    minValue = min(minValue, dp[child][1] + siblingSum)
                }
                dp[current][0] = if (minValue == Long.MAX_VALUE) 0L else minValue
            }
        }

        fun solution(sales: IntArray, links: Array<IntArray>): Int {
            pushGraph(links)

            recursion(1, true, sales)
            recursion(1, false, sales)


            return min(dp[1][0], dp[1][1]).toInt()
        }
    }
}

fun main() {
    val solution = `매출 하락 최소화`.Solution()
    println(solution.solution(
        intArrayOf(10, 10, 1, 1),
        arrayOf(
            intArrayOf(3, 2),
            intArrayOf(4, 3),
            intArrayOf(1, 4),
        )
    ))
}