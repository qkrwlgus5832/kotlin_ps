class `산 모양 타일링` {
    class Solution {
        fun solution(n: Int, tops: IntArray): Int {
            val dpArray = Array(n + 1) {
                Array(2) {
                    0
                }
            }

            if (tops[0] == 1) {
                dpArray[0][0] = 3
                dpArray[0][1] = 1
            } else {
                dpArray[0][0] = 2
                dpArray[0][1] = 1
            }

            for (i in 1 until tops.size) {
                if (tops[i] == 1) {
                    dpArray[i][0] = (dpArray[i - 1][0] * 3 + dpArray[i - 1][1] * 2) % 10007
                    dpArray[i][1] = (dpArray[i - 1][0] + dpArray[i - 1][1]) % 10007
                }
                else {
                    dpArray[i][0] = (dpArray[i - 1][0] * 2 + dpArray[i-1][1]) % 10007
                    dpArray[i][1] = (dpArray[i-1][0] + dpArray[i-1][1]) % 10007
                }
            }

            return dpArray[tops.size - 1][0] % 10007 + dpArray[tops.size - 1][1] % 10007
        }
    }
}

fun main() {
    val solution = `산 모양 타일링`.Solution()
    solution.solution(
        10, intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    )
}