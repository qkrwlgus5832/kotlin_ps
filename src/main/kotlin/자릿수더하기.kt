class 자릿수더하기 {
    class Solution {
        fun solution(n: Int): Int {
            var answer: Int = 0

            val nString = n.toString()

            for (i in 0 until nString.length) {
                answer += nString[i] - '0'
            }
            return answer
        }
    }
}