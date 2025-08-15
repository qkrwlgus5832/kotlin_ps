import java.util.LinkedList

class 가장먼노드 {
    class Solution {
        private val check = BooleanArray(20001) { false }

        private fun BFS(x: Int, graph: Array<MutableList<Int>>): Int {
            val queue = LinkedList<Int>()

            var result = 0

            queue.add(x)
            check[x] = true

            while(queue.size > 0) {
                val qSize = queue.size
                result = qSize

                for (i in 0 until qSize) {
                    val front = queue.poll()

                    for (j in 0 until graph[front].size)
                    if (check[graph[front][j]] == false) {
                        check[graph[front][j]] = true
                        queue.add(graph[front][j])
                    }
                }
            }

            return result
        }

        fun solution(n: Int, edge: Array<IntArray>): Int {
            val graph = Array<MutableList<Int>>(20001){ mutableListOf() }

            edge.forEachIndexed { index, it ->
                graph[it[0]].add(it[1])
                graph[it[1]].add(it[0])
            }

            return BFS(1, graph)
        }
    }
}

fun main() {
    val solution = 가장먼노드.Solution()
    solution.solution(6, arrayOf(intArrayOf(3, 6), intArrayOf(4, 3), intArrayOf(3, 2), intArrayOf(1, 3), intArrayOf(1, 2), intArrayOf(2, 4), intArrayOf(5, 2)))
}