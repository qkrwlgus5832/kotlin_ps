import kotlin.math.min

class 에어컨 {
    class Solution {
        fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
            var answer: Int = Int.MAX_VALUE

            val dp = Array<MutableMap<Int, Int>>(onboard.size + 1) {
                mutableMapOf()
            }

            dp[0][temperature] = 0

            for (i in 1 until onboard.size) {
                val mapKeyList = dp[i - 1].keys.toList()

                for (i1 in 0 until mapKeyList.size) {
                    if (onboard[i - 1] == 1) {
                        if (mapKeyList[i1] > t2 || mapKeyList[i1] < t1) {
                            continue
                        }
                    }

                    var newKey = mapKeyList[i1] + 1

                    if (dp[i][newKey] == null) {
                        dp[i][newKey] = dp[i-1][mapKeyList[i1]]!! + a
                    } else {
                        dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!! + a)
                    }

                    newKey = mapKeyList[i1] - 1

                    if (dp[i][newKey] == null) {
                        dp[i][newKey] = dp[i-1][mapKeyList[i1]]!! + a
                    } else {
                        dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!! + a)
                    }


                    if (mapKeyList[i1] > temperature) {
                        newKey = mapKeyList[i1] - 1

                        if (dp[i][newKey] == null) {
                            dp[i][newKey] = dp[i-1][mapKeyList[i1]]!!
                        } else {
                            dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!!)
                        }
                    }

                    else if (mapKeyList[i1] < temperature) {
                        newKey = mapKeyList[i1] + 1

                        if (dp[i][newKey] == null) {
                            dp[i][newKey] = dp[i-1][mapKeyList[i1]]!!
                        } else {
                            dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!!)
                        }
                    } else {
                        newKey = mapKeyList[i1]

                        if (dp[i][newKey] == null) {
                            dp[i][newKey] = dp[i-1][mapKeyList[i1]]!!
                        } else {
                            dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!!)
                        }
                    }

                    newKey = mapKeyList[i1]

                    if (dp[i][newKey] == null) {
                        dp[i][newKey] = dp[i-1][mapKeyList[i1]]!! + b
                    } else {
                        dp[i][newKey] = min(dp[i][newKey]!!, dp[i-1][mapKeyList[i1]]!! + b)
                    }
                }
            }

            val candidateList = dp[onboard.size - 1].toList()

            for (i in 0 until candidateList.size) {
                if (onboard[onboard.size - 1] == 1) {
                    if (candidateList[i].first >= t1 && candidateList[i].first <= t2) {
                        answer = min(candidateList[i].second, answer)
                    }
                } else {
                    answer = min(candidateList[i].second, answer)
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = 에어컨.Solution()
    solution.solution(
        11,
        8,
        10,
        10,
        100,
        intArrayOf(0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1)
    )
}