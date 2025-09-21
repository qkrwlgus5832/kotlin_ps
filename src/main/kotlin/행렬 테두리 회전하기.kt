import kotlin.math.min

class `행렬 테두리 회전하기` {
    class Solution {
        fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
            var answer = IntArray(queries.size + 1)

            val matrix = Array<IntArray>(rows + 1) {
                IntArray(columns + 1) {
                    0
                }
            }

            for (i in 1..rows) {
                for (j in 1..columns) {
                    matrix[i][j] = (i - 1) * columns + j
                }
            }

            queries.forEachIndexed { index, query ->
                val point1 = Pair(query[0], query[1])
                val point2 = Pair(query[2], query[3])
                var minValue = Int.MAX_VALUE
                var beforeValue = matrix[point1.first][point1.second]

                for (i in point1.second..point2.second - 1) {
                    val tmpBeforeValue = matrix[point1.first][i+1]
                    matrix[point1.first][i + 1] = beforeValue
                    beforeValue = tmpBeforeValue

                    minValue = min(minValue, matrix[point1.first][i+1])
                }

                for (i in point1.first .. point2.first - 1) {
                    val tmpBeforeValue = matrix[i + 1][point2.second]
                    matrix[i + 1][point2.second] = beforeValue
                    beforeValue = tmpBeforeValue

                    minValue = min(minValue, matrix[i+1][point2.second])
                }

                for (i in point2.second downTo point1.second + 1) {
                    val tmpBeforeValue = matrix[point2.first][i-1]
                    matrix[point2.first][i-1] = beforeValue
                    beforeValue = tmpBeforeValue

                    minValue = min(minValue, matrix[point2.first][i-1])
                }

                for (i in point2.first downTo point1.first + 1) {
                    val tmpBeforeValue = matrix[i-1][point1.second]
                    matrix[i-1][point1.second] = beforeValue
                    beforeValue = tmpBeforeValue

                    minValue = min(minValue, matrix[i-1][point1.second])
                }

                answer[index] = minValue
            }

            return answer
        }
    }
}

fun main() {
    val solution = `행렬 테두리 회전하기`.Solution()
    solution.solution(
        100, 97, arrayOf(
            intArrayOf(1, 1, 100, 97),
        )
    )
}