import kotlin.math.min

class `2차원 동전 뒤집기` {
    class Solution {
        private fun reverseCoin(coin: Int): Int {
            if (coin == 0) {
                return 1
            }
            else {
                return 0
            }
        }

        private fun reverseColLine(beginning: Array<IntArray>, colIndex: Int) {
            for (i in 0 until beginning.size) {
                beginning[i][colIndex] = reverseCoin(beginning[i][colIndex])
            }
        }

        private fun reverseRowLine(beginning: Array<IntArray>, rowIndex: Int) {
            for (i in 0 until beginning[0].size) {
                beginning[rowIndex][i] = reverseCoin(beginning[rowIndex][i])
            }
        }

        private fun matchingRowLine(beginning: Array<IntArray>, target: Array<IntArray>, rowIndex: Int): Int {
            var count = 0

            for (i in 0 until beginning[0].size) {
                if (beginning[rowIndex][i] != target[rowIndex][i]) {
                    reverseColLine(beginning, i)
                    count++
                }
            }

            return count
        }

        private fun matchingColLine(beginning: Array<IntArray>, target: Array<IntArray>, colIndex: Int): Int {
            var count = 0

            for (i in 0 until beginning.size) {
                if (beginning[i][colIndex] != target[i][colIndex]) {
                    reverseRowLine(beginning, i)
                    count++
                }
            }

            return count
        }

        private fun isAnswer(beginning: Array<IntArray>, target: Array<IntArray>): Boolean {
            for (i in 0 until beginning.size) {
                for (j in 0 until beginning[0].size) {
                    if (beginning[i][j] != target[i][j]) {
                        return false
                    }
                }
            }

            return true
        }

        private fun reverseFirstRowLine(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var count = 0

            count += matchingRowLine(beginning, target, 0)

            for (i in 1 until beginning.size) {
                if (beginning[i][0] != target[i][0]) {
                    reverseRowLine(beginning, i)
                    count += 1
                }
            }

            if (isAnswer(beginning, target)) {
                return count
            }
            else {
                return -1
            }
        }

        private fun reverseLastRowLine(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var count = 0

            count += matchingRowLine(beginning, target, beginning.size - 1)

            for (i in beginning.size - 2 downTo 0) {
                if (beginning[i][0] != target[i][0]) {
                    reverseRowLine(beginning, i)
                    count += 1
                }
            }

            if (isAnswer(beginning, target)) {
                return count
            }
            else {
                return -1
            }
        }

        private fun reverseFirstColLine(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var count = 0

            count += matchingColLine(beginning, target, 0)

            for (i in 1 until beginning[0].size) {
                if (beginning[0][i] != target[0][i]) {
                    reverseColLine(beginning, i)
                    count += 1
                }
            }

            if (isAnswer(beginning, target)) {
                return count
            }
            else {
                return -1
            }
        }

        private fun reverseLastColLine(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var count = 0

            count += matchingColLine(beginning, target, beginning[0].size - 1)

            for (i in beginning[0].size - 2 downTo 0) {
                if (beginning[0][i] != target[0][i]) {
                    reverseColLine(beginning, i)
                    count += 1
                }
            }

            if (isAnswer(beginning, target)) {
                return  count
            }
            else {
                return -1
            }
        }

        fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
            var answer = -1

            val func = listOf(
                ::reverseFirstRowLine,
                ::reverseFirstColLine,
            )

            for (fn in func) {
                val copy = beginning.map {it.copyOf()}.toTypedArray()
                val result = fn(copy, target)

                if (result != -1) {
                    answer = if (answer == -1) result else min(answer, result)
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `2차원 동전 뒤집기`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(0,0,1,0,0),
            intArrayOf(1,0,0,0,0),
            intArrayOf(0,0,0,0,0),
            intArrayOf(0,0,0,0,0),
            intArrayOf(0,0,0,0,0),
        ),
        arrayOf(
            intArrayOf(0,1,0,1,1),
            intArrayOf(0,0,0,0,0),
            intArrayOf(1,0,0,0,0),
            intArrayOf(1,0,0,0,0),
            intArrayOf(1,0,0,0,0),
        )
    )
}