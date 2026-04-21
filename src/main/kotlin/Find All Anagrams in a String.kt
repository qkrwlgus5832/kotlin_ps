class `Find All Anagrams in a String` {
    class Solution {
        fun findAnagrams(s: String, p: String): List<Int> {
            val pCount = Array(26) { 0 }

            for (i in 0 until p.length) {
                pCount[p[i] -'a']++
            }

            val answer = mutableListOf<Int>()

            val deque = ArrayDeque<Pair<Char, Int>>()
            val windowSlidingCount = Array(26) { 0 }

            for (i in 0 until s.length) {
                deque.add(Pair(s[i], i))
                windowSlidingCount[s[i] - 'a']++

                if (pCount[s[i]-'a'] < windowSlidingCount[s[i] -'a']) {
                    while(true) {
                        val first = deque.removeFirst()
                        windowSlidingCount[first.first - 'a']--
                        if (windowSlidingCount[s[i]-'a'] == pCount[s[i] -'a']) {
                            break
                        }
                    }
                }

                if (deque.size == p.length) {
                    answer.add(deque.first().second)
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `Find All Anagrams in a String`.Solution()
    solution.findAnagrams("abab", "ab")
}