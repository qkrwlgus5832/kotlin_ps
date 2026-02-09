import java.util.*

class `피보나치 수 6` {
    class Solution {
        companion object {
            private const val REMAIN_NUMBER = 1000000007
        }


        fun getBinaryString(d: Long): String {
            val resultString = StringBuilder()

            var current = 1.toLong()

            while (current < d) {
                current *= 2
            }

            if (current > d) {
                current /= 2
            }

            var tmpD = d

            while (tmpD > 0 || current > 0) {
                if (tmpD >= current) {
                    resultString.append("1")
                    tmpD -= current
                } else {
                    resultString.append("0")
                }

                current /= 2
            }

            return resultString.toString()
        }

        private fun matrixMultiply(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray> {
            val newMatrix = Array(2) {
                IntArray(2) {
                    0
                }
            }

            for (i in 0 until matrix1.size) {
                for (i1 in 0 until matrix2[0].size) {
                    var sum = 0L

                    for (j in 0 until matrix1[0].size) {
                        sum += ((matrix1[i][j] % REMAIN_NUMBER).toLong() * (matrix2[j][i1] % REMAIN_NUMBER)) % REMAIN_NUMBER
                        sum %= REMAIN_NUMBER
                    }

                    newMatrix[i][i1] = sum.toInt()
                }
            }

            return newMatrix
        }

        private fun divideAndConquer(group: Long): Array<IntArray> {
            val binaryString = getBinaryString(group)

            var flag = false

            var result = arrayOf(
                intArrayOf(1, 0),
                intArrayOf(0, 1)
            )

            var current = arrayOf(
                intArrayOf(2, 3),
                intArrayOf(3, 5)
            )

            for (i in binaryString.length - 1 downTo 0) {
                if (binaryString[i] == '1') {
                    if (flag == false) {
                        flag = true
                        result = current
                    }
                    else {
                        result = matrixMultiply(result, current)
                    }
                }
                current = matrixMultiply(current, current)
            }

            return matrixMultiply(
                arrayOf(
                    intArrayOf(0, 1),
                    intArrayOf(1, 2)
                ), result
            )
        }

        fun solution(n: Long): Int {
            val group = (n) / 4
            val index = (n) % 4

            val resultArray = divideAndConquer(group)

            if (index <= 1) {
                return resultArray[0][index.toInt()]
            }
            return resultArray[1][index.toInt() - 2]
        }
    }
}

fun main() = with(Scanner(System.`in`)){
    val n = nextLong()
    val solution = `피보나치 수 6`.Solution()
    println(solution.solution(n))
}