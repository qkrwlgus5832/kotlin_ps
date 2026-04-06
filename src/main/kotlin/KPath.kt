import java.util.*

class `KPath` {
    class Solution {
        companion object {
            private const val REMAIN_NUMBER = 1000000007
        }

        fun getBinaryString(k: Int): String {
            val resultString = StringBuilder()

            var current = 1

            while (current < k) {
                current *= 2
            }

            if (current > k) {
                current /= 2
            }

            var tmpK = k

            while (tmpK > 0 || current > 0) {
                if (tmpK >= current) {
                    resultString.append("1")
                    tmpK -= current
                } else {
                    resultString.append("0")
                }

                current /= 2
            }

            return resultString.toString()
        }

        private fun matrixMultiply(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray> {
            val newMatrix = Array(matrix1.size) {
                IntArray(matrix2[0].size) {
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

        private fun divideAndConquer(matrix: Array<IntArray>, k: Int): Array<IntArray> {
            val binaryString = getBinaryString(k)

            var flag = false

            var result = arrayOf(intArrayOf())

            var current = matrix

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

            return result
        }


        fun solution(matrix: Array<IntArray>, k: Int): Int {
            val resultArray = divideAndConquer(matrix, k)

            var answer = 0

            for (i in 0 until resultArray.size) {
                for (j in 0 until resultArray[i].size) {
                    answer = ((resultArray[i][j].toLong() + answer) % REMAIN_NUMBER).toInt()
                }
            }

            return answer
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val k = nextInt()

    val matrix = Array(n) {
        IntArray(n) { 0 }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val tmp = nextInt()
            matrix[i][j] = tmp
        }
    }

    val solution = KPath.Solution()
    println(solution.solution(matrix, k))
}