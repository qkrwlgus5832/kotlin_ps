import java.util.LinkedList
import kotlin.math.min

class 경주로건설 {
    data class Node(
        val x: Int,
        val y: Int,
        val direction: Int,
        val straight: Int,
        val corner: Int
    )

    class Solution {
        private var answer = Int.MAX_VALUE

        private val direction = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private fun BFS(board: Array<IntArray>, x: Int, y: Int) {
            val queue = LinkedList<Node>()
            val check = Array(26) {
                Array(26) {
                    Array(4) { Int.MAX_VALUE }
                }
            }

            queue.add(Node(x, y, -1, 0, 0))

            while(queue.size > 0) {
                val front = queue.poll()

                if (front.x == board.size - 1 && front.y == board[0].size - 1) {
                    answer = min(answer, front.straight * 100 + front.corner * 500)
                }

                for (i in 0 until direction.size) {
                    val newX = front.x + direction[i][0]
                    val newY = front.y + direction[i][1]
                    var straight = front.straight
                    var corner = front.corner

                    if (front.direction == -1) {
                        straight++
                    }
                    else if (front.direction != i) {
                        corner++
                        straight++
                    }
                    else {
                        straight++
                    }

                    if (newX < 0 || newY < 0 || newX >= board.size || newY >= board[0].size) {
                        continue
                    }

                    if (board[newX][newY] == 0 && check[newX][newY][i] > corner * 500 + straight * 100) {
                        check[newX][newY][i] = corner * 500 + straight * 100
                        queue.add(Node(newX, newY, i, straight, corner))
                    }
                }
            }
        }

        fun solution(board: Array<IntArray>): Int {
            BFS(board, 0, 0)

            return answer
        }
    }
}

fun main() {
    val solution = 경주로건설.Solution()
    solution.solution(arrayOf(
        intArrayOf(0, 0, 1, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 1, 0, 1),
        intArrayOf(1, 0, 0, 0)
    ))
}