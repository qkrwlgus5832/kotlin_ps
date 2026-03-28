import java.util.Scanner
import kotlin.math.min

class `RGB거리 2` {
    class Solution {
        private fun getMinValue(dpValue1: Int, dpValue2: Int, currentValue: Int): Int {
            if (dpValue1 == -1 && dpValue2 == -1) {
                return -1
            }

            if (dpValue1 == -1) {
                return dpValue2 + currentValue
            }

            if (dpValue2 == -1) {
                return dpValue1 + currentValue
            }

            return min(dpValue1, dpValue2) + currentValue
        }

        fun solution(rgbCost: Array<IntArray>, n: Int): Int {
            val dp = Array<Array<IntArray>>(rgbCost.size) {
                Array(3) {
                    IntArray(3) {
                        -1
                    }
                }
            }

            dp[0][0][0] = rgbCost[0][0]
            dp[0][1][1] = rgbCost[0][1]
            dp[0][2][2] = rgbCost[0][2]

            for (i in 1 until n) {
                for (j in 0 until 3) {
                    dp[i][j][0] = getMinValue(dp[i-1][j][1], dp[i-1][j][2], rgbCost[i][0])
                    dp[i][j][1] = getMinValue(dp[i-1][j][0], dp[i-1][j][2], rgbCost[i][1])
                    dp[i][j][2] = getMinValue(dp[i-1][j][0], dp[i-1][j][1], rgbCost[i][2])
                }
            }

            var answer = min(dp[n-1][0][1], dp[n-1][0][2])
            answer = min(answer, dp[n-1][1][0])
            answer = min(answer, dp[n-1][1][2])
            answer = min(answer, dp[n-1][2][0])
            answer = min(answer , dp[n-1][2][1])

            return answer
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val rgbCost = Array(n) {
        IntArray(n) { 0 }
    }

    for (i in 0 until n) {
        for (j in 0 until 3) {
            val cost = nextInt()
            rgbCost[i][j] = cost
        }
    }

    val solution = `RGB거리 2`.Solution()

    println(solution.solution(rgbCost, n))
}