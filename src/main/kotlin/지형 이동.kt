import java.util.PriorityQueue
import kotlin.math.abs

class `지형 이동` {
    class Solution {
        data class Node(
            val coordiValue: Int,
            val cost: Int
        )

        private val dir = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private fun convertToIntValue(coordi: Pair<Int, Int>, landColSize: Int): Int{
            return coordi.first * landColSize + coordi.second
        }

        private fun convertToCoordi(value: Int, landColSize: Int): Pair<Int, Int> {
            return Pair(value / landColSize, value % landColSize)
        }

        private fun initQueuePush(land: Array<IntArray>, start: Pair<Int, Int>, queue: PriorityQueue<Node>, edgeCheck: Array<Array<Boolean>>) {
            for (i in 0 until dir.size) {
                val ni = start.first + dir[i][0]
                val nj = start.second + dir[i][1]

                if (ni < 0 || nj < 0 || ni >= land.size || nj >= land[0].size) {
                    continue
                }

                val startCoordiValue = convertToIntValue(start, land[0].size)
                val newCoordiValue = convertToIntValue(Pair(ni, nj), land[0].size)

                edgeCheck[startCoordiValue][newCoordiValue] = true
                edgeCheck[newCoordiValue][startCoordiValue] = true

                queue.add(Node(
                    newCoordiValue, abs(land[start.first][start.second] - land[ni][nj])
                ))
            }
        }

        private fun minimalSpaningTree(land: Array<IntArray>, height: Int, start: Pair<Int, Int>): Int {
            val set = HashSet<Int>()
            val edgeCheck = Array(90001) {
                Array(land.size * land.size + 1) {
                    false
                }
            }

            set.add(convertToIntValue(start, land[0].size))

            val queue = PriorityQueue<Node>( compareBy { it.cost } )

            initQueuePush(land, start, queue, edgeCheck)

            var result = 0

            while (queue.size > 0) {
                val front = queue.poll()
                val current = front.coordiValue

                val currentCoordi = convertToCoordi(current, land[0].size)

                if (set.size == land.size * land[0].size) {
                    return result
                }

                if (!set.contains(current)) {
                    set.add(current)

                    if (front.cost > height) {
                        result += front.cost
                    }

                    for (i in 0 until dir.size) {
                        val ni = currentCoordi.first + dir[i][0]
                        val nj = currentCoordi.second + dir[i][1]
                        val newCoordiValue = convertToIntValue(Pair(ni, nj), land[0].size)

                        if (ni < 0 || nj < 0 || ni >= land.size || nj >= land[0].size) {
                            continue
                        }

                        if (edgeCheck[current][newCoordiValue] == true) {
                            continue
                        }

                        edgeCheck[current][newCoordiValue] = true
                        edgeCheck[newCoordiValue][current] = true
                        queue.add(Node(newCoordiValue, abs(land[currentCoordi.first][currentCoordi.second] - land[ni][nj])))
                    }
                }
            }
            return result
        }

        fun solution(land: Array<IntArray>, height: Int): Int {
            var answer = Int.MAX_VALUE

            minimalSpaningTree(land, height, Pair(0, 0))

            return answer
        }
    }
}

fun main() {
    val solution = `지형 이동`.Solution()

    println(
        solution.solution(
        arrayOf(
            intArrayOf(1, 4, 8, 10),
            intArrayOf(5, 5, 5, 5),
            intArrayOf(10, 10, 10, 10),
            intArrayOf(10, 10, 10, 20)
        ), 3)
    )
}