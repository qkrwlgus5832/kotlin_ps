import java.util.*

class `N으로 표현` {
    class Solution {
        private fun plus(current: Long, N: Long): Long {
            return current + N
        }

        private fun sub(current: Long, N: Long): Long {
            return current - N
        }

        private fun div(current: Long, N: Long): Long {
            return current / N
        }

        private fun mul(current: Long, N: Long): Long {
            return current * N
        }

        private fun setPush(currentSet: HashSet<Long>, value: Long) {
            currentSet.add(value)
        }

        private fun getAllCases(beforeSet: HashSet<Long>, afterSet: HashSet<Long>, currentSet: HashSet<Long>) {
            beforeSet.forEach { before ->
                afterSet.forEach { after ->
                    val plus = plus(before, after)
                    val sub = sub(before, after)
                    if (after != 0L) {
                        val div = div(before, after)
                        setPush(currentSet, div)
                    }
                    val mul = mul(before, after)

                    setPush(currentSet, plus)
                    setPush(currentSet, sub)
                    setPush(currentSet, mul)
                }
            }
        }

        fun solution(N: Int, number: Int): Int {
            val dp = Array(9) {
                HashSet<Long>()
            }


            dp[1] = HashSet<Long>()
            dp[1].add(N.toLong())

            if (N == number){
                return 1
            }

            for (i in 2..8) {
                var tmpSet = HashSet<Long>()

                for (j in 1 until i) {
                    getAllCases(dp[j], dp[i-j], tmpSet)
                }

                tmpSet.add(N.toString().repeat(i).toLong())

                if (tmpSet.contains(number.toLong())) {
                    return i
                }

                dp[i] = tmpSet
            }

            return -1
        }
    }
}

fun main() {
    val solution = `N으로 표현`.Solution()
    println(solution.solution(3, 333))
}