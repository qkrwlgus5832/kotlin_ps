import kotlin.math.min

class `블록 게임` {
    class Solution {
        private fun down(board: Array<IntArray>, currentTurn: Int) {
            for (j in 0 until board[0].size) {
                var maxBlankIndex = board.size - 1

                for (i in board.size -1 downTo 0) {
                    if (board[i][j] > 0) {
                        maxBlankIndex = i - 1
                    }
                }

                for (i in maxBlankIndex downTo 0) {
                    board[i][j] = currentTurn * - 1
                }
            }
        }

        private fun isRectangle(start: Pair<Int, Int>, board: Array<IntArray>, currentTurn: Int, rowSize: Int, colSize: Int): Boolean {
            var blockIndex: Int? = null
            var blockCount: Int = 0

            for (i in 0 until rowSize) {
                for (j in 0 until colSize) {
                    val nexti = start.first + i
                    val nextj = start.second + j

                    if (nexti < 0 || nextj < 0 || nexti >= board.size || nextj >= board[0].size) {
                        return false
                    }

                    if (board[nexti][nextj] < 0 && board[nexti][nextj] != currentTurn) {
                        return false
                    }

                    if (board[nexti][nextj] > 0) {
                        if (blockIndex == null) {
                            blockIndex = board[nexti][nextj]
                        }
                        else if (blockIndex != board[nexti][nextj]) {
                            return false
                        }
                        blockCount++
                    }

                    if (board[nexti][nextj] == 0) {
                        return false
                    }
                }
            }

            return blockCount == 4
        }

        private fun popRectangle(board: Array<IntArray>, currentTurn: Int, start: Pair<Int, Int>, rowSize: Int, colSize: Int) {
            for (i in 0 until rowSize) {
                for (j in 0 until colSize) {
                    val nexti = start.first + i
                    val nextj = start.second + j

                    board[nexti][nextj] = 0
                }
            }
        }

        private fun pop(board: Array<IntArray>, currentTurn: Int, start: Pair<Int, Int>): Boolean {
            if (isRectangle(start, board, currentTurn * -1, 3, 2)) {
                popRectangle(board, currentTurn, start, 3, 2)
                return true
            }
            else if (isRectangle(start, board, currentTurn * - 1, 2, 3)) {
                popRectangle(board, currentTurn, start, 2, 3)
                return true
            }

            return false
        }

        fun solution(board: Array<IntArray>): Int {
            var currentTurn = 1

            var answer = 0

            while(true) {
                down(board, currentTurn)

                var flag = false

                for (i in 0 until board.size) {
                    for (j in 0 until board[0].size) {
                        val result = pop(board, currentTurn, Pair(i, j))
                        answer += if (result) 1 else 0
                        flag = flag || result
                    }
                }

                if (flag == false) {
                    break
                }
                currentTurn++
            }

            return answer
        }
    }
}

fun main() {
    val solution = `블록 게임`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,0,0,0,0),
            intArrayOf(0,0,0,0,0,0,4,0,0,0),
            intArrayOf(0,0,0,0,0,4,4,0,0,0),
            intArrayOf(0,0,0,0,3,0,4,0,0,0),
            intArrayOf(0,0,0,2,3,0,0,0,5,5),
            intArrayOf(1,2,2,2,3,3,0,0,0,5),
            intArrayOf(1,1,1,0,0,0,0,0,0,5)
        )
    )
}