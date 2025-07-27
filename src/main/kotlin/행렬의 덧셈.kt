class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val row = arr1.size
        val col = arr1[0].size

        val answer = Array(row) { IntArray(col) }

        for ((i, value) in arr1.withIndex()) {
            for ((j, value) in arr1[0].withIndex()) {
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        return answer
    }
}

fun main(args: Array<String>) {
    val solution = Solution();
    solution.solution(arrayOf(
        intArrayOf(1),
        intArrayOf(2)
    ), arrayOf( intArrayOf(3), intArrayOf(4)))
}