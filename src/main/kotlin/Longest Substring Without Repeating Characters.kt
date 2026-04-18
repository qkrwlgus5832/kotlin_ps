import kotlin.math.max

class `Longest Substring Without Repeating Characters` {
    class Solution {
        fun lengthOfLongestSubstring(s: String): Int {
            val indexMap = HashMap<Char, Int>()

            var currentStart = 0
            var answer = 0
            var count = 0

            for (i in 0 until s.length) {
                val isContains = indexMap.containsKey(s[i])

                if (isContains) {
                    if (indexMap[s[i]]!! >= currentStart) {
                        currentStart = indexMap[s[i]]!! + 1
                        answer = max(answer, count)
                        count = i - indexMap[s[i]]!!
                        indexMap[s[i]] = i
                        continue
                    }
                }

                indexMap[s[i]] = i
                count++
            }

            return max(answer, count)
        }
    }
}

fun main() {
    val solution = `Longest Substring Without Repeating Characters`.Solution()
    println(solution.lengthOfLongestSubstring("uqinntq"))
}