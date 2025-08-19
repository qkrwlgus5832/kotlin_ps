import java.util.LinkedList

class `표 병합` {
    class Solution {
        private val table: Array<Array<String>> = Array(51) { Array(51) { "" } }
        private val graph: Array<Array<MutableList<Pair<Int, Int>>>> = Array(51) { Array(51) { mutableListOf() } }

        private fun BFS(r: Int, c: Int, value: String) {
            val check:Array<Array<Boolean>> = Array(51) { Array(51) { false } }
            val queue = LinkedList<Pair<Int, Int>>()

            queue.add(Pair(r,c))
            check[r][c] = true

            while(queue.size > 0) {
                val front = queue.poll()

                val newR = front.first
                val newC = front.second

                for (i in 0 until graph[newR][newC].size) {
                    val coordi = graph[newR][newC][i]

                    if (check[coordi.first][coordi.second] == false) {
                        check[coordi.first][coordi.second] = true
                        table[coordi.first][coordi.second] = value
                        queue.push(Pair(coordi.first, coordi.second))
                    }
                }
            }
        }

        private fun removeFromGraphAndRollbackFromOrigin(r: Int, c: Int, value: String) {
            val queue = LinkedList<Pair<Int, Int>>()

            val check = Array<Array<Boolean>>(51) { Array<Boolean>(51) { false } }
            queue.push(Pair(r,c))

            check[r][c] = true

            val list = mutableListOf<Pair<Int,Int>>()

            list.add(Pair(r,c))

            while(queue.size > 0) {
                val front = queue.poll()

                val newR = front.first
                val newC = front.second

                for (i in 0 until graph[newR][newC].size) {
                    val coordi = graph[newR][newC][i]

                    if (check[coordi.first][coordi.second] == false) {
                        check[coordi.first][coordi.second] = true
                        list.add(Pair(coordi.first, coordi.second))
                        table[coordi.first][coordi.second] = ""
                        queue.push(Pair(coordi.first, coordi.second))
                    }
                }
            }

            for (i in 0 until list.size) {
                graph[list[i].first][list[i].second].clear()
            }

            table[r][c] = value
        }

        fun solution(commands: Array<String>): Array<String> {
            var answer: Array<String> = arrayOf<String>()

            commands.forEachIndexed { index, command ->
                val splited = command.split(' ')
                if (splited[0] == "UPDATE") {
                    if (splited.size == 4) {
                        val r = splited[1].toInt() - 1
                        val c = splited[2].toInt() - 1
                        val value = splited[3]

                        table[r][c] = value
                        BFS(r,c, value)
                    }
                    else if (splited.size == 3) {
                        val value1 = splited[1]
                        val value2 = splited[2]

                        for (i in 0 until table.size) {
                            for (j in 0 until table[i].size) {
                                if (table[i][j] == value1) {
                                    table[i][j] = value2
                                }
                            }
                        }
                    }
                }
                else if (splited[0] == "MERGE") {
                    val r1 = splited[1].toInt() - 1
                    val c1 = splited[2].toInt() - 1
                    val r2 = splited[3].toInt() - 1
                    val c2 = splited[4].toInt() - 1

                    if (r1 == r2 && c1 == c2) {
                        return@forEachIndexed
                    }

                    graph[r1][c1].add(Pair(r2, c2))
                    graph[r2][c2].add(Pair(r1, c1))

                    if(table[r1][c1] == "" && table[r2][c2] != "") {
                        BFS(r2, c2, table[r2][c2])
                    }

                    else if(table[r1][c1] != "" && table[r2][c2] == "") {
                        BFS(r1, c1, table[r1][c1])
                    }

                    else if(table[r1][c1] != "" && table[r2][c2] != "") {
                        BFS(r1, c1, table[r1][c1])
                    }
                }

                else if (splited[0] == "UNMERGE") {
                    val r = splited[1].toInt() - 1
                    val c = splited[2].toInt() - 1

                    removeFromGraphAndRollbackFromOrigin(r,c, table[r][c])
                }

                else if (splited[0] == "PRINT") {
                    val r = splited[1].toInt() - 1
                    val c = splited[2].toInt() - 1

                    if (table[r][c] == "") {
                        answer += "EMPTY"
                    }
                    else {
                        answer += table[r][c]
                    }
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `표 병합`.Solution()
    solution.solution(arrayOf("UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"))
}