import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet
import kotlin.math.min


class `미로 탈출` {
    class Solution {
        private val trapMap = HashMap<Int, Int>()

        data class Node(
            val current: Int,
            val trapCheck: Long,
            val cost: Int,
            val list: MutableList<Int> = mutableListOf()
        )

        private fun Long.contains(x: Int): Boolean {
            val index = trapMap[x] ?: return false
            return this and (1L shl index) != 0L
        }

        private fun Long.xor(x: Int): Long {
            val index = trapMap[x]!!
            return this xor (1L shl index)
        }

        private fun bfs(start: Int, end: Int, graph: Array<MutableList<Pair<Int, Int>>>, reverseGraph: Array<MutableList<Pair<Int, Int>>>): Int {
            val queue = PriorityQueue<Node>(compareBy { it.cost })
            val bfsCheck = Array<Array<Int>>(1001) { Array<Int>(2000) { -1 } }

            val startNode = Node(start, 0, 0, mutableListOf(start))
            queue.add(startNode)
            bfsCheck[startNode.current][startNode.trapCheck.toInt()] = 0
            var answer = Int.MAX_VALUE

            while (queue.size > 0) {
                val qSize = queue.size

                for (i in 0 until qSize) {
                    val front = queue.poll()

                    if (front.current == end) {
                        return front.cost
                    }

                    if (front.trapCheck.contains(front.current)) {
                        graph[front.current].forEach {
                            val next = it.first
                            val nextCost = it.second
                            var nextTrapCheck = front.trapCheck
                            val list = front.list.toMutableList()

                            if (!front.trapCheck.contains(next)) {
                                return@forEach
                            }

                            if (trapMap.containsKey(next)) {
                                nextTrapCheck = front.trapCheck.xor(next)
                            }

                            list.add(next)
                            val nextNode = Node(next, nextTrapCheck, front.cost + nextCost, list)
                            if (bfsCheck[next][nextTrapCheck.toInt()] == -1 || bfsCheck[next][nextTrapCheck.toInt()] > front.cost + nextCost) {
                                bfsCheck[next][nextTrapCheck.toInt()] = front.cost + nextCost
                                queue.add(nextNode)
                            }
                        }
                        reverseGraph[front.current].forEach {
                            val next = it.first
                            val nextCost = it.second
                            var nextTrapCheck = front.trapCheck
                            val list = front.list.toMutableList()

                            if (front.trapCheck.contains(next)) {
                                return@forEach
                            }

                            if (trapMap.containsKey(next)) {
                                nextTrapCheck = front.trapCheck.xor(next)
                            }

                            list.add(next)
                            val nextNode = Node(next, nextTrapCheck, front.cost + nextCost, list)
                            if (bfsCheck[next][nextTrapCheck.toInt()] == -1 || bfsCheck[next][nextTrapCheck.toInt()] > front.cost + nextCost) {
                                bfsCheck[next][nextTrapCheck.toInt()] = front.cost + nextCost
                                queue.add(nextNode)
                            }
                        }
                    } else {
                        graph[front.current].forEach {
                            val next = it.first
                            val nextCost = it.second
                            var nextTrapCheck = front.trapCheck
                            val list = front.list.toMutableList()

                            if (front.trapCheck.contains(next)) {
                                return@forEach
                            }

                            if (trapMap.containsKey(next)) {
                                nextTrapCheck = front.trapCheck.xor(next)
                            }

                            list.add(next)
                            val nextNode = Node(next, nextTrapCheck, front.cost + nextCost, list)
                            if (bfsCheck[next][nextTrapCheck.toInt()] == -1 || bfsCheck[next][nextTrapCheck.toInt()] > front.cost + nextCost) {
                                bfsCheck[next][nextTrapCheck.toInt()] = front.cost + nextCost
                                queue.add(nextNode)
                            }
                        }

                        reverseGraph[front.current].forEach {
                            val next = it.first
                            val nextCost = it.second
                            var nextTrapCheck = front.trapCheck
                            val list = front.list.toMutableList()

                            if (!front.trapCheck.contains(next)) {
                                return@forEach
                            }

                            if (trapMap.containsKey(next)) {
                                nextTrapCheck = front.trapCheck.xor(next)
                            }

                            list.add(next)
                            val nextNode = Node(next, nextTrapCheck, front.cost + nextCost, list)
                            if (bfsCheck[next][nextTrapCheck.toInt()] == -1 || bfsCheck[next][nextTrapCheck.toInt()] > front.cost + nextCost) {
                                bfsCheck[next][nextTrapCheck.toInt()] = front.cost + nextCost
                                queue.add(nextNode)
                            }
                        }
                    }
                }
            }

            return answer
        }

        fun solution(n: Int, start: Int, end: Int, roads: Array<IntArray>, traps: IntArray): Int {
            val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
            val reverseGraph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

            for (road in roads) {
                val from = road[0]
                val to = road[1]
                val cost = road[2]

                graph[from].add(Pair(to, cost))
                reverseGraph[to].add(Pair(from, cost))
            }

            for (i in 0 until traps.size) {
                trapMap[traps[i]] = i
            }

            return bfs(start, end, graph, reverseGraph)
        }
    }
}

fun main() {
    val solution = `미로 탈출`.Solution()
    println(solution.solution(5, 1, 4,
        arrayOf(
            intArrayOf(1, 2, 1),
            intArrayOf(2, 1, 1),
            intArrayOf(3, 2, 5),
            intArrayOf(3, 1, 1),
            intArrayOf(4, 3, 50),
            intArrayOf(3, 5, 1),
            intArrayOf(5, 4, 1),
        ), intArrayOf(2, 3)
    ))
}