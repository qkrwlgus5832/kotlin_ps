import java.util.*
import kotlin.math.min

class 배달 {
    class Solution {
        val town = Array(51) { IntArray(51) }
        val costCheck = IntArray(51) { -1 }

        private fun BFS(x: Int, N: Int, K: Int) {
            val queue = LinkedList<Pair<Int, Int>>()
            queue.push(Pair(x, 0))

            while(queue.size > 0) {
                val front = queue.poll()

                for (i in 1..N) {
                    if (town[front.first][i] > 0) {
                        if (costCheck[i] == -1 || costCheck[i] > front.second + town[front.first][i]) {
                            costCheck[i] = front.second + town[front.first][i]
                            queue.push(Pair(i, front.second + town[front.first][i]))
                        }
                    }
                }
            }
        }

        private fun setTownEdge(from: Int, to: Int, cost: Int) {
            if (town[from][to] == 0) {
                town[from][to] = cost
            }
            else {
                town[from][to] = min(town[from][to], cost)
            }
        }

        fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
            var answer = 0

            for (i in 0 until road.size) {
                setTownEdge(road[i][0], road[i][1], road[i][2])
                setTownEdge(road[i][1], road[i][0], road[i][2])
            }

            BFS(1, N, k)

            for (i in 1..N) {
                if (costCheck[i] <= k) {
                    answer++
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = 배달.Solution()
    solution.solution(5, arrayOf(intArrayOf(1,2,1), intArrayOf(2, 3, 3), intArrayOf(5, 2, 2), intArrayOf(1, 4, 2), intArrayOf(5, 3, 1), intArrayOf(5, 4, 2)), 3)
}
