import java.util.Scanner
import kotlin.math.min

class `파일 합치기` {
    class Solution(
        val prefixSum: IntArray
    ) {

        private fun recursion(lines: IntArray, n: Int, k: Int, dp: Array<IntArray>) {
            var minValue = Integer.MAX_VALUE

            if (n == k) {
                dp[n][k] = 0
                return
            }

            if (n + 1 == k) {
                dp[n][k] = lines[n] + lines[k]
                return
            }

            for (i in n..k - 1) {
                if (dp[n][i] == -1) {
                    recursion(lines, n, i, dp)
                }

                if (dp[i +1][k] == -1) {
                    recursion(lines, i + 1, k, dp)
                }

                val currentValue = dp[n][i] + dp[i+1][k] +
                        (if (n - 1 >= 0) (prefixSum[i] - prefixSum[n-1]) else prefixSum[i]) +
                                (prefixSum[k] - prefixSum[i])

                minValue = min(minValue, currentValue)
            }

            dp[n][k] = minValue
        }

        fun solution(lines: IntArray): Int {
            val dp = Array<IntArray>(lines.size) {
                IntArray(lines.size) {
                    -1
                }
            }

            recursion(lines, 0,  lines.size -1, dp)
            return dp[0][lines.size - 1]
        }
    }
}


fun main() = with(Scanner(System.`in`)) {
    val t = nextInt()

    for (t1 in 0 until t) {
        val k = nextInt()
        val lines = IntArray(k)
        val prefixSum = IntArray(k)

        for (k1 in 0 until k) {
            val line = nextInt()
            lines[k1] = line
            prefixSum[k1] = if (k1 == 0) line else prefixSum[k1 - 1] + line
        }

        val solution = `파일 합치기`.Solution(prefixSum)

        println(solution.solution(lines))
    }
}