import java.util.*

class `공유기 설치` {
    class Solution {
        fun isAnswer(houseArray: Array<Int>, c: Int, current: Int): Boolean {
            var before = houseArray[0]
            var count = 1

            for (i in 1 until houseArray.size) {
                if (houseArray[i] - before >= current) {
                    before = houseArray[i]
                    count++
                }

                if (count >= c) {
                    return true
                }
            }

            return false
        }

        private fun binarySearch(houseArray: Array<Int>, c: Int, start: Int, end: Int): Int {
            if (start == end) {
                return start
            }

            if (start + 1 == end) {
                if (isAnswer(houseArray, c, end)) {
                    return end
                }
                return start
            }
            if (isAnswer(houseArray, c, (start + end) / 2)) {
                return binarySearch(houseArray, c, (start + end) / 2 , end)
            }
            return binarySearch(houseArray, c, start, (start  + end) / 2 - 1)
        }

        fun solution(houseArray: Array<Int>, c: Int): Int {
            return binarySearch(houseArray, c, 0, 1000000000)
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val c = nextInt()

    val houseArray = Array(n) { 0 }

    for (i in 0 until n) {
        val tmp = nextInt()
        houseArray[i] = tmp
    }

    houseArray.sort()

    val solution = `공유기 설치`.Solution()
    println(solution.solution(houseArray, c))
}