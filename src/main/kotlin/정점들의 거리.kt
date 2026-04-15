import java.util.LinkedList
import java.util.Scanner

class `정점들의 거리` {
    data class Node(
        val from: Int,
        val to: Int,
        val cost: Int
    )

    class Solution {
        val costs = Array<Array<Pair <Int, Int>?>> (40001) {
            Array(16) {
                null
            }
        }

        val levels = Array(40001) {
            0
        }

        fun bfs(graph: Array<MutableList<Pair<Int, Int>>>): MutableList<Int> {
            val nodes = mutableListOf<Int>()

            val queue = LinkedList<Int>()
            queue.add(1)

            val check = BooleanArray(graph.size)
            check[1] = true
            nodes.add(1)
            var level = 1
            levels[0] = 0

            while(!queue.isEmpty()) {
                val qSize = queue.size

                for (i1 in 0 until qSize) {
                    val front = queue.poll()

                    for (i in 0 until graph[front].size) {
                        val next = graph[front][i].first
                        val nextCost = graph[front][i].second

                        if (check[next] == false) {
                            check[next] = true
                            costs[next][0] = Pair(front, nextCost)
                            nodes.add(next)
                            levels[next] = level
                            queue.add(next)
                        }
                    }
                }

                level++
            }

            return nodes
        }

        fun dfs(origin: Int, current: Int?, currentIndex: Int, cost: Int?) {
            if (current == null) {
                return
            }
            if (costs[current][currentIndex] == null) {
                return
            }

            val next = costs[current][currentIndex]!!.first
            val nextCost = costs[current][currentIndex]!!.second + cost!!

            costs[origin][currentIndex + 1] = Pair(next, nextCost)

            dfs(origin, next, currentIndex + 1, nextCost)
        }


        fun climb(current: Int, spanLevel: Int, cost: Int): Pair<Int, Int> {
            if (spanLevel == 0) {
                return Pair(current, cost)
            }

            var currentIndex = 0
            var level = 1

            while(true) {
                if (spanLevel < level * 2) {
                    break
                }
                currentIndex++
                level *= 2
            }

            val parent = costs[current][currentIndex]!!.first
            val parentCost = costs[current][currentIndex]!!.second

            return climb(parent, spanLevel - level, cost + parentCost)
        }

        fun leveling(from: Int, to: Int): Node {
            val fromLevel = this.levels[from]
            val toLevel = this.levels[to]

            if (fromLevel < toLevel) {
                val (newTo, cost) = climb(to, toLevel - fromLevel, 0)
                return Node(from, newTo, cost)

            }

            else if (fromLevel > toLevel) {
                val (newFrom, cost) = climb(from, fromLevel - toLevel, 0)
                return Node(newFrom, to, cost)
            }
            else {
                return Node(from, to, 0)
            }
        }

        fun getCommonParent(from: Int, to: Int, cost: Int): Int {
            if (from == to) {
                return cost
            }

            for (i in 0 until costs[from].size) {
                val nextFrom = costs[from][i]?.first
                val nextTo = costs[to][i]?.first
                val nextFromCost = costs[from][i]?.second
                val nextToCost = costs[to][i]?.second

                if (nextFrom == nextTo) {
                    if (i == 0) {
                        return cost + nextFromCost!! + nextToCost!!
                    }
                    else {
                        return getCommonParent(costs[from][i-1]!!.first, costs[to][i-1]!!.first, cost + costs[from][i - 1]!!.second + costs[to][i - 1]!!.second)
                    }
                }
            }

            return -1
        }
    }
}


fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }

    for (i in 0 until n - 1) {
        val start = nextInt()
        val end = nextInt()
        val cost = nextInt()

        graph[start].add(Pair(end, cost))
        graph[end].add(Pair(start, cost))
    }

    val solution = `정점들의 거리`.Solution()

    val nodes = solution.bfs(graph)

    for (i in 0 until nodes.size) {
        val next = solution.costs[nodes[i]][0]?.first
        val cost = solution.costs[nodes[i]][0]?.second

        solution.dfs(nodes[i], next, 0, cost)
    }


    val m = nextInt()

    for (i in 0 until m) {
        val from = nextInt()
        val to = nextInt()

        val node = solution.leveling(from, to)

        val cost = solution.getCommonParent(node.from, node.to, 0)

        println(node.cost + cost)
    }
}