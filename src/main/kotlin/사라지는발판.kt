import java.util.HashMap
import kotlin.math.max
import kotlin.math.min

class 사라지는발판 {
    class Solution {
        data class Node(
            val board: List<List<Int>>,
            val aloc: Int,
            val bloc: Int,
            val isATurn: Boolean
        )

        val map = HashMap<Node, Pair<Boolean, Int>>()

        val direction: Array<IntArray> = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private fun getDimension(coordi: IntArray, board: List<List<Int>>): Int {
            return coordi[0] * board[0].size + coordi[1]
        }

        private fun isSame(board: List<List<Int>>, board2: List<List<Int>>): Boolean {
            for (i in 0 until board.size) {
                for (j in 0 until board[i].size) {
                    if (board[i][j] != board2[i][j]) {
                        return false
                    }
                }
            }
            return true
        }

        private fun MutableList<MutableList<Int>>.copy(): MutableList<MutableList<Int>> {
            return this.map {
                it.toMutableList()
            }.toMutableList()
        }

        private fun recursion(
            board: MutableList<MutableList<Int>>,
            isATurn: Boolean,
            aloc: IntArray,
            bloc: IntArray
        ): Pair<Boolean, Int> {
            if (isATurn && board[aloc[0]][aloc[1]] == 0) {
                return Pair(false, 0)
            }

            if (!isATurn && board[bloc[0]][bloc[1]] == 0) {
                return Pair(false, 0)
            }

            if (isATurn) {
                var isWin = false
                var minCount = Int.MAX_VALUE
                var maxCount = -1

                for (i in 0 until 4) {
                    val newX = aloc[0] + direction[i][0]
                    val newY = aloc[1] + direction[i][1]

                    if (newX < 0 || newY < 0 || newX >= board.size || newY >= board[0].size) {
                        continue
                    }

                    if (board[newX][newY] == 1) {
                        board[aloc[0]][aloc[1]] = 0

                        val node =
                            Node(board.copy(), getDimension(intArrayOf(newX, newY), board), getDimension(bloc, board), false)

                        if (!map.containsKey(node)) {
                            map[node] = recursion(board, false, intArrayOf(newX, newY), bloc)
                        }

                        if (!map[node]!!.first) {
                            isWin = true
                            minCount = min(minCount, map[node]!!.second)
                        } else {
                            maxCount = max(maxCount, map[node]!!.second)
                        }

                        board[aloc[0]][aloc[1]] = 1
                    }

                }

                if (isWin) {
                    map[Node(
                        board.copy(),
                        getDimension(aloc, board),
                        getDimension(bloc, board),
                        isATurn
                    )] = Pair(isWin, minCount + 1)
                    return Pair(isWin, minCount + 1)
                } else {
                    map[Node(
                        board.copy(),
                        getDimension(aloc, board),
                        getDimension(bloc, board),
                        isATurn
                    )] = Pair(isWin, maxCount + 1)
                    return Pair(isWin, maxCount + 1)
                }
            } else {
                var isWin = false
                var minCount = Int.MAX_VALUE
                var maxCount = -1

                for (i in 0 until 4) {
                    val newX = bloc[0] + direction[i][0]
                    val newY = bloc[1] + direction[i][1]

                    if (newX < 0 || newY < 0 || newX >= board.size || newY >= board[0].size) {
                        continue
                    }

                    if (board[newX][newY] == 1) {
                        board[bloc[0]][bloc[1]] = 0

                        val node = Node(
                            board.copy(),
                            getDimension(aloc, board),
                            getDimension(intArrayOf(newX, newY), board),
                            true
                        )

                        if (!map.containsKey(node)) {
                            map[node] = recursion(board, true, aloc, intArrayOf(newX, newY))
                        }

                        if (!map[node]!!.first) {
                            isWin = true
                            minCount = min(minCount, map[node]!!.second)
                        } else {
                            maxCount = max(maxCount, map[node]!!.second)
                        }

                        board[bloc[0]][bloc[1]] = 1
                    }

                }
                if (isWin) {
                    map[Node(board.copy(), getDimension(aloc, board), getDimension(bloc, board), isATurn)] =
                        Pair(isWin, minCount + 1)
                    return Pair(isWin, minCount + 1)
                } else {
                    map[Node(board.copy(), getDimension(aloc, board), getDimension(bloc, board), isATurn)] =
                        Pair(isWin, maxCount + 1)
                    return Pair(isWin, maxCount + 1)
                }
            }
        }

        fun solution(board: Array<IntArray>, aloc: IntArray, bloc: IntArray): Int {
            val answer = recursion(board.map {
                it.toMutableList()
            }.toMutableList(), true, aloc, bloc)

            return answer.second
        }
    }

}

fun main() {
    val solution = 사라지는발판.Solution()
    println(
        solution.solution(
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)
            ),
            intArrayOf(1, 0),
            intArrayOf(1, 2),
        )
    )
}