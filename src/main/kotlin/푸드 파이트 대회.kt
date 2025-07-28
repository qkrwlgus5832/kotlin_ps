class 푸드파이트대회 {
    class Solution {
        fun solution(food: IntArray): String {
            var answer: StringBuffer = StringBuffer()

            for (index in 1..food.size - 1) {
                for (i in 1..food[index] / 2) {
                    answer.append(index)
                }
            }

            answer.append('0')

            for (index in food.lastIndex downTo 1) {
                for (i in 1..food[index] / 2) {
                    answer.append(index)
                }
            }

            return answer.toString()
        }
    }
}

fun main(args: Array<String>) {
    val solution = 푸드파이트대회.Solution()
    System.out.println(solution.solution(
        intArrayOf(1, 3, 4, 6)
    ))
}


