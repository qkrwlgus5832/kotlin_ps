import kotlin.collections.ArrayDeque

class `행렬과 연산` {
    class Solution {
        fun solution(rc: Array<IntArray>, operations: Array<String>): Array<IntArray> {
            var answer: Array<IntArray> = Array(rc.size) {
                IntArray(rc[0].size)
            }

            val leftSide = ArrayDeque<Int>()
            val rightSide = ArrayDeque<Int>()

            val middleDequeList = ArrayDeque<ArrayDeque<Int>>()

            for (i in 0 until rc.size) {
                val tmpDeque = ArrayDeque<Int>()

                for (j in 0 until rc[i].size) {
                    if (j == 0) {
                        leftSide.add(rc[i][j])
                    }
                    else if (j == rc[0].size -1) {
                        rightSide.add(rc[i][j])
                    }
                    else {
                        tmpDeque.add(rc[i][j])
                    }
                }
                middleDequeList.add(tmpDeque)
            }

            for (operation in operations) {
                if (operation == "Rotate") {
                    middleDequeList[0].addFirst(leftSide.removeFirst())
                    rightSide.addFirst(middleDequeList[0].removeLast())
                    middleDequeList.last().addLast(rightSide.removeLast())
                    leftSide.addLast(middleDequeList.last().removeFirst())
                }

                else if (operation == "ShiftRow") {
                    leftSide.addFirst(leftSide.removeLast())
                    rightSide.addFirst(rightSide.removeLast())
                    middleDequeList.addFirst(middleDequeList.removeLast())
                }
            }

            for (i in 0 until rc.size) {
                for (j in 0 until rc[i].size) {
                    if (j == 0) {
                        answer[i][j] = leftSide[i]
                    }
                    else if (j == rc[0].size - 1) {
                        answer[i][j] = rightSide[i]
                    }
                    else {
                        answer[i][j] = middleDequeList[i][j - 1]
                    }

                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `행렬과 연산`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(1, 2, 3, 4),
            intArrayOf(5, 6, 7, 8),
            intArrayOf(9, 10, 11, 12)
        ),
        arrayOf(
            "ShiftRow", "Rotate", "ShiftRow", "Rotate"
        )
    )
}