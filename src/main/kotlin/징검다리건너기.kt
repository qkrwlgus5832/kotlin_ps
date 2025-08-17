import java.util.*

class 징검다리건너기 {
    class Solution {
        private fun isAnswer(stones: IntArray, k: Int, standard: Int): Boolean {
            var count = 0
            stones.forEach {
                if (it - standard <= 0) {
                    count++
                }
                else {
                    if (count > k) {
                        return false
                    }
                    count = 0
                }
            }
            return true
        }

        private fun binarySearch(left: Int, right: Int, stones: IntArray, k: Int): Int {
            if (left == right) {
                return left
            }

            if (left + 1 == right) {
                if (isAnswer(stones, k, right)) {
                    return right
                }
                else {
                    return left
                }
            }
            val middle = (left + right) / 2

            return if (isAnswer(stones, k, middle)) {
                binarySearch(middle, right, stones, k)
            } else {
                binarySearch(left, middle - 1, stones, k)
            }
        }

        fun solution(stones: IntArray, k: Int): Int {
            val stoneMax = stones.maxOrNull()!!
            return binarySearch(0, stoneMax, stones, k)
        }
    }
}

fun main() {
    val solution = 징검다리건너기.Solution()
    System.out.println(solution.solution(intArrayOf(2, 4, 5, 3, 2, 1, 4, 2, 5, 1), 3))
}