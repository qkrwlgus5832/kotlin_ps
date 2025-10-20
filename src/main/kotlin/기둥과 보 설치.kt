class `기둥과 보 설치` {
    class Solution {
        private fun isPillarFine(x: Int, y: Int, graph: Array<Array<BooleanArray>>): Boolean {
            if (y == 0 || graph[x][y - 1][0] == true || graph[x][y][1] == true || (x - 1 >=0 && graph[x-1][y][1] == true)) {
                return true
            }
            return false
        }

        private fun isBoFine(x: Int, y: Int, graph: Array<Array<BooleanArray>>, n: Int): Boolean {
            if (graph[x][y][0] == true) {
                return true
            }

            if (x + 1 <= n && graph[x+1][y][0] == true) {
                return true
            }

            if (y - 1 >=0 && graph[x][y - 1][0] == true) {
                return true
            }

            if (x + 1 <= n && y -1 >=0 && graph[x+1][y - 1][0] == true) {
                return true
            }

            if ((x - 1 >=0 && graph[x-1][y][1] == true) && (x + 1) <= n && graph[x + 1][y][1] == true) {
                return true
            }

            return false
        }

        private fun checkFromGraph(graph: Array<Array<BooleanArray>>, x: Int, y: Int, n: Int): Boolean {
            if (x < 0 || y < 0 || x > n || y > n) {
                return true
            }

            if (graph[x][y][0] == false && graph[x][y][1] == false) {
                return true
            }

            if (graph[x][y][0] == true && graph[x][y][1] == true) {
                if (isPillarFine(x, y, graph) && isBoFine(x, y, graph, n)) {
                    return true
                }
                return false
            }

            else if (graph[x][y][0] == true) {
                if (isPillarFine(x, y, graph)) {
                    return true
                }
                return false
            }

            else if (graph[x][y][1] == true) {
                if (isBoFine(x, y, graph, n)) {
                    return true
                }

                return false
            }

            return false
        }

        private fun checkIsValidForRemove(graph: Array<Array<BooleanArray>>, x: Int, y: Int, a: Int, n: Int): Boolean {
            if (a == 0) {
                return checkFromGraph(graph, x, y - 1, n) &&
                checkFromGraph(graph, x - 1, y, n) &&
                checkFromGraph(graph, x + 1, y, n)
            }
            else if (a == 1) {
                return checkFromGraph(graph, x - 1, y, n) &&
                checkFromGraph(graph, x + 1, y, n)
            }

            return false
        }

        private fun checkIsValidForInstall(graph: Array<Array<BooleanArray>>, x: Int, y: Int, a: Int, n: Int): Boolean {
            return checkFromGraph(graph, x, y, n)
        }

        private fun removeFromGraph(graph: Array<Array<BooleanArray>>, x: Int, y: Int, a: Int) {
            graph[x][y][a] = false
        }

        private fun installFromGraph(graph: Array<Array<BooleanArray>>, x: Int, y: Int, a: Int) {
            graph[x][y][a] = true
        }

        fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
            val graph = Array(n + 1) {
                Array(n + 1) {
                    BooleanArray(2) {
                        false
                    }
                }
            }

            for (i in 0 until build_frame.size) {
                val x = build_frame[i][0]
                val y = build_frame[i][1]
                val a = build_frame[i][2]
                val b = build_frame[i][3]

                if (b== 0) {
                    removeFromGraph(graph, x, y, a)
                    graph[x][y][a] = false

                    val result = checkIsValidForRemove(graph, x, y, a, n)

                    if (!result) {
                        graph[x][y][a] = true
                    }
                }

                else if (b== 1){
                    installFromGraph(graph, x, y, a)
                    graph[x][y][a] = true

                    val result = checkIsValidForInstall(graph, x, y, a, n)

                    if (!result) {
                        graph[x][y][a] = false
                    }
                }
            }

            var result = mutableListOf<IntArray>()

            for (i in 0 until graph.size) {
                for (j in 0 until graph[i].size) {
                    if (graph[i][j][0] == true) {
                        result.add(intArrayOf(i, j, 0))
                    }

                    if (graph[i][j][1] == true) {
                        result.add(intArrayOf(i, j, 1))
                    }
                }
            }

            return result.toTypedArray()
        }
    }
}

fun main() {
    val solution = `기둥과 보 설치`.Solution()
    solution.solution(
        5,
        arrayOf(
            intArrayOf(0, 0, 0, 1),
            intArrayOf(2, 0, 0, 1),
            intArrayOf(4, 0, 0, 1),
            intArrayOf(0, 1, 1, 1),
            intArrayOf(1, 1, 1, 1),
            intArrayOf(2, 1, 1, 1),
            intArrayOf(3, 1, 1, 1),
            intArrayOf(2, 0, 0, 0),
            intArrayOf(1, 1, 1, 0),
            intArrayOf(2, 2, 0, 1),
        )
    )
}