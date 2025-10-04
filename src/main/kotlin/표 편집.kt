class `표 편집` {
    data class Node(
        var previous: Int,
        var origin: Int,
        var next: Int,
        var isAlive: Boolean
    )

    class Solution {
        private val deletedIterator = mutableListOf<Pair<Int, Int>>()
        private var currentIndex: Int = -1

        private fun runningCmd(table: MutableList<Node>, cmd: String, n: Int) {
            if (cmd[0] == 'U') {
                val count = cmd.substring(2).toInt()

                for (i in 0 until count) {
                    currentIndex = table[currentIndex].previous
                }

            }
            else if (cmd[0] == 'D') {
                val count = cmd.substring(2).toInt()

                for (i in 0 until count) {
                    currentIndex = table[currentIndex].next
                }
            }
            else if (cmd[0] == 'C') {
                val current = table[currentIndex]
                val previous = current.previous
                val next = current.next

                if (previous >= 0) {
                    table[previous].next = next
                }
                if (next <= n -1) {
                    table[next].previous = previous
                }

                table[currentIndex].isAlive = false
                deletedIterator.add(Pair(currentIndex, current.origin))

                if (next == n) {
                    currentIndex = previous
                }
                else {
                    currentIndex = next
                }
            }
            else if (cmd[0] == 'Z') {
                val deletedIndex = deletedIterator.last().first
                val previous = table[deletedIndex].previous
                val next = table[deletedIndex].next

                table[deletedIndex].isAlive = true

                if (previous >= 0) {
                    table[previous].next = deletedIndex
                }

                if (next <= n -1) {
                    table[next].previous = deletedIndex
                }

                deletedIterator.removeLast()
            }
        }

        fun solution(n: Int, k: Int, cmd: Array<String>): String {
            var answer = StringBuilder()

            val table = mutableListOf<Node>()

            for (i in 0 until n) {
                table.add(Node(i - 1, i, i + 1, true))
            }

            currentIndex = k

            for (i in 0 until cmd.size) {
                runningCmd(table, cmd[i], n)
            }

            for (i in 0 until n) {
                if (table[i].isAlive) {
                    answer.append('O')
                } else {
                    answer.append('X')
                }
            }
            return answer.toString()
        }
    }
}

fun main() {
    val solution = `표 편집`.Solution()
    solution.solution(
        8, 2, arrayOf(
            "D 2","C","U 3","C","D 4","C","U 2","Z","Z","U 1","C"
        )
    )
