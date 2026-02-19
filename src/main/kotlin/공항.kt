import java.util.*

class 공항 {
    class Solution {
        private fun find(gi: Int, unionFind: Array<Int>): Int {
            if (unionFind[gi] == -1) {
                return gi
            }

            if (unionFind[gi] == 0) {
                return 0
            }

            val finded = find(unionFind[gi], unionFind)


            unionFind[gi] = finded

            return finded
        }

        fun solution(g: Int, p: Int, giArray: Array<Int>): Int {
            val set = TreeSet<Int>()
            var count = 0

            val unionFind = Array(g + 1) { -1 }

            for (i in 0 until giArray.size) {
                if (unionFind[giArray[i]] == -1) {
                    unionFind[giArray[i]] = giArray[i] - 1
                    count++
                    continue
                }

                val finded = find(giArray[i], unionFind)

                if (finded == 0) {
                    break
                }

                unionFind[finded] = finded - 1
                count++
            }

            return count
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val g = nextInt()
    val p = nextInt()

    val giArray = Array(p) { 0 }

    for (i in 0 until p) {
        val gi = nextInt()
        giArray[i] = gi
    }

    val solution = `공항`.Solution()
    println(solution.solution(g, p, giArray))
}