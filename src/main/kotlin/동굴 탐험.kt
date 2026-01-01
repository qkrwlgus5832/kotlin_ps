import java.util.*

class `동굴 탐험` {
    class Solution {
        val check = HashMap<Pair<Int, Int>, Boolean>()

        val depth = Array(200001) { 0 }

        private fun getParentGraph(graph: Array<MutableList<Int>>, n: Int): Array<MutableSet<Int>> {
            val start = 0
            val parentGraph = Array(n) {
                mutableSetOf<Int>()
            }

            val queue = LinkedList<Int>()
            val check = BooleanArray(n + 1) { false }

            queue.add(start)
            check[start] = true

            while(queue.size > 0) {
                val front = queue.poll()

                for (next in graph[front]) {
                    if (check[next] == false) {
                        parentGraph[front].add(next)
                        depth[next]++
                        check[next] = true
                        queue.add(next)
                    }
                }
            }

            return parentGraph
        }

        private fun topologicalSort(orderGraph: Array<MutableSet<Int>>, n: Int): Boolean {
            val queue = LinkedList<Int>()

            for (i in 0 until n) {
                if (depth[i] == 0) {
                    queue.add(i)
                }
            }

            while(queue.size > 0) {
                val front = queue.poll()

                for (next in orderGraph[front]) {
                    if (check.containsKey(Pair(front, next))) {
                        continue
                    }
                    depth[next]--
                    check[Pair(front, next)] = true

                    if (depth[next] == 0) {
                        queue.add(next)
                    }
                }
            }

            return true
        }

        fun solution(n: Int, path: Array<IntArray>, order: Array<IntArray>): Boolean {
            var answer = false

            val graph = Array(n) {
                mutableListOf<Int>()
            }

            for (i in 0 until path.size) {
                val first = path[i][0]
                val second = path[i][1]

                graph[first].add(second)
                graph[second].add(first)
            }

            val parentGraph = getParentGraph(graph, n)

            val orderGraph = parentGraph

            for (i in 0 until order.size) {
                val before = order[i][0]
                val after = order[i][1]

                if (!orderGraph[before].contains(after)) {
                    orderGraph[before].add(after)
                    depth[after]++
                }
            }


           topologicalSort(orderGraph, n)


            for (i in 0 until  n) {
                if (depth[i] > 0) {
                    return false
                }
            }

            return true
        }
    }
}

fun main() {
    val solution = `동굴 탐험`.Solution()
    solution.solution(
        9, arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 3),
            intArrayOf(0, 7),
            intArrayOf(8, 1),
            intArrayOf(3, 6),
            intArrayOf(1, 2),
            intArrayOf(4, 7),
            intArrayOf(7, 5),
        ),
        arrayOf(
            intArrayOf(4, 1),
            intArrayOf(8, 7),
            intArrayOf(6, 5)
        )
    )
}