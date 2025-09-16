class `행렬의 곱셈` {
    class Solution {
        private fun getMulValue(i: Int, j: Int, arr1: Array<IntArray>, arr2: Array<IntArray>): Int {
            val arr1ColSize = arr1[0].size
            val arr2RowSize = arr2.size

            var sum = 0

            for (i1 in 0 until arr1ColSize) {
                sum += arr1[i][i1] * arr2[i1][j]
            }

            return sum
        }

        fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
            var answer = Array<IntArray>(arr1.size) {
                IntArray(arr2[0].size) {
                    0
                }
            }

            for (i in 0 until arr1.size) {
                for (j in 0 until arr2[0].size) {
                    answer[i][j] = getMulValue(i, j, arr1, arr2)
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `행렬의 곱셈`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(2, 3, 2),
            intArrayOf(4, 2, 4),
            intArrayOf(3, 1, 4)
        ),
        arrayOf(
            intArrayOf(5, 4, 3),
            intArrayOf(2, 4, 1),
            intArrayOf(3, 1, 1)
        )
    )
}