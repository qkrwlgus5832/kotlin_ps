import kotlin.math.min
import kotlin.math.sqrt
import kotlin.math.pow

class N개의최소공배수 {
    class Solution {
        private fun getDenominators(x: Int): MutableList<Long> {
            val list = mutableListOf<Long>()

            var number = x

            for (i in 2 .. sqrt(x.toDouble()).toInt()) {
                while (number % i == 0) {
                    list.add(i.toLong())
                    number = number / i
                }
            }

           list.add(number.toLong())

            return list
        }

        private fun getResultByList(list: MutableList<Long>): Long {
            val set = list.distinct()

            var result = 1L
            set.forEachIndexed { index, element ->
                if (element == 1L){
                    return@forEachIndexed
                }
                val count = list.count { it == element}

                result *= (element.toDouble().pow(count).toLong())
            }
            return result
        }

        private fun getResult(arr: IntArray): Long {
            val maxValue = arr.maxOrNull()!!

            var result = Long.MAX_VALUE

            for (i in 1..maxValue) {
                var denominators = getDenominators(i)
                val currentDenominators = mutableListOf<Long>()

                for (j in 0 until arr.size) {
                    var arrDenominators = getDenominators(arr[j])
                    denominators.forEach {
                        arrDenominators.remove(it)
                    }

                    currentDenominators.forEach {
                        arrDenominators.remove(it)
                    }

                    currentDenominators.addAll(arrDenominators)
                }

                val current: Long = getResultByList(currentDenominators)

                result = min(result, current * i)
            }

            return result
        }

        fun solution(arr: IntArray): Long {
            return getResult(arr)
        }
    }
}

fun main() {
    val solution = N개의최소공배수.Solution()
    System.out.println(solution.solution(intArrayOf(2, 3, 4)))
}