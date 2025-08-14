import kotlin.math.max
import kotlin.math.min

class 카펫 {
    class Solution {
        private fun getBrownAndYellow(right: Int, left: Int): Pair<Int, Int> {
            val brown = right * 2 + left * 2 - 4
            val yellow = right * left - brown

            return Pair(brown, yellow)
        }

        fun solution(brown: Int, yellow: Int): IntArray {
            var answer = intArrayOf()

            for (i in 1..brown) {
                for (j in 1..brown) {
                    val result = getBrownAndYellow(i, j)
                    if (result.first == brown && result.second == yellow) {
                        val right = max(i, j)
                        val left = min(i, j)
                        answer += right
                        answer += left
                        return answer
                    }
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = 카펫.Solution()
    solution.solution(24, 24)
}
