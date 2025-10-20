import kotlin.math.max
import kotlin.math.min

class `상담원 인원` {
    class Solution {

        private var answer = Int.MAX_VALUE

        private fun getResultTime(k: Int, n: Int, reqs: Array<IntArray>, adviser: MutableList<Int>): Int {
            val lastTime = Array(k +1) {
                IntArray(n + 1) {
                    0
                }
            }

            var waitingTime = 0

            for (i in 0 until reqs.size) {
                val requestTime = reqs[i][0]
                val processTime = reqs[i][1]
                val requestId = reqs[i][2] - 1

                var minWaitingTime = Int.MAX_VALUE
                var minWaitingIndex = -1

                if (adviser[requestId] == 0) {
                    return Int.MAX_VALUE
                }

                for (j in 0 until adviser[requestId]) {
                    val adviserEndTime = lastTime[requestId][j]
                    val waitingTime = adviserEndTime - requestTime
                    if (minWaitingTime > waitingTime) {
                        minWaitingTime = waitingTime
                        minWaitingIndex = j
                    }
                }

                if (minWaitingTime > 0) {
                    waitingTime += minWaitingTime
                }

                lastTime[requestId][minWaitingIndex] = max(requestTime + processTime, lastTime[requestId][minWaitingIndex] + processTime)
            }

            return waitingTime
        }

        private fun recursion(k: Int, n: Int, reqs: Array<IntArray>, sum: Int, adviser: MutableList<Int>) {
            if (adviser.size == k - 1) {
                adviser.add(n - sum)

                val resultTime = getResultTime(k, n, reqs, adviser)

                adviser.removeLast()

                answer = min(answer, resultTime)
                return
            }

            for (i in 1..n) {
                if (sum + i < n) {
                    adviser.add(i)
                    recursion(k, n, reqs, sum + i, adviser)
                    adviser.removeLast()
                }
            }
        }

        fun solution(k: Int, n: Int, reqs: Array<IntArray>): Int {
            recursion(k, n, reqs, 0, mutableListOf())
            return answer
        }
    }
}

fun main() {
    val solution = `상담원 인원`.Solution()
    println(solution.solution(3, 5,
        arrayOf(
            intArrayOf(10, 60, 1),
            intArrayOf(15, 100, 3),
            intArrayOf(20, 30, 1),
            intArrayOf(30, 50, 3),
            intArrayOf(50, 40, 1),
            intArrayOf(60, 30, 2),
            intArrayOf(65, 30, 1),
            intArrayOf(70, 100, 2),
        )
    ))
}