class 순위 {
    class Solution {
        private val upGraph = Array<MutableList<Int>>(101) {
            mutableListOf()
        }

        private val downGraph = Array<MutableList<Int>>(101) {
            mutableListOf()
        }

        private fun upDfs(x: Int, set: HashSet<Int>) {
            for (i in 0 until upGraph[x].size) {
                if (set.contains(upGraph[x][i]) == false) {
                    set.add(upGraph[x][i])
                    upDfs(upGraph[x][i], set)
                }
            }
        }

        private fun downDfs(x: Int, set: HashSet<Int>) {
            for (i in 0 until downGraph[x].size) {
                if (set.contains(downGraph[x][i]) == false) {
                    set.add(downGraph[x][i])
                    downDfs(downGraph[x][i], set)
                }
            }
        }

        fun solution(n: Int, results: Array<IntArray>): Int {
            var answer = 0

            results.forEach { result ->
                upGraph[result[1]].add(result[0])
                downGraph[result[0]].add(result[1])
            }

            val upRank = IntArray(n + 1) { -1 }
            val downRank = IntArray(n + 1) { -1 }


            for (i in 1..n) {
                val set = HashSet<Int>()
                upDfs(i, set)
                upRank[i] = set.size
            }

            for (i in 1..n) {
                val set = HashSet<Int>()
                downDfs(i, set)
                downRank[i] = set.size
            }

            for (i in 1..n) {
                if (upRank[i] + downRank[i] == n - 1) {
                    answer++
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = 순위.Solution()
    solution.solution(5, arrayOf(intArrayOf(4, 3), intArrayOf(4, 2), intArrayOf(3, 2), intArrayOf(1, 2), intArrayOf(2, 5)))
}