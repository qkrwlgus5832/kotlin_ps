import kotlin.math.max

class `가장 긴 팬린드롬` {
    class Solution {
        private var answer = 1

        private val dp = Array(2501) {
            Array(2501) {
                -1
            }
        }

        fun recursion(start: Int, end: Int, s: String) {
            if (start == end) {
                dp[start][end] = 1
                return
            }

            if (start + 1 == end) {
                if (s[start] == s[end]) {
                    dp[start][end] = 2
                }
                else {
                    dp[start][end] = Int.MIN_VALUE
                }
                return
            }

            if (s[start] == s[end]) {
                if (dp[start +1][end - 1] == -1) {
                    recursion(start + 1 , end -1, s)
                }
                if (dp[start + 1][end - 1] == Int.MIN_VALUE) {
                    dp[start][end] = Int.MIN_VALUE
                }
                else {
                    dp[start][end] = dp[start + 1][end - 1] + 2
                }
            } else {
                dp[start][end] = Int.MIN_VALUE
            }
        }

        fun solution(s: String): Int {
            for (i in 0 until s.length) {
                for (j in i until s.length) {
                    if (dp[i][j] == -1) {
                        recursion(i, j, s)
                    }

                    answer = max(answer, dp[i][j])
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `가장 긴 팬린드롬`.Solution()
    System.out.println(solution.solution("abacde"))
}