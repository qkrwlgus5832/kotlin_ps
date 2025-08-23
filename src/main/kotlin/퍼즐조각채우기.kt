import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

class 퍼즐조각채우기 {
    class Solution {
        val direction = arrayOf(intArrayOf(0, -1), intArrayOf(0, 1), intArrayOf(-1, 0), intArrayOf(1, 0))
        val blockCheck = Array<Array<Boolean>>(51) { Array<Boolean>(51) { false } }
        val blankCheck = Array<Array<Boolean>>(51) { Array<Boolean>(51) { false } }
        val blockMap = HashMap<List<Pair<Int, Int>>, Int>()

        var answer = 0

        private fun normalization(coordiList: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
            var minX = Int.MAX_VALUE
            var minY = Int.MAX_VALUE

            val resultList = mutableListOf<Pair<Int, Int>>()

            coordiList.forEach {
                minX = min(minX, it.first)
                minY = min(minY, it.second)
            }

            coordiList.forEach {
                val norX = it.first - minX
                val norY = it.second - minY

                resultList.add(Pair(norX, norY))
            }

            return resultList.sortedWith(compareBy({it.first}, {it.second}))
        }

        private fun BFS(i: Int, j: Int, table: Array<IntArray>) {
            blockCheck[i][j] = true

            val queue = LinkedList<Pair<Int, Int>>()

            val list = mutableListOf<Pair<Int, Int>>()

            list.add(Pair(i, j))
            queue.add(Pair(i, j))

            while (queue.size > 0) {
                val front = queue.poll()

                for (i1 in 0 until direction.size) {
                    val ni = front.first + direction[i1][0]
                    val nj = front.second + direction[i1][1]

                    if (ni < 0 || nj < 0 || ni >= table.size || nj >= table[0].size) {
                        continue
                    }

                    if (!blockCheck[ni][nj] && table[ni][nj] == 1) {
                        blockCheck[ni][nj] = true
                        list.add(Pair(ni, nj))
                        queue.add(Pair(ni, nj))
                    }

                }
            }

            val normalizeList = normalization(list)

            blockMap[normalizeList] = blockMap.getOrDefault(normalizeList, 0) + 1
        }

        private fun rotate(block: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
            val result = mutableListOf<Pair<Int, Int>>()

            var maxX = Int.MIN_VALUE

            block.forEach {
                maxX = max(maxX, it.first)
            }

            block.forEach {
                result.add(Pair(it.second, maxX - it.first))
            }

            return result.sortedWith(compareBy({it.first}, {it.second}))
        }

        private fun getBlockBlank(gameBoard: Array<IntArray>, i: Int, j: Int): List<Pair<Int, Int>> {
            val queue = LinkedList<Pair<Int, Int>>()

            val list = mutableListOf<Pair<Int, Int>>()

            queue.add(Pair(i, j))
            blankCheck[i][j] = true
            var count = 1
            list.add(Pair(i, j))

            while (queue.size > 0) {
                val front = queue.poll()

                for (i1 in 0 until direction.size) {
                    val ni = front.first + direction[i1][0]
                    val nj = front.second + direction[i1][1]

                    if (ni < 0 || nj < 0 || ni >= gameBoard.size || nj >= gameBoard[0].size) {
                        continue
                    }

                    if (gameBoard[ni][nj] == 0 && blankCheck[ni][nj] == false) {
                        blankCheck[ni][nj] = true
                        queue.add(Pair(ni, nj))
                        list.add(Pair(ni, nj))
                        count++
                    }
                }
            }

            return normalization(list)
        }

        fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
            var answer: Int = 0

            var blankArray: Array<List<Pair<Int, Int>>> = arrayOf()

            for (i in 0 until table.size) {
                for (j in 0 until table[i].size) {
                    if (table[i][j] == 1 && blockCheck[i][j] == false) {
                        BFS(i, j, table)
                    }
                }
            }

            for (i in 0 until game_board.size) {
                for (j in 0 until game_board[i].size) {
                    if (game_board[i][j] == 0 && blankCheck[i][j] == false) {
                        blankArray += getBlockBlank(game_board, i, j)
                    }
                }
            }

            for (i in 0 until blankArray.size) {
                var currentBlankArray = blankArray[i]

                for (i1 in 0 until 4) {
                    if (blockMap.getOrDefault(currentBlankArray, 0) > 0) {
                        blockMap[currentBlankArray] = blockMap.getOrDefault(currentBlankArray, 0) - 1
                        answer += currentBlankArray.size
                        break
                    } else {
                        currentBlankArray = rotate(currentBlankArray)
                    }
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = 퍼즐조각채우기.Solution()
    solution.solution(
        arrayOf(intArrayOf(0, 0, 0),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 1, 1),
        ),
        arrayOf(intArrayOf(1, 1, 1),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 0, 0),
    ))
}
