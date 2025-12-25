class 카운트다운 {
    data class Score(
        var count: Int = -1,
        var boolOrSingleCount: Int = -1
    )

    class Solution {
        private fun countDownDp(DP: Array<Score>, current: Int, i: Int, isSingleOrBool: Boolean) {
            val boolOrSingleCount = if (isSingleOrBool) 1 else 0
            if (current - i >= 0) {
                if (DP[current - i].count + 1 < DP[current].count) {
                    DP[current].count = DP[current - i].count + 1
                    DP[current].boolOrSingleCount = DP[current - i].boolOrSingleCount + boolOrSingleCount
                }
                else if (DP[current - i].count + 1 == DP[current].count){
                    if (DP[current].boolOrSingleCount < DP[current - i].boolOrSingleCount + boolOrSingleCount) {
                        DP[current].boolOrSingleCount = DP[current - i].boolOrSingleCount + boolOrSingleCount
                    }
                }
            }
        }
        private fun countDown(DP: Array<Score>, target: Int) {
            for (current in 1..target) {
                val count = if (current % 20 == 0) current / 20 else current / 20 + 1
                DP[current] = Score(count, count)

                for (i in 1..20) {
                    countDownDp(DP, current, i, true)
                    countDownDp(DP, current, i * 2, false)
                    countDownDp(DP, current, i * 3, false)
                }

                countDownDp(DP, current, 50, true)
            }
        }

        fun solution(target: Int): IntArray {
            var answer: IntArray = intArrayOf()

            val DP: Array<Score> = Array(target + 1) { Score() }

            DP[0] = Score(0, 0)

            countDown(DP, target)
            return intArrayOf(DP[target].count, DP[target].boolOrSingleCount)
        }
    }
}

fun main() {
    val solution = 카운트다운.Solution()
    solution.solution(173)
}