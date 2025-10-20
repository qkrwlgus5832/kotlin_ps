import java.util.LinkedList
import kotlin.math.absoluteValue

class `블록 이동하기` {
    class Solution {
        data class Robot(
            val x: Int,
            val y: Int,
            val direction: Direction
        )

        enum class Direction(val value: Int) {
            HORIZON(0),
            VERTICAL(1)
        }

        private val check = Array(101) {
            Array(101) {
                BooleanArray(2)
            }
        }

        private val dir = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private fun checkAndQueuePushForMove(board: Array<IntArray>, x: Int, y: Int, direction: Direction, queue: LinkedList<Robot>) {
            if (direction == Direction.HORIZON) {
                if (board[x][y] == 0 && y + 1 < board[0].size && board[x][y + 1] == 0 && !check[x][y][direction.value]) {
                    check[x][y][direction.value] = true
                    queue.add(Robot(x,y,direction))
                }
            }

            else if (direction == Direction.VERTICAL) {
                if (board[x][y] == 0 && x + 1 < board.size && board[x + 1][y] == 0 && !check[x][y][direction.value]) {
                    check[x][y][direction.value] = true
                    queue.add(Robot(x, y, direction))
                }
            }
        }

        private fun rotateForHorizontal(board: Array<IntArray>, x: Int, y: Int, direction: Direction): List<Robot> {
            val result = mutableListOf<Robot>()

            if (x + 1 < board.size && y + 1 < board[0].size && board[x + 1][y] == 0 && board[x+1][y+1] == 0) {
                result.add(Robot(x, y + 1, Direction.VERTICAL))
            }

            if (x - 1 >=0 && y + 1 < board[0].size && board[x-1][y + 1] == 0 && board[x-1][y] == 0) {
                result.add(Robot(x - 1, y, Direction.VERTICAL))
            }

            if (x - 1 >= 0 && y + 1 < board[0].size && board[x-1][y] == 0 && board[x-1][y+1] == 0) {
                result.add(Robot(x - 1, y + 1, Direction.VERTICAL))
            }

            if (x + 1 < board.size && y + 1 < board[0].size && board[x + 1][y + 1] == 0 && board[x + 1][y] == 0) {
                result.add(Robot(x, y, Direction.VERTICAL))
            }

            return result
        }

        private fun rotateForVertical(board: Array<IntArray>, x: Int, y: Int, direction: Direction): List<Robot> {
            val result = mutableListOf<Robot>()

            if (x + 1 < board.size && y + 1 < board.size && board[x+1][y+1] == 0 && board[x][y+1] == 0) {
                result.add(Robot(x, y, Direction.HORIZON))
            }

            if (x + 1 < board.size && y - 1 >=0 && board[x][y-1] == 0 && board[x + 1][y-1] == 0) {
                result.add(Robot(x + 1, y- 1, Direction.HORIZON))
            }

            if (x + 1 < board.size && y + 1 < board[0].size && board[x][y+1] == 0 && board[x+1][y+1] == 0) {
                result.add(Robot(x + 1, y, Direction.HORIZON))
            }

            if (x + 1 < board.size && y - 1 >= 0 && board[x + 1][y-1] == 0 && board[x][y-1] == 0) {
                result.add(Robot(x, y- 1, Direction.HORIZON))
            }

            return result
        }

        private fun checkAndQueuePushForRotate(board: Array<IntArray>, x: Int, y: Int, direction: Direction, queue: LinkedList<Robot>){
            val list = if (direction == Direction.HORIZON) {
                rotateForHorizontal(board, x, y, direction)
            }
            else {
                rotateForVertical(board, x, y, direction)
            }

            list.forEach { robot ->
                if (check[robot.x][robot.y][robot.direction.value] == false) {
                    check[robot.x][robot.y][robot.direction.value] = true
                    queue.add(robot)
                }
            }
        }

        private fun isEnd(board: Array<IntArray>, x: Int, y: Int, direction: Direction): Boolean {
            val endX = board.size - 1
            val endY = board[0].size - 1

            if (x == endX && y == endY - 1 && direction == Direction.HORIZON) {
                return true
            }
            else if (x == endX - 1 && y == endY && direction == Direction.VERTICAL) {
                return true
            }

            return false
        }

        private fun BFS(board: Array<IntArray>, x: Int, y: Int, direction: Direction): Int {
            val queue = LinkedList<Robot>()

            queue.add(Robot(x, y, direction))

            check[x][y][direction.value] = true

            var level = 0

            while(queue.size > 0) {
                val qSize = queue.size
                for (i1 in 0 until qSize) {
                    val front = queue.poll()

                    if (isEnd(board, front.x, front.y, front.direction)) {
                        return level
                    }

                    for (i in 0 until dir.size) {
                        val nx = front.x + dir[i][0]
                        val ny = front.y + dir[i][1]

                        if (nx < 0 || ny < 0 || nx >= board.size || ny >= board[0].size) {
                            continue
                        }

                        checkAndQueuePushForMove(board, nx, ny, front.direction, queue)
                    }

                    checkAndQueuePushForRotate(board, front.x, front.y, front.direction, queue)
                }
                level++
            }

            return - 1
        }

        fun solution(board: Array<IntArray>): Int {
            return BFS(board, 0, 0, Direction.HORIZON)
        }
    }
}

fun main() {
    val solution = `블록 이동하기`.Solution()
    println(solution.solution(
        arrayOf(
            intArrayOf(0, 0, 0, 1, 1),
            intArrayOf(0, 0, 0, 1, 0),
            intArrayOf(0, 1, 0, 1, 1),
            intArrayOf(1, 1, 0, 0, 1),
            intArrayOf(0, 0, 0, 0, 0)
        )
    ))
}