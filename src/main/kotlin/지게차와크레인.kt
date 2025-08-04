import java.util.*

class 지게차와크레인 {
    class Solution {
        private val check: Array<Array<Int>> = Array(51) { Array(51) { 0 } }

        private fun removeStorage(storage: Array<String>, alpha: Char, i: Int, j: Int, exportQueue: LinkedList<Pair< Int,Int>>) {
            val queue = LinkedList<Pair<Int, Int>>()

            queue.add(Pair(i, j))

            val bfsCheck: Array<Array<Int>> = Array(51) { Array(51) { 0 } }

            bfsCheck[i][j] = 1

            val dir = arrayOf(
                intArrayOf(-1, 0),
                intArrayOf(1, 0),
                intArrayOf(0, -1),
                intArrayOf(0, 1)
            )

            while(queue.size > 0) {
                val front: Pair<Int, Int> = queue.poll()

                dir.forEach {
                    val ni = front.first + it[0]
                    val nj = front.second + it[1]

                    if (ni < 0 || nj < 0 || ni >= storage.size || nj >= storage[0].length) {
                        return@forEach
                    }

                    if (check[ni][nj] == 1 && bfsCheck[ni][nj] == 0) {
                        bfsCheck[ni][nj] = 1;
                        queue.push(Pair(ni, nj))
                    }
                    else if (check[ni][nj] == 0 && bfsCheck[ni][nj] == 0 && storage[ni][nj] == alpha) {
                        bfsCheck[ni][nj] = 1;
                        exportQueue.push(Pair(ni, nj))
                    }
                }
            }

        }

        private fun allRemoveStorage(storage: Array<String>, alpha: Char) {
            for (i in storage.indices) {
                for (j in storage[i].indices) {
                    if (storage[i][j] == alpha) {
                        check[i][j] = 1
                    }
                }
            }
        }

        fun solution(storage: Array<String>, requests: Array<String>): Int {
            var answer: Int = 0

            val exportQueue = LinkedList<Pair<Int, Int>>()

            requests.forEachIndexed { index, request ->
                if (request.length == 2) {
                    allRemoveStorage(storage, request[0])
                } else {
                    for (x in storage.indices) {
                        for (y in storage[x].indices) {
                            if (x != 0 && y != 0 && x != storage.size - 1 && y != storage[x].length -1) {
                                continue
                            }
                            if (check[x][y] == 0 && storage[x][y] == request[0]) {
                                exportQueue.add(Pair(x,y))
                            }

                            else if (check[x][y] == 1)
                                removeStorage(storage, request[0],  x, y, exportQueue)
                        }
                    }
                }

                while(exportQueue.size > 0) {
                    val front = exportQueue.poll()
                    check[front.first][front.second] = 1
                }
            }

            for (i in storage.indices) {
                for (j in storage[i].indices) {
                    if (check[i][j] == 0) {
                        answer++
                    }
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = 지게차와크레인.Solution()
    System.out.println(solution.solution(arrayOf("HAH", "HBH", "HHH", "HAH", "HBH"),
        arrayOf("C", "B", "B", "B", "B", "H")))
}