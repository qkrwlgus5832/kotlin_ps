import java.util.LinkedList
import kotlin.math.min

class 등대 {
    class Solution {
        fun recursion(n: Int, current: Int, isBlink: Boolean, dp: Array<IntArray>, treeGraph: Array<MutableList<Int>>, check: BooleanArray) {
            if (treeGraph[current].size == 0) {
                if (isBlink) {
                    dp[current][1] = 1
                }
                else {
                    dp[current][0] = 0
                }
                return
            }

            for (i in 0 until treeGraph[current].size) {
                val next = treeGraph[current][i]

                if (check[next] == false) {
                    check[next] = true

                    if (dp[next][0] == -1) {
                        recursion(n, next, false, dp, treeGraph, check)
                    }

                    if (dp[next][1] == -1) {
                        recursion(n, next, true, dp, treeGraph, check)
                    }
                }
            }

            var sumResult = if(isBlink) 1 else 0

            for (i in 0 until treeGraph[current].size) {
                val next = treeGraph[current][i]

                if (isBlink) {
                    sumResult += min(dp[next][0], dp[next][1])
                }
                else {
                    sumResult += dp[next][1]
                }
            }

            dp[current][if(isBlink) 1 else 0] = sumResult
        }

        fun getTree(n: Int, graph: Array<MutableList<Int>>): Array<MutableList<Int>> {
            val check = BooleanArray(n + 1)

            val queue = LinkedList<Int>()

            val newGraph = Array(n + 1) { mutableListOf<Int> () }
            val init = 1

            queue.add(init)
            check[init] = true

            while (queue.size > 0) {
                val front = queue.poll()

                for (i in 0 until graph[front].size) {
                    val next = graph[front][i]

                    if (check[next] == false) {
                        check[next] = true
                        queue.add(next)
                        newGraph[front].add(next)
                    }
                }
            }

            return newGraph
        }

        fun solution(n: Int, lighthouse: Array<IntArray>): Int {
            val dp = Array(n + 1) {
                IntArray(2) {
                    -1
                }
            }

            val graph = Array(n + 1) {
                mutableListOf<Int>()
            }

            for (i in 0 until lighthouse.size) {
                val edge = lighthouse[i]
                graph[edge[0]].add(edge[1])
                graph[edge[1]].add(edge[0])
            }

            val treeGraph = getTree(n, graph)

            recursion(n, 1, false, dp, treeGraph, BooleanArray(n + 1).apply { this[1] = true})
            recursion(n, 1, true, dp, treeGraph, BooleanArray(n + 1).apply { this[1] = true})

            return min(dp[1][0], dp[1][1])
        }
    }
}

fun main() {
    val solution = 등대.Solution()
    solution.solution(7,
        arrayOf(
            intArrayOf(2, 3),
            intArrayOf(3, 4),
            intArrayOf(4, 1),
            intArrayOf(1, 5),
            intArrayOf(5, 6),
            intArrayOf(6, 7),
        ))
}