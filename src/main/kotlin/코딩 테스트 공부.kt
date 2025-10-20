import kotlin.math.max
import kotlin.math.min

class `코딩 테스트 공부` {
    class Solution {
        var answer = Int.MAX_VALUE
        val check = Array(3001) {
            Array(3001) {
                Int.MAX_VALUE
            }
        }

        fun recursion(alp: Int, cop: Int, maxAlp: Int, maxCop: Int, cost: Int, problems: Array<IntArray>) {
            if (alp >= maxAlp && cop >= maxCop) {
                answer = min(answer, cost)
                return
            }

            if (cost >= answer) {
                return
            }

            if (alp + 1 <= maxAlp && check[alp + 1][cop] > cost + 1) {
                check[alp + 1][cop] = cost + 1
                recursion(alp + 1, cop, maxAlp, maxCop, cost + 1, problems)
            }

            if (cop + 1 <= maxCop && check[alp][cop + 1] > cost + 1) {
                check[alp][cop + 1] = cost + 1
                recursion(alp, cop + 1, maxAlp, maxCop, cost + 1, problems)
            }

            for (i in 0 until problems.size) {
                if (problems[i][0] <= alp && problems[i][1] <= cop) {
                    val newAlp = alp + problems[i][2]
                    val newCop = cop + problems[i][3]
                    val newCost = cost + problems[i][4]

                    if (check[newAlp][newCop] > newCost) {
                        check[newAlp][newCop] = newCost
                        recursion(newAlp, newCop, maxAlp, maxCop, newCost, problems)
                    }
                }
            }


        }

        fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
            var maxAlp = 0
            var maxCop = 0

            for (i in 0 until problems.size) {
                val problem = problems[i]

                maxAlp = max(maxAlp, problem[0])
                maxCop = max(maxCop, problem[1])
            }

            recursion(alp, cop, maxAlp, maxCop, 0, problems)
            return answer
        }
    }
}

fun main() {
    val solution = `코딩 테스트 공부`.Solution()
    solution.solution(10, 10,
        arrayOf(
            intArrayOf(10, 15, 2, 1, 2),
            intArrayOf(20, 20, 3, 3, 4),
        )
    )
}