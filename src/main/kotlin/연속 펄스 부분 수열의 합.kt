import kotlin.math.max

class `연속 펄스 부분 수열의 합` {
    class Solution {
        fun solution(sequence: IntArray): Long {
            var maxA = Long.MIN_VALUE
            var maxB = Long.MIN_VALUE

            var current = 0L

            for (i in 0 until sequence.size) {
                if (i % 2 == 0) {
                    current = max(current + sequence[i] * -1, sequence[i] * -1L)
                }
                else {
                    current = max(current + sequence[i], sequence[i].toLong())
                }

                maxA = max(maxA, current)
            }

            for (i in 0 until sequence.size) {
                if (i % 2 == 0) {
                    current = max(current + sequence[i], sequence[i].toLong())
                }
                else {
                    current = max(current + sequence[i] * -1, sequence[i] * -1L)
                }

                maxB = max(maxB, current)
            }

            return max(maxA, maxB)
        }
    }
}

fun main() {
    val solution = `연속 펄스 부분 수열의 합`.Solution()
    solution.solution(
        intArrayOf(-100000)
    )
}