import kotlin.math.max

class `Longest Repeating Character Replacement` {
    class Solution {
        private fun getMaxFrequent(count: Array<Int>): Int {
            var maxCount = 0

            for (i in 0 until count.size) {
                maxCount = max(maxCount, count[i])
            }

            return maxCount
        }

        fun characterReplacement(s: String, k: Int): Int {
            val deque = ArrayDeque<Char>()

            val count = Array(26) { 0 }

            var answer = 0

            for (i in 0 until s.length) {
                deque.add(s[i])
                count[s[i]-'A']++

                if (deque.size - getMaxFrequent(count) > k) {
                    while(true) {
                        val first = deque.removeFirst()
                        count[first - 'A']--

                        if (deque.size - getMaxFrequent(count) <= k) {
                            answer = max(answer, deque.size)
                            break
                        }
                    }
                }
                else {
                    answer = max(answer, deque.size)
                }
            }
            return max(answer, deque.size)
        }
    }
}

fun main() {
    val solution = `Longest Repeating Character Replacement`.Solution()
    println(solution.characterReplacement("AABABBA", 1))
}