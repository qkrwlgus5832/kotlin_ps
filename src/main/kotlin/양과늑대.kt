import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

class 양과늑대 {
    class Solution {
        private var answer = 0

        private val check = Array(37) {
            Array(18) {
                Array(18){
                    false
                }
            }
        }

        private val graph = Array<MutableList<Int>>(18) {
            mutableListOf()
        }

        private val edgeMap = HashMap<Pair<Int, Int>, Int>()

        private fun dfs(x: Int, sheep: Int, fox: Int, isVisited: BooleanArray, info: IntArray) {
            var currentSheep = sheep

            answer = max(answer, currentSheep)

            val list = LinkedList<Int>()

            for (i in 0 until isVisited.size) {
                if (isVisited[i] == true) {
                    list.add(i)
                }
            }

            for (i in 0 until list.size) {
                for (j in 0 until graph[list[i]].size) {
                    val candi = graph[list[i]][j]

                    if (isVisited[candi] == false) {
                        isVisited[candi] = true
                        if (info[candi] == 1) {
                            if (sheep > fox + 1) {
                                dfs(candi, sheep, fox + 1, isVisited, info)
                            }
                        }
                        else {
                            dfs(candi, sheep + 1, fox, isVisited, info)
                        }
                        isVisited[candi] = false
                    }
                }
            }

        }

        fun solution(info: IntArray, edges: Array<IntArray>): Int {
            edges.forEachIndexed { index, edge ->
                graph[edge[0]].add(edge[1])
                graph[edge[1]].add(edge[0])

                edgeMap.put(Pair(edge[0], edge[1]), index * 2)
                edgeMap.put(Pair(edge[1], edge[0]), index * 2 + 1)
            }

            val isVisited = Array<Boolean>(18) { false }
            isVisited[0] = true

            dfs(0, 1, 0, isVisited.toBooleanArray(), info)
            return answer
        }
    }
}

fun main() {
    val solution = 양과늑대.Solution()
    print(solution.solution(intArrayOf(0,1,0,1,1,0,1,0,0,1,0),
        arrayOf(
            intArrayOf(0, 1),
            intArrayOf(0, 2),
            intArrayOf(1, 3),
            intArrayOf(1, 4),
            intArrayOf(2, 5),
            intArrayOf(2, 6),
            intArrayOf(3, 7),
            intArrayOf(4, 8),
            intArrayOf(6, 9),
            intArrayOf(9, 10),
        )))
}