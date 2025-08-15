import kotlin.math.min
import kotlin.math.pow

class 마법의엘리베이터 {
    class Solution {
        private val check: BooleanArray = BooleanArray(100000001)

        private fun DFS(currentStorey: Int, digit: Int, count: Int, digitValue: Int): Int {
            if (currentStorey == 0) {
                return count
            }
            val currentStoreyString = currentStorey.toString()


            val thrownAway = currentStorey / digitValue * digitValue

            if (currentStoreyString[currentStoreyString.length - digit] <= '4') {
                val tmpCount = currentStoreyString[currentStoreyString.length - digit] - '0'
                return DFS(thrownAway, digit + 1, count + tmpCount, digitValue * 10)
            }

            else if (currentStoreyString[currentStoreyString.length - digit] >= '6') {
                val tmpCount = 10 - (currentStoreyString[currentStoreyString.length - digit] - '0')
                return DFS(thrownAway + digitValue, digit + 1, count + tmpCount, digitValue * 10)
            }

            else {
                val count1 = DFS(thrownAway, digit + 1, count + 5, digitValue * 10)
                val count2= DFS(thrownAway + digitValue, digit + 1, count + 5, digitValue * 10)

                return min(count1, count2)
            }
        }


        fun solution(storey: Int): Int {
            return DFS(2554, 1, 0, 10)
        }
    }
}

fun main() {
    val solution = 마법의엘리베이터.Solution()
    System.out.println(solution.solution(2554))
}

