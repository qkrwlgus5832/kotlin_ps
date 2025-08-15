import kotlin.math.max

class 숫자카드나누기 {
    class Solution {
        private fun gcd(a: Int, b: Int): Int {
            if (b==0) return a
            else return gcd(b, a % b)
        }

        private fun canDivide(gcd: Int, array: IntArray): Boolean {
            array.forEach {
                if (it % gcd == 0) {
                    return true
                }
            }

            return false
        }
        fun solution(arrayA: IntArray, arrayB: IntArray): Int {
            var answer: Int = 0

            arrayA.sort()
            arrayB.sort()

            var gcdA = arrayA[0]
            var gcdB = arrayB[0]

            for (i in 1 until arrayA.size) {
                gcdA = gcd(arrayA[i], gcdA)
                gcdB = gcd(arrayB[i], gcdB)
            }

            if (!canDivide(gcdA, arrayB) && !canDivide(gcdB, arrayA)) {
                answer = max(gcdA, gcdB)
            } else if (!canDivide(gcdA, arrayB)) {
                answer = gcdA
            } else if (!canDivide(gcdB, arrayA)) {
                answer = gcdB
            }

            if (answer == 1) {
                return 0
            }
            return answer
        }
    }
}

fun main() {
    val solution = 숫자카드나누기.Solution()
    solution.solution(intArrayOf(14, 35, 119), intArrayOf(18, 30, 102))
}