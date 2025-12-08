import kotlin.math.min

class `고고학 최고의 발견` {
    class Solution {
        private val direction = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(0, 0)
        )

        private var answer = Int.MAX_VALUE

        private fun rotate(clock: Int, number: Int = 1): Int {
            return (clock + number) % 4
        }

        private fun reroate(clock: Int, number: Int = 1): Int {
            if (clock >= number) {
                return clock - number
            }
            else {
                return clock - number + 4
            }
        }

        private fun checkIsAnswer(clockHands: Array<IntArray>): Int {
            var totalCount = 0

            for (i in 1 until clockHands.size) {
                for (j in 0 until clockHands[i].size) {
                    val count = (4 - clockHands[i-1][j]) % 4

                    for (i1 in 0 until direction.size) {
                        val ni = i + direction[i1][0]
                        val nj = j + direction[i1][1]

                        if (ni < 0 || nj < 0 || ni >= clockHands.size || nj >= clockHands[0].size) {
                            continue
                        }

                        clockHands[ni][nj] = rotate(clockHands[ni][nj], count)
                    }

                    totalCount += count
                }
            }

            for (i in 0 until clockHands[0].size) {
                if (clockHands[clockHands.size - 1][i] != 0) {
                    return -1
                }
            }

            return totalCount
        }

        private fun recursion(clockHands: Array<IntArray>, index: Int, totalCount: Int) {
            if (index == clockHands[0].size) {
                val result = checkIsAnswer(clockHands.map {it.copyOf()}.toTypedArray())
                if (result != -1) {
                    answer = min(answer, totalCount + result)
                }

                return
            }

            for (count in 0..3) {
                for (i1 in 0 until 5) {
                    val ni = 0 + direction[i1][0]
                    val nj = index + direction[i1][1]

                    if (ni < 0 || ni >= clockHands.size || nj < 0 || nj >= clockHands[0].size) {
                        continue
                    }

                    clockHands[ni][nj] = rotate(clockHands[ni][nj], count)
                }

                recursion(clockHands, index + 1, totalCount + count)

                for (i1 in 0 until 5) {
                    val ni = 0 + direction[i1][0]
                    val nj = index + direction[i1][1]

                    if (ni < 0 || ni >= clockHands.size || nj < 0 || nj >= clockHands[0].size) {
                        continue
                    }

                    clockHands[ni][nj] = reroate(clockHands[ni][nj], count)
                }

            }
        }

        fun solution(clockHands: Array<IntArray>): Int {
            recursion(clockHands, 0, 0)
            return answer
        }
    }
}

fun main() {
    val solution = `고고학 최고의 발견`.Solution()
    println(solution.solution(
        arrayOf(
            intArrayOf(0,1,3,0),
            intArrayOf(1, 2, 0, 0),
            intArrayOf(3,0,2,2),
            intArrayOf(0,2,0,0),
        )
    ))
}