class 평균구하기 {
    class Solution {
        fun solution(arr: IntArray): Double {
            var answer = 0

            arr.forEach {
                answer += it
            }
            return answer / arr.size.toDouble()
        }
    }
}