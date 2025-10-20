import java.util.LinkedList
import kotlin.math.min

class 부대복귀 {
    class Solution {
        private val graph = Array(100001) {
            mutableListOf<Int>()
        }

        private val count = Array(100001) {
            -1
        }

        private fun BFS(source: Int, destination: Int, n: Int): Int {
            val queue = LinkedList<Int>()

            var level = 0

            val check = BooleanArray(n+1) { false }

            queue.add(source)
            check[source] = true

            var answer = Int.MAX_VALUE

            while(queue.size > 0) {
                val qSize = queue.size
                var flag = false

                for (i in 0 until qSize) {
                    val front = queue.poll()

                    if (front == destination) {
                        answer = min(answer, level)
                        return answer
                    }

                    if (count[front] != -1) {
                        answer = min(answer, level + count[front])
                        continue
                    }

                    flag = true

                    for (j in 0 until graph[front].size) {
                        val next = graph[front][j]

                        if (!check[next]) {
                            check[next] = true
                            queue.add(next)
                        }
                    }
                }

                if (!flag) {
                    return answer
                }
                level++
            }

            return if (answer == Int.MAX_VALUE) -1 else answer
        }

        fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
            var answer: IntArray = IntArray(sources.size)


            for (i in roads.indices) {
                graph[roads[i][0]].add(roads[i][1])
                graph[roads[i][1]].add(roads[i][0])
            }

            for (i in sources.indices) {
                answer[i] = BFS(sources[i], destination, n)
                count[sources[i]] = answer[i]
            }

            return answer
        }
    }
}

fun main() {
    val solution = 부대복귀.Solution()
    solution.solution(5, arrayOf(
            intArrayOf(1, 2),
            intArrayOf(1, 4),
            intArrayOf(2, 4),
            intArrayOf(2, 5),
            intArrayOf(4, 5)
        ), intArrayOf(1, 3, 5), 5,
    )
}