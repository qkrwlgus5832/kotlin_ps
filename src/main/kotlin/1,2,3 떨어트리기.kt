import java.util.LinkedList
import kotlin.math.max

class `1,2,3 떨어트리기` {
    class Solution {
        private val result = Array(201) {
            mutableListOf<Int>()
        }

        private val visitedCount = Array(201) { 0 }

        private fun getLeefNodeList(graph: Array<MutableList<Int>>, root: Int): MutableList<Int> {
            val queue = LinkedList<Int>()
            queue.add(root)
            val result = mutableListOf<Int>()

            while(queue.size > 0) {
                val front = queue.poll()

                if (graph[front].isEmpty()) {
                    result.add(front)
                    continue
                }

                for (next in graph[front]) {
                    queue.add(next)
                }
            }

            return result
        }

        private fun getLeefNode(graph: Array<MutableList<Int>>, root: Int): Int {
            val queue = LinkedList<Int>()
            queue.add(root)

            while(queue.size > 0) {
                val front = queue.poll()

                if (graph[front].isEmpty()) {
                    return front
                }

                val index = visitedCount[front] % graph[front].size
                visitedCount[front]++
                queue.add(graph[front][index])
            }
            return -1
        }

        private fun getResult(leefNodeList: MutableList<Int>, target: IntArray, countArray: Array<Int>): Boolean {
            val tmpResult = Array(202) { mutableListOf<Int>() }

            for (i in 0 until leefNodeList.size) {
                val standardCount = countArray[leefNodeList[i]]
                if (target[leefNodeList[i] - 1] % 3 != 0) {
                    tmpResult[i].add(target[leefNodeList[i] - 1] % 3)
                }

                for (i1 in 0 until  target[leefNodeList[i] - 1] / 3) {
                    tmpResult[i].add(3)
                }

                var currentCount = tmpResult[i].size

                for (i1 in 0 until tmpResult[i].size) {
                    if (currentCount == standardCount) {
                        for (i2 in i1 until tmpResult[i].size) {
                            result[leefNodeList[i]].add(tmpResult[i][i2])
                        }
                        break
                    }
                    if (tmpResult[i][i1] == 1) {
                        result[leefNodeList[i]].add(1)
                    }
                    if (tmpResult[i][i1] == 2) {
                        result[leefNodeList[i]].add(1)
                        result[leefNodeList[i]].add(1)
                        currentCount++
                    }

                    if (tmpResult[i][i1] == 3) {
                        if (standardCount - currentCount == 1) {
                            result[leefNodeList[i]].add(1)
                            result[leefNodeList[i]].add(2)
                            currentCount++
                        }
                        else {
                            result[leefNodeList[i]].add(1)
                            result[leefNodeList[i]].add(1)
                            result[leefNodeList[i]].add(1)
                            currentCount += 2
                        }
                    }
                }

                if (currentCount != standardCount) {
                    return false
                }

            }

            return true
        }

        private fun Int.getDivCount(): Int {
            if (this % 3 == 0) {
                return this / 3
            }
            else {
                return this / 3 + 1
            }
        }

        fun solution(edges: Array<IntArray>, target: IntArray): IntArray {
            val graph = Array(target.size + 1) { mutableListOf<Int>() }

            for (edge in edges) {
                val parent = edge[0]
                val child = edge[1]

                graph[parent].add(child)
            }

            for (i in 0 until graph.size) {
                graph[i].sort()
            }

            val leefNodeList = getLeefNodeList(graph, 1)

            val mustCountArray = Array(target.size + 1) { 0 }

            for (i in 0 until target.size) {
                if (target[i] > 0) {
                    mustCountArray[i + 1] = target[i].getDivCount()
                }
            }

            val countArray = Array(target.size + 1) { 0 }

            while (true) {
                var flag = false

                for (leefNode in leefNodeList) {
                    if (mustCountArray[leefNode] > countArray[leefNode]) {
                        flag = true
                        break
                    }
                }

                if (flag == false) {
                    break
                }

                val leef = getLeefNode(graph, 1)
                countArray[leef]++
            }

            val isResult = getResult(leefNodeList, target, countArray)

            if (!isResult) {
                return intArrayOf(-1)
            }

            val answer = mutableListOf<Int>()

            visitedCount.fill(0)

            while(true) {
                val leef = getLeefNode(graph, 1)
                if (result[leef].size == 0) {
                    break
                }
                answer.add(result[leef].removeFirst())
            }

            return answer.toIntArray()
        }
    }
}

fun main() {
    val solution = `1,2,3 떨어트리기`.Solution()
    solution.solution(
        arrayOf(
            intArrayOf(1, 3),
            intArrayOf(1, 2)
        ),
        intArrayOf(0, 7, 1),
    )
}