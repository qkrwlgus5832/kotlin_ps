class 할인행사 {
    class Solution {
        fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
            var answer: Int = 0

            val map = HashMap<String, Int>()

            var sumCount = 0

            want.forEachIndexed { index, item ->
                map[item] = number[index]
                sumCount += number[index]
            }

            discount.forEachIndexed { index, discountItem ->
                val copiedMap = HashMap(map)
                var copiedSumCount = sumCount

                for (i in index until index + 10) {
                    if (i >= discount.size) {
                        break
                    }
                    if (copiedMap.containsKey(discount[i]) && copiedMap[discount[i]]!! > 0) {
                        copiedMap[discount[i]] = copiedMap[discount[i]]!!.minus(1)
                        copiedSumCount--
                    }
                    if (copiedSumCount == 0) {
                        answer++
                        break
                    }
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = 할인행사.Solution()
    solution.solution(arrayOf("apple"), intArrayOf(10), arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"))
}