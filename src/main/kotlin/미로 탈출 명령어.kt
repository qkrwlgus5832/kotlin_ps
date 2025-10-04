import java.util.*

class `미로 탈출 명령어` {
    class Solution {
        data class Node(
            val x: Int,
            val y: Int,
            val path: StringBuilder
        )

        private val dir = arrayOf(
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1),
            intArrayOf(-1, 0)
        )

        private fun getPathChar(dirIndex: Int): Char {
            if (dirIndex == 0) {
                return 'd'
            }
            else if (dirIndex == 1) {
                return 'l'
            }
            else if (dirIndex == 2) {
                return 'r'
            }
            else if (dirIndex == 3) {
                return 'u'
            }
            return '-'
        }

        private fun BFS(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
            val queue = LinkedList<Node>()
            val check = Array(n) {
                Array(m) {
                    BooleanArray(k)
                }
            }

            queue.add(Node(x, y, StringBuilder()))
            check[x][y][0]= true

            var level = 0

            while(level < k) {
                val qSize = queue.size

                for (i in 0 until qSize) {
                    val front = queue.poll()

                    for (i in 0 until dir.size) {
                        val nx = front.x + dir[i][0]
                        val ny = front.y + dir[i][1]

                        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                            continue
                        }

                        if (!check[nx][ny][level]) {
                            check[nx][ny][level] = true
                            queue.add(Node(nx, ny, StringBuilder(front.path).append(getPathChar(i))))
                        }

                    }
                }

                level++
            }

            while (queue.size > 0) {
                val front = queue.poll()
                if (front.x == r && front.y == c) {
                    return front.path.toString()
                }
            }

            return "impossible"
        }

        fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
            return BFS(n, m, x - 1, y - 1, r - 1, c - 1, k)
        }
    }
}

fun main() {
    val solution = `미로 탈출 명령어`.Solution()
    System.out.println(solution.solution(2, 2, 1, 1, 2, 2, 2))
}