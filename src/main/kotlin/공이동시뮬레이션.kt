import kotlin.math.max
import kotlin.math.min

class 공이동시뮬레이션 {
    class Solution {
        private fun isOverFlowX(first: Int, second: Int, n: Int): Boolean {
            if ((first < 0 || first >= n) && (second <0 || second >= n)) {
                return true
            }
            return false
        }

        private fun isOverFlowY(first: Int, second: Int, m: Int): Boolean {
            if ((first < 0 || first >= m) && (second < 0 || second >=m)) {
                return true
            }
            return false
        }

        fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
            var rangeX = Pair(x, x)
            var rangeY = Pair(y, y)

            for (i in queries.indices.reversed()) {
                val dx = queries[i][1]
                if (queries[i][0] == 0) {
                    if (rangeY.first == 0) {
                        val first = 0
                        val second = min(rangeY.second + dx, m - 1)
                        rangeY = Pair(first, second)
                    }
                    else {
                        if (isOverFlowY(rangeY.first + dx, rangeY.second + dx, m)) {
                            return 0
                        }
                        val first = min(rangeY.first + dx, m - 1)
                        val second = min(rangeY.second + dx, m - 1)
                        rangeY = Pair(first, second)
                    }
                }
                else if (queries[i][0] == 1) {
                    if (rangeY.second == m - 1) {
                        val first = max(rangeY.first - dx, 0)
                        val second = m - 1
                        rangeY = Pair(first, second)
                    }
                    else {
                        if (isOverFlowY(rangeY.first - dx, rangeY.second - dx, m)) {
                            return 0
                        }

                        val first = max(rangeY.first - dx, 0)
                        val second = max(rangeY.second - dx, 0)
                        rangeY = Pair(first, second)
                    }
                }

                else if (queries[i][0] == 2) {
                    if (rangeX.first == 0) {
                        val first = 0
                        val second = min(rangeX.second + dx, n -1)
                        rangeX = Pair(first, second)
                    } else {
                        if (isOverFlowX(rangeX.first + dx, rangeX.second + dx, n)) {
                            return 0
                        }

                        val first = min(rangeX.first + dx, n - 1)
                        val second = min(rangeX.second + dx, n - 1)

                        rangeX = Pair(first, second)
                    }
                }

                else if (queries[i][0] == 3) {
                    if (rangeX.second == n-1) {
                        val first = max(rangeX.first - dx , 0)
                        val second = n - 1
                        rangeX = Pair(first, second)
                    }
                    else {
                        if (isOverFlowX(rangeX.first - dx, rangeX.second - dx, n)) {
                            return 0
                        }

                        val first = max(rangeX.first - dx, 0)
                        val second = max(rangeX.second - dx, 0)
                        rangeX = Pair(first, second)
                    }
                }
            }

            return ((rangeX.second - rangeX.first + 1) * (rangeY.second - rangeY.first + 1).toLong())
        }
    }
}

fun main() {
    val solution = 공이동시뮬레이션.Solution()
    System.out.println(solution.solution(2, 5, 0, 1, arrayOf(
        intArrayOf(3,1),
        intArrayOf(2,2),
        intArrayOf(1,1),
        intArrayOf(2,3),
        intArrayOf(0,1),
        intArrayOf(2,1)
    )))
}