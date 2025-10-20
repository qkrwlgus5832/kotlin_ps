class `파괴되지 않은 건물` {
    class Solution {
        private fun accumulatedSum(accumulatedSum: Array<IntArray>, board: Array<IntArray>) {
            for (i in 0 until accumulatedSum.size) {
                for (j in 0 until accumulatedSum[i].size - 1) {
                    accumulatedSum[i][j + 1] = accumulatedSum[i][j] + accumulatedSum[i][j+1]
                }
            }

            for (j in 0 until accumulatedSum[0].size) {
                for (i in 0 until accumulatedSum.size - 1) {
                    accumulatedSum[i + 1][j] = accumulatedSum[i][j] + accumulatedSum[i+1][j]
                }
            }

            for (i in 0 until board.size) {
                for (j in 0 until board[i].size) {
                    board[i][j] = board[i][j] + accumulatedSum[i][j]
                }
            }
        }

        fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
            var answer: Int = 0
            val accumulatedSum = Array(board.size + 1) {
                IntArray(board[0].size + 1)
            }

            for (i in 0 until skill.size) {
                val type = skill[i][0]
                val r1 = skill[i][1]
                val c1 = skill[i][2]
                val r2 = skill[i][3]
                val c2 = skill[i][4]
                var degree = skill[i][5]

                if (type == 1) {
                    degree = degree * -1
                }

                accumulatedSum[r1][c1] += degree
                accumulatedSum[r1][c2 + 1] += degree * -1
                accumulatedSum[r2 + 1][c1] += degree * -1
                accumulatedSum[r2 + 1][c2 + 1] += degree
            }

            accumulatedSum(accumulatedSum, board)

            for (i in 0 until board.size) {
                for (j in 0 until board[i].size) {
                    if (board[i][j] > 0) {
                        answer++
                    }
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `파괴되지 않은 건물`.Solution()
    solution.solution(arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    ),arrayOf(
        intArrayOf(1,1,1,2,2,4),
        intArrayOf(1,0,0,1,1,2),
        intArrayOf(2,2,0,2,0,100),
    ))
}