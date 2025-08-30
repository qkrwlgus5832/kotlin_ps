import java.lang.Math.pow
import kotlin.math.min

class `금과 은 운반하기` {
    class Solution {
        private var answer = Long.MAX_VALUE

        private fun isAnswer(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray, currentT: Long): Boolean {
            var maxGold = 0L
            var maxSilver = 0L
            var allCount = 0L

            for (i in 0 until t.size) {
                val count = ((currentT / t[i]) + 1) / 2

                allCount += min(w[i] * count, (g[i] + s[i]).toLong())
                maxGold += min(g[i].toLong(), w[i] * count)
                maxSilver += min(s[i].toLong(), w[i] * count)
            }

            if (allCount >= a + b && maxGold >= a && maxSilver >= b) {
                return true
            }

            return false
        }

        private fun binarySearch(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray, tStart: Long, tEnd: Long) {
            if (tStart == tEnd) {
                answer = tStart.toLong()
                return
            }
            if (tStart + 1 == tEnd) {
                if (isAnswer(a, b, g, s, w, t, tStart)) {
                    answer = tStart.toLong()
                }
                else {
                    answer = tEnd.toLong()
                }
                return
            }

            if (isAnswer(a, b, g, s, w, t, (tStart.toLong() + tEnd) / 2)) {
                binarySearch(a, b, g, s, w, t, tStart, (tStart + tEnd) / 2)
            } else {
                binarySearch(a, b, g, s, w, t, (tStart + tEnd) / 2 + 1, tEnd)
            }
        }

        fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
            binarySearch(a, b, g, s, w, t, 0, 4 * pow(10.toDouble(), 14.toDouble()).toLong())
            return answer
        }
    }
}

fun main() {
    val solution = `금과 은 운반하기`.Solution()
    println(solution.solution(10, 10, intArrayOf(100) , intArrayOf(100), intArrayOf(7), intArrayOf(10)))
}