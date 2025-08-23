import java.util.*
import kotlin.math.sqrt

class 억억단을외우자 {
    class Solution {
        private val divideCount = IntArray(5000001)

        private fun setAppearCount(standard: Int) {
            for (i in 1..standard) {
                var j = i

                while (j <= standard) {
                    divideCount[j]++
                    j += i
                }
            }
        }

        private fun getAppearCount(standard: Int): Int {
            return divideCount[standard]
        }

        fun solution(e: Int, starts: IntArray): IntArray {
            var answer = IntArray(starts.size) { 0 }

            val maxSize = sqrt(e.toDouble()).toInt() * 2 + 1

            val treeSetList = Array<TreeSet<Int>>(maxSize) {
                TreeSet()
            }

            setAppearCount(e)

            for (i in 1..e) {
                val count = getAppearCount(i)
                treeSetList[count].add(i)
            }

            starts.forEachIndexed { index, start ->
                for (i in treeSetList.size - 1 downTo 0) {
                    val higher = treeSetList[i].ceiling(start)
                    if (higher != null) {
                        answer[index] = higher
                        break
                    }
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = 억억단을외우자.Solution()
    solution.solution(8, intArrayOf(1, 3, 7))
}