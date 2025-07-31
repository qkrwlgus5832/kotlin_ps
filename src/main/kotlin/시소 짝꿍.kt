class `시소짝꿍` {
    class Solution {
        fun solution(weights: IntArray): Long {
            var answer: Long = 0

            weights.sort()

            val weightMap = HashMap<Int, Int>(); // 이전 weight 수를 기록하기 위함

            weights.forEach {  weight ->
                answer += weightMap.getOrDefault(weight, 0)

                if (weight % 2 == 0) {
                    answer += weightMap.getOrDefault(weight / 2, 0)
                }

                if (weight % 3 == 0) {
                    answer += weightMap.getOrDefault(weight * 2 / 3, 0)
                }

                if (weight % 4 == 0) {
                    answer += weightMap.getOrDefault(weight * 3 / 4, 0)
                }

                val currentWeightFrequency = weightMap.getOrDefault(weight, 0)
                weightMap[weight] = currentWeightFrequency + 1
            }

            return answer
        }
    }
}


fun main(args: Array<String>) {
    val solution = 시소짝꿍.Solution()
    System.out.println(solution.solution(
        intArrayOf(100,180,360,100,270)
    ))
}
