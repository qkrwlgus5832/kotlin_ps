import java.util.LinkedList
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.max

class `숫자 타자 대회` {
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

        private fun setLeftMinCost(numbers: String, left: Int, right: Int, index: Int, nextNumber: Int) {
            if (index >=0 && left == numbers.get(index) - '0') {
                for (i in 0..9) {
                    if (dpArray[left][i][index] == -1 || left == i) {
                        continue
                    }
                    val result =  dpArray[left][i][index] + findCost(i, nextNumber)
                    dpArray[left][nextNumber][index + 1] = if (dpArray[left][nextNumber][index + 1] == -1) result else min(dpArray[left][nextNumber][index + 1], result)

                    val result2 = dpArray[left][i][index] + findCost(left, nextNumber)
                    dpArray[nextNumber][i][index + 1] = if (dpArray[nextNumber][i][index+1] == -1) result2 else min(dpArray[nextNumber][i][index + 1], result2)
                }
            }
            else if (index < 0) {
                dpArray[nextNumber][right][index + 1] = findCost(left, nextNumber)
            }
        }

        private fun setRightMinCost(numbers: String, left: Int, right: Int, index: Int, nextNumber: Int) {
            if (index >=0 && right == numbers.get(index) - '0') {
                for (i in 0..9) {
                    if (dpArray[i][right][index] == -1 || right == i) {
                        continue
                    }
                    val result =  dpArray[i][right][index] + findCost(i, nextNumber)
                    dpArray[nextNumber][right][index + 1] = if (dpArray[nextNumber][right][index + 1] == -1) result else min(dpArray[nextNumber][right][index + 1], result)

                    val result2 = dpArray[i][right][index] + findCost(right, nextNumber)
                    dpArray[i][nextNumber][index + 1] = if (dpArray[i][nextNumber][index+1] == -1) result2 else min(dpArray[i][nextNumber][index + 1], result2)
                }
            } else if (index < 0) {
                dpArray[left][nextNumber][index + 1] = findCost(right, nextNumber)
            }
        }

        private fun BFS(numbers: String, currentLeft: Int, currentRight: Int) {
            val queue = LinkedList<Node>()

            queue.add(Node(currentLeft, currentRight, -1, 0))

            while (queue.size > 0) {
                val front = queue.poll()

                if (front.left == front.right) {
                    continue
                }

                if (front.index + 1 == numbers.length) {
                    continue
                }

                val nextNum = numbers.get(front.index + 1) - '0'
                var currentCost = findCost(front.left, nextNum)

                if (front.index < 0 || !bfsCheck[front.left][front.right][front.index]) {
                    if (front.index >=0 ) bfsCheck[front.left][front.right][front.index] = true

                    setLeftMinCost(numbers, front.left, front.right, front.index, nextNum)
                    setRightMinCost(numbers, front.left, front.right, front.index, nextNum)

                    queue.add(Node(nextNum, front.right, front.index + 1, currentCost))
                    queue.add(Node(front.left, nextNum, front.index + 1, currentCost))

                }
            }

        }

        fun solution(numbers: String): Int {
            var answer: Int = Int.MAX_VALUE

            BFS(numbers, 4, 6)

            val last = numbers.last() - '0'

            for (i in 0..9) {
                val minValue = dpArray[i][last][numbers.length - 1]
                if (minValue != -1 && i != last) {
                    answer = min(answer, minValue)
                }
            }


            for (i in 0..9) {
                val minValue = dpArray[last][i][numbers.length - 1]
                if (minValue != -1 && i != last) {
                    answer = min(answer, minValue)
                }
            }


            return answer
        }
    }
}

fun main() {
    val solution = `숫자 타자 대회`.Solution()
    println(solution.solution("101"))
}

