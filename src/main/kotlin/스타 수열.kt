import kotlin.math.max

class `스타 수열` {
    class Solution {
        private fun getByElement(element: Int, a: IntArray): Int {
            val checkArray = BooleanArray(a.size)

            var count = 0

            var flag = false

            for (i in 0 until a.size) {
                if (a[i] == element) {
                    if (i - 1 >= 0 && checkArray[i - 1] == false && a[i - 1] != element) {
                        checkArray[i - 1] = true
                        checkArray[i] = true
                        count += 2
                    } else if (i + 1 < a.size && checkArray[i + 1] == false && a[i + 1] != element) {
                        checkArray[i + 1] = true
                        checkArray[i] = true
                        count += 2
                    } else {
                        continue
                    }
                }
            }

            return if (count == 2) 0 else count
        }

        fun solution(a: IntArray): Int {
            var answer: Int = 0

            val first = a[0]
            val last = a[a.size - 1]

            val map = HashMap<Int, Int>()

            var maxCount = 0
            var maxElement = -1

            if (a.size < 4) {
                return 0
            }

            for (i in 0 until a.size) {
                val count = map.getOrDefault(a[i], 0) + 1
                map.put(a[i], count)

                if (count > maxCount) {
                    maxCount = count
                    maxElement = a[i]
                }
            }

            answer = max(getByElement(maxElement, a), answer)
            answer = max(getByElement(first, a), answer)
            answer = max(getByElement(last, a), answer)

            return answer
        }
    }
}