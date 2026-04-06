import java.util.*

class `트리 트리오 중간값` {
    class Solution {
        private fun bfs(
            graph: Array<MutableList<Int>>,
            start: Int,
            n: Int,
            check: BooleanArray
        ): Triple<List<Int>, Int, Int> {
            val queue = LinkedList<Int>()

            check[start] = true
            queue.add(start)

            var count = 0
            var diameterNodeList = listOf<Int>()
            var depth = 0

            while (queue.size > 0) {
                val qSize = queue.size
                count = qSize
                diameterNodeList = queue.toList()

                for (i in 0 until qSize) {
                    val front = queue.poll()

                    for (next in graph[front]) {
                        if (check[next] == false) {
                            check[next] = true
                            queue.add(next)
                        }
                    }
                }
                depth++
            }

            return Triple(diameterNodeList, count, depth -1)
        }

        fun solution(n: Int, edges: Array<IntArray>): Int {
            var answer: Int = 0

            val graph = Array(n + 1) { mutableListOf<Int>() }
            val edgeCheck = Array(n + 1) { 0 }

            var root = 1
            var maxEdge = 0

            for (edge in edges) {
                graph[edge[0]].add(edge[1])
                graph[edge[1]].add(edge[0])
                edgeCheck[edge[0]]++
                edgeCheck[edge[1]]++

                if (edgeCheck[edge[0]] > maxEdge) {
                    maxEdge = edgeCheck[edge[0]]
                    root = edge[0]
                }

                if (edgeCheck[edge[1]] > maxEdge) {
                    maxEdge = edgeCheck[edge[1]]
                    root = edge[1]
                }
            }

            val bfsResult = bfs(graph, root, n, BooleanArray(n + 1))
            val bfsResult2 = bfs(graph, bfsResult.first.first(), n, BooleanArray(n + 1))

            if (bfsResult.second > 1 || bfsResult2.second > 1) {
                if (bfsResult.second == 2 && bfsResult2.second == 1) {
                    if (bfsResult.first[1] == bfsResult2.first.first()) {
                        return bfsResult2.third - 1
                    }
                }
                return bfsResult2.third
            } else {
                return bfsResult2.third - 1
            }
        }
    }
}

fun main() {
    val solution = `트리 트리오 중간값`.Solution()
    solution.solution(
        5,
        arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 3),
            intArrayOf(2, 4),
            intArrayOf(3, 5),
        )
    )
}