import java.util.*

class `본대 산책2` {
    class Solution {
        private val matrix = Array(8) {
            IntArray(8) { 0 }
        }

        companion object {
            private const val REMAIN_NUMBER = 1000000007
        }

        private val campusMap = mutableMapOf(
            "정보과학관" to 0,
            "전산관" to 1,
            "미래관" to 2,
            "신양관" to 3,
            "한경직기념관" to 4,
            "진리관" to 5,
            "형남공학관" to 7,
            "학생회관" to 6,
        )

        private fun edgeConnect(campus1: String, campus2: String) {
            val campus1Index = campusMap[campus1]!!
            val campus2Index = campusMap[campus2]!!

            matrix[campus1Index][campus2Index] = 1
            matrix[campus2Index][campus1Index] = 1
        }

        private fun init() {
            edgeConnect("정보과학관", "전산관")
            edgeConnect("정보과학관", "미래관")
            edgeConnect("전산관", "미래관")
            edgeConnect("전산관", "신양관")
            edgeConnect("신양관", "미래관")

            edgeConnect("신양관", "한경직기념관")
            edgeConnect("신양관", "진리관")
            edgeConnect("진리관", "한경직기념관")
            edgeConnect("미래관", "한경직기념관")

            edgeConnect("진리관", "학생회관")
            edgeConnect("한경직기념관", "형남공학관")
            edgeConnect("학생회관", "형남공학관")
        }

        private fun matrixMultiply(matrix1: Array<IntArray>, matrix2: Array<IntArray>): Array<IntArray> {
            val newMatrix = Array(8) {
                IntArray(8) {
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

        fun getBinaryString(d: Int): String {
            val resultString = StringBuilder()

            var current = 1

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

        private fun divideAndConquer(d: Int, matrix: Array<IntArray>): Array<IntArray> {
            val binaryString = getBinaryString(d)

            var result = Array(8) {
                IntArray(8) {
                    0
                }
            }

            var resultFlag = false

            var current = matrix

            for (i in binaryString.length -1 downTo 0) {
                if (binaryString[i] == '1') {
                    if (!resultFlag) {
                        resultFlag = true
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

        private fun getAnswer(d: Int): Int {
            val index = campusMap["정보과학관"]!!
            val resultMatrix = divideAndConquer(d, this.matrix)


            return resultMatrix[index][index]
        }

        fun solution(d: Int): Int {
            init()
            return getAnswer(d)
        }
    }
}

fun main() = with(Scanner(System.`in`)){
    val d = nextInt()
    val solution = `본대 산책2`.Solution()
    println(solution.solution(d))
}