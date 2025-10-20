import java.util.*
import kotlin.math.min

class `카드 짝 맞추기` {
    class Solution {
        data class Coordi(
            val x: Int,
            val y: Int
        )

        data class Node(
            val checkNode: CheckNode,
            val commandCount: Int,
        )

        data class CheckNode(
            val cursor: Coordi,
            val currentMap: MutableList<MutableList<Int>>,
            val enterCard: Coordi,
        )

        private val dir = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private var answer = Int.MAX_VALUE

        val check = HashMap<CheckNode, Int>()

        private fun checkAndPush(node: Node, queue: LinkedList<Node>, nx: Int, ny: Int) {
            val checkNode = node.checkNode

            if (node.checkNode.enterCard == Coordi(-1, -1)) {
                val newNode = Node(CheckNode(Coordi(nx, ny), checkNode.currentMap, enterCard = Coordi(-1, -1)), node.commandCount + 1)
                if (!check.containsKey(newNode.checkNode) || check[newNode.checkNode]!! > newNode.commandCount) {
                    check.put(newNode.checkNode, newNode.commandCount)
                    queue.add(newNode)
                }
            } else {
                val enterCard = checkNode.enterCard
                var newNode = Node(CheckNode(Coordi(nx, ny), checkNode.currentMap, enterCard = checkNode.enterCard), node.commandCount + 1)

                if (checkNode.currentMap[nx][ny] > 0) {
                    if (checkNode.currentMap[nx][ny] == checkNode.currentMap[enterCard.x][enterCard.y] && !(nx == enterCard.x && ny == enterCard.y)) {
                        val copiedMap = checkNode.currentMap.map { it.toMutableList() }.toMutableList() // 깊은 복사
                        copiedMap[nx][ny] = 0
                        copiedMap[enterCard.x][enterCard.y] = 0
                        newNode = Node(CheckNode(Coordi(nx, ny), copiedMap, Coordi(-1, -1)), node.commandCount + 2)
                    }
                }

                if (!check.containsKey(newNode.checkNode) || check[newNode.checkNode]!! > newNode.commandCount) {
                    check.put(newNode.checkNode, newNode.commandCount)
                    queue.add(newNode)
                }

            }
        }

        private fun checkAndPushForEnter(node: Node, queue: LinkedList<Node>) {
            val checkNode = node.checkNode
            if (checkNode.currentMap[checkNode.cursor.x][checkNode.cursor.y] > 0 && checkNode.enterCard == Coordi(-1, -1)) {
                val newNode = Node(CheckNode(Coordi(checkNode.cursor.x, checkNode.cursor.y), checkNode.currentMap, enterCard = Coordi(checkNode.cursor.x, checkNode.cursor.y)), node.commandCount + 1)
                if (!check.containsKey(newNode.checkNode) || check[newNode.checkNode]!! > newNode.commandCount) {
                    check.put(newNode.checkNode, newNode.commandCount)
                    queue.add(newNode)
                }
            }
        }

        private fun checkAndPushForMove(node: Node, queue: LinkedList<Node>) {
            for (i in 0 until dir.size) {
                val nx = node.checkNode.cursor.x + dir[i][0]
                val ny = node.checkNode.cursor.y + dir[i][1]

                if (nx < 0 || ny < 0 || nx >= node.checkNode.currentMap.size || ny >= node.checkNode.currentMap.size) {
                    continue
                }

                checkAndPush(node, queue, nx, ny)
            }
        }

        private fun getNext(node: Node, index: Int, dirIndex: Int): Coordi {
            return Coordi(node.checkNode.cursor.x + dir[dirIndex][0] * index, node.checkNode.cursor.y + dir[dirIndex][1] * index)
        }

        private fun checkAndPushForControlMove(node: Node, queue: LinkedList<Node>) {
            for (i in 0 until dir.size) {
                var index = 1

                while(true) {
                    val next = getNext(node, index, i)
                    val nx = next.x
                    val ny = next.y

                    if (nx < 0 || ny < 0 || nx >= node.checkNode.currentMap.size || ny >= node.checkNode.currentMap[0].size) {
                        var (nx, ny) = getNext(node, index - 1, i).let {
                            Pair(it.x, it.y)
                        }
                        checkAndPush(node, queue, nx, ny)
                        break
                    }

                    if (node.checkNode.currentMap[nx][ny] > 0) {
                        checkAndPush(node, queue, nx, ny)
                        break
                    }

                    index++
                }
            }
        }

        private fun isAnswer(currentMap: MutableList<MutableList<Int>>): Boolean {
            for (i in 0 until currentMap.size) {
                for (j in 0 until currentMap[i].size) {
                    if (currentMap[i][j] > 0) {
                        return false
                    }
                }
            }
            return true
        }

        private fun BFS(board: Array<IntArray>, x: Int, y: Int): Int {
            val queue = LinkedList<Node>()

            queue.add(Node(CheckNode(Coordi(x, y), board.map { it.toMutableList()}.toMutableList(), Coordi(-1, -1)), 0))

            while(queue.size > 0) {
                val front = queue.poll()

                if (isAnswer(front.checkNode.currentMap) == true) {
                    answer = min(answer, front.commandCount)
                    continue
                }

                checkAndPushForEnter(front, queue)
                checkAndPushForMove(front, queue)
                checkAndPushForControlMove(front, queue)
            }

            return answer
        }

        fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
            return BFS(board, r, c)
        }
    }
}

fun main() {
    val solution = `카드 짝 맞추기`.Solution()
    println(solution.solution(arrayOf(
        intArrayOf(1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0),
        intArrayOf(0, 0, 0, 0)
    ), 0, 0))
}