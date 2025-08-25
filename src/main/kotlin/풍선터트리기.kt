import java.util.*
import kotlin.math.min

class 풍선터트리기 {
    class Solution {
        fun solution(a: IntArray): Int {
            var answer: Int = 0

            val balloonSet = TreeSet<Int>()

            if (a.size == 1 || a.size == 2){
                return a.size
            }

            for (i in 2 until a.size) {
                balloonSet.add(a[i])
            }

            var leftMin = a[0]

            for (i in 1 until a.size - 1) {
                val rightMin = balloonSet.first()
                if (!(leftMin < a[i] && a[i] > rightMin)) {
                    answer++
                }

                leftMin = min(leftMin, a[i])
                balloonSet.remove(a[i + 1])
            }
            return answer + 2
        }
    }
}

fun main() {
    val solution = 풍선터트리기.Solution()
    println(solution.solution(intArrayOf(-16,27,65,-2,58,-92,-71,-68,-61,-33)))
}