import java.util.Scanner
import kotlin.math.max

class 겹치는건 {
    class Solution {
        fun solution(n: Int, k: Int, inputArray: Array<Int>): Int {
            val dequeList = Array<ArrayDeque<Int>>(1000001) {
                ArrayDeque<Int>()
            }

            var count = 0
            var answer = 0
            var beforeCutIndex = -1

            for (i in 0 until inputArray.size) {
                if (dequeList[inputArray[i]].size == k) {
                    val beforeIndex = dequeList[inputArray[i]].first()

                    if (beforeIndex > beforeCutIndex) {
                        answer = max(answer, count)
                        count = (i - beforeIndex)
                        beforeCutIndex = i - count
                    }
                    else {
                        count++
                    }

                    dequeList[inputArray[i]].removeFirst()
                    dequeList[inputArray[i]].addLast(i)
                }
                else {
                    dequeList[inputArray[i]].addLast(i)
                    count++
                }
            }

            return max(answer, count)
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val k = nextInt()

    val inputArray = Array(n) { 0 }

    for (i in 0 until n) {
        val a = nextInt()
        inputArray[i] = a
    }

    val solution = `겹치는건`.Solution()

    println(solution.solution(n, k, inputArray))
}