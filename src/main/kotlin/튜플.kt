class 튜플 {
    class Solution {
        fun solution(s: String): IntArray {
            var answer = intArrayOf()

            val map = HashMap<Int, Int>()

            var number: String = ""

            for (i in 0 until s.length) {
                if (s[i] >= '0' && s[i] <= '9') {
                    number += s[i]
                }
                else if ((s[i] == ',' || s[i] == '}') && number.isNotEmpty()) {
                    map.put(number.toInt(), map.getOrDefault(number.toInt(), 0) + 1)
                    number = ""
                }
            }

            val list = map.entries.sortedByDescending { it.value }

            list.forEach {
                answer += it.key
            }
            return answer
        }
    }
}

fun main() {
    val solution = 튜플.Solution()
    solution.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")
}