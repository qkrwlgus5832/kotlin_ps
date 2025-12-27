import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

class `숫자 타자 대회(루프버전)` {
    class Solution {
        val dpArray = Array(11) {
            Array(11) {
                Array(100001) {
                    -1
                }
            }
        }

        val bfsCheck = Array(11) {
            Array(11) {
                Array(100001) {
                    false
                }
            }
        }

        val costArray = Array(11) {
            Array(11) {
                -1
            }
        }

        val numberPad = arrayOf (
            charArrayOf('1', '2', '3'),
            charArrayOf('4', '5', '6'),
            charArrayOf('7', '8', '9'),
            charArrayOf('*', '0', '#')
        )

        private fun findDirection(x: Char): Pair<Int, Int> {
            for (i in 0 until numberPad.size) {
                for (j in 0 until numberPad[i].size) {
                    if (numberPad[i][j] == x) {
                        return Pair(i, j)
                    }
                }
            }

            return Pair(-1, -1)
        }

        private fun findCost(x: Int, y: Int): Int {
            if (costArray[x][y] != -1) {
                return costArray[x][y]
            }

            val directionX = findDirection(x.digitToChar())
            val directionY = findDirection(y.digitToChar())

            if (directionX == directionY) {
                return 1
            }

            val gapRow = abs(directionX.first - directionY.first)
            val gapCol = abs(directionX.second - directionY.second)

            if (gapRow == 0 || gapCol == 0) {
                return (gapRow + gapCol) * 2
            }

            val cost = 3 * min(gapRow, gapCol) + ((max(gapRow, gapCol) - min(gapRow, gapCol)) * 2)
            costArray[x][y] = cost
            costArray[y][x] = cost

            return cost
        }

        data class Node(
            val left: Int,
            val right: Int,
            val index: Int,
            val cost: Int
        )

        private fun setMinCost(numbers: String, left: Int, right: Int, index: Int) {
            val nextNumber = numbers.get(index - 1) - '0'

            if (dpArray[left][right][index - 1] == -1)
                return

            if (left != nextNumber) {
                val result = dpArray[left][right][index - 1] + findCost(right, nextNumber)

                if (dpArray[left][nextNumber][index] == -1) {
                    dpArray[left][nextNumber][index] = result
                }
                else {
                    dpArray[left][nextNumber][index ] = min(dpArray[left][nextNumber][index], result)
                }
            }

            if (right != nextNumber) {
                val result2 = dpArray[left][right][index - 1] + findCost(left, nextNumber)

                if (dpArray[nextNumber][right][index] == -1) {
                    dpArray[nextNumber][right][index] = result2
                }
                else {
                    dpArray[nextNumber][right][index] = min(dpArray[nextNumber][right][index], result2)
                }
            }
        }

        private fun dpdp(numbers: String) {
            dpArray[4][6][0] = 0

            for (i in 1..numbers.length) {
                for (i1 in 0..9){
                    for (j1 in 0..9) {
                        setMinCost(numbers, i1, j1, i)
                    }
                }
            }
        }

        fun solution(numbers: String): Int {
            var answer: Int = Int.MAX_VALUE

            dpdp(numbers)

            val last = numbers.last() - '0'

            for (i in 0..9) {
                val minValue1 = dpArray[i][last][numbers.length]
                val minValue2 = dpArray[last][i][numbers.length]

                if (minValue1 != -1 && i != last) {
                    answer = min(answer, minValue1)
                }

                if (minValue2 != -1 && i != last) {
                    answer = min(answer, minValue2)
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `숫자 타자 대회(루프버전)`.Solution()
    println(solution.solution("1756"))
}

