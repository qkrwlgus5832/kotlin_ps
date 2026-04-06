import java.util.*
import kotlin.collections.HashMap

class `홀짝 트리` {
    class Solution {
        private val nodeCheck = HashMap<Int, Boolean>()
        private val dividedNodes = mutableListOf<MutableList<Int>>()

        private val graph = HashMap<Int, MutableList<Int>>()

        private fun BFS(start: Int, index: Int) {
            val queue = LinkedList<Int>()
            val result = mutableListOf<Int>()

            queue.add(start)
            result.add(start)

            nodeCheck[start] = true

            while (queue.size > 0) {
                val front = queue.poll()

                graph[front]?.let {
                    for (next in it) {
                        if (nodeCheck[next] == true) {
                            continue
                        }
                        nodeCheck[next] = true
                        queue.add(next)
                        result.add(next)
                    }
                }
            }

            dividedNodes.add(result)
        }

        private fun slurpTreeCondition(treeNumber: Int, childCount: Int): Boolean {
            return treeNumber % 2 != childCount % 2
        }

        private fun reverseSlurpTreeCondition(treeNumber: Int, childCount: Int): Boolean {
            return treeNumber % 2 == childCount % 2
        }

        private fun isSlurpTree(edgeCount: HashMap<Int, Int>, nodes: MutableList<Int>): Boolean {
            var flag = false

            for (node in nodes) {
                if (!slurpTreeCondition(node, edgeCount[node] ?: 0)) {
                    if (!flag) {
                        flag = true
                    } else {
                        return false
                    }
                }
            }
            return flag
        }

        private fun isReverseSlurpTree(edgeCount: HashMap<Int, Int>, nodes: MutableList<Int>): Boolean {
            var flag = false

            for (node in nodes) {
                if (!reverseSlurpTreeCondition(node, edgeCount[node] ?: 0)) {
                    if (!flag) {
                        flag = true
                    } else {
                        return false
                    }
                }
            }
            return flag
        }

        fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {
            var answer: IntArray = IntArray(2) { 0 }

            val edgeCount = HashMap<Int, Int>()

            for (edge in edges) {
                edgeCount[edge[0]] = if (edgeCount[edge[0]] == null) 1 else edgeCount[edge[0]]!! + 1
                edgeCount[edge[1]] = if (edgeCount[edge[1]] == null) 1 else edgeCount[edge[1]]!! + 1

                graph.getOrPut(edge[0]) { mutableListOf() }
                graph[edge[0]]?.add(edge[1])
                graph.getOrPut(edge[1]) { mutableListOf() }
                graph[edge[1]]?.add(edge[0])
            }

            var index = 0

            for (node in nodes) {
                if (nodeCheck[node] == true) {
                    continue
                }
                BFS(node, index)
                index++
            }


            for (i in 0 until dividedNodes.size) {
                if (isSlurpTree(edgeCount, dividedNodes[i])) {
                    answer[0]++
                }
                if (isReverseSlurpTree(edgeCount, dividedNodes[i])) {
                    answer[1]++
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `홀짝 트리`.Solution()
    solution.solution(
        intArrayOf(9, 15, 14, 7, 6, 1, 2, 4, 5, 11, 8, 10),
        arrayOf(
            intArrayOf(5, 14),
            intArrayOf(1, 4),
            intArrayOf(9, 11),
            intArrayOf(2, 15),
            intArrayOf(2, 5),
            intArrayOf(9, 7),
            intArrayOf(8, 1),
            intArrayOf(6, 4),
        )
    )
}