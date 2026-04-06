class `경사로의 개수` {
    class Solution {
        private val directions: Array<IntArray> = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        companion object {
            private const val REMAINDER = 1000000007
        }

        private fun matrixMultiply(matrixA: Array<IntArray>, matrixB: Array<IntArray>): Array<IntArray> {
            val newMatrix = Array(matrixA.size) {
                IntArray(matrixA.size) {
                    0
                }
            }

            for (i in 0 until matrixA.size) {
                for (j in 0 until matrixA[0].size) {
                    for (i1 in 0 until matrixA[0].size) {
                        if (matrixA[i][i1] == 0 || matrixB[i1][j] == 0) continue
                        newMatrix[i][j] = ((newMatrix[i][j] % REMAINDER) + (((matrixA[i][i1] % REMAINDER) * (matrixB[i1][j].toLong() % REMAINDER)) % REMAINDER).toInt()) % REMAINDER
                    }
                }
            }

            return newMatrix
        }

        fun getBinaryK(k: Int): String {
            var current = 1
            var currentK = k

            var resultString = StringBuilder()

            while (true) {
                if (k >= current) {
                    current *= 2
                }
                else {
                    current /= 2
                    break
                }
            }

            while (current >= 1) {
                if (currentK >= current) {
                    resultString.append(1)
                    currentK -= current
                } else {
                    resultString.append(0)
                }
                current /= 2
            }

            return resultString.toString()
        }

        private fun divideAndConquer(dp: Array<IntArray>, binaryString: String): Array<IntArray> {
            var result = Array(dp.size) {
                IntArray(dp[0].size) {
                    0
                }
            }

            var nextDp = dp

            if (binaryString == "1") {
                return dp
            }

            var flag = false

            for (i in binaryString.length - 1 downTo  0) {
                if (binaryString[i] == '1') {
                    if (i == binaryString.length - 1) {
                        result = nextDp
                    } else {
                        if (binaryString.last() == '0' && flag == false) {
                            flag = true
                            result = nextDp
                        }
                        else {
                            result = matrixMultiply(result, nextDp)
                        }
                    }
                }
                nextDp = matrixMultiply(nextDp, nextDp)
            }

            return result
        }

        fun solution(grid: Array<IntArray>, d: IntArray, k: Int): Int {
            var answer: Int = 0

            val matrix = Array(d.size) {
                Array(grid.size * grid[0].size) {
                    IntArray(grid.size * grid[0].size) {
                        0
                    }
                }
            }

            var dp = Array(grid.size * grid[0].size) {
                IntArray(grid.size * grid[0].size) {
                    0
                }
            }


            for (i1 in 0 until d.size) {
                for (i in 0 until grid.size) {
                    for (j in 0 until grid[i].size) {
                        val currentIndex = i * grid[0].size + j

                        for (direction in directions) {
                            val ni = i + direction[0]
                            val nj = j + direction[1]

                            if (ni < 0 || nj < 0 || ni >= grid.size || nj >= grid[0].size) {
                                continue
                            }

                            if (grid[ni][nj] - grid[i][j] == d[i1]) {
                                val nextIndex = ni * grid[0].size + nj
                                matrix[i1][currentIndex][nextIndex] = 1
                            }
                        }
                    }
                }
            }


            if (d.size == 1) {
                dp = matrix[0]
            }
            if (d.size >= 2) {
                dp = matrixMultiply(matrix[0], matrix[1])

                for (i in 2 until d.size) {
                    dp = matrixMultiply(dp, matrix[i])
                }
            }

            val result = divideAndConquer(dp, getBinaryK(k))

            for (i in 0 until result.size) {
                for (j in 0 until result[i].size) {
                    answer = (answer + result[i][j]) % REMAINDER
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `경사로의 개수`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1,0 ,0 ),
            intArrayOf(0,0 ,0 ),
            intArrayOf(0,0 ,1 ),
            intArrayOf(1,0 ,0 ),
        ),
        intArrayOf(0, 0, 1, -1, 0, 0, 1, -1),
        10
    )
}