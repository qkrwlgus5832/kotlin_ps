import kotlin.math.sqrt

class k진수에서소수개수구하기 {
    class Solution {
        private fun isPrimeNumber(number: Long): Boolean {
            if (number == 1L){
                return false
            }
            for (i in 2..sqrt(number.toDouble()).toLong()) {
                if (number % i == 0L) {
                    return false
                }
            }

            return true
        }

        fun solution(n: Int, k: Int): Int {
            var answer: Int = 0

            var kadecimal: StringBuilder = StringBuilder()
            var copiedN = n

            while(true) {
                val span = copiedN % k
                kadecimal.insert(0, span)
                copiedN /= k

                if (copiedN == 0) {
                    break
                }
            }

            var numberString: StringBuilder = StringBuilder()
            var numberList = arrayOf<Long>()

            for (i in 0 until kadecimal.length) {
                if (kadecimal[i] == '0') {
                    if (numberString.length > 0) {
                        numberList += numberString.toString().toLong()
                        numberString.clear()
                    }
                }
                else {
                    numberString.append(kadecimal[i])
                }
            }

            if (numberString.length > 0) {
                numberList += numberString.toString().toLong()
            }

            numberList.forEach { number ->
                if (isPrimeNumber(number)) {
                    answer++
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = k진수에서소수개수구하기.Solution()
    solution.solution(110011, 10)
}