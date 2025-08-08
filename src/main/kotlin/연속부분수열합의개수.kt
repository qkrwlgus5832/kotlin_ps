class 연속부분수열합의개수 {
    class Solution {
        fun solution(elements: IntArray): Int {
            var answer: Int = 0

            val elementLength = elements.size

            val set = HashSet<Int>()

            var sumElements: IntArray = intArrayOf()

            for (i in 0 until elementLength) {
                if (i == 0) {
                    sumElements += elements[i]
                }
                else {
                    sumElements += elements[i] + sumElements[i-1]
                }
            }
            for (i in 0 until elementLength) {
                var start = 0
                var end = i
                var sum = sumElements[i]
                set.add(sum)

                if (end == elementLength - 1) {
                    continue
                }

                while(true) {
                    sum -= elements[start]
                    sum += elements[end + 1]
                    start++
                    end++

                    set.add(sum)

                    if (start == elementLength - 1) {
                        start = start % (elementLength - 1)
                    }

                    if (end == elementLength - 1) {
                        end = -1
                    }

                    if (start == 0) {
                        break
                    }
                }
            }
            return set.size
        }
    }
}

fun main() {
    val solution = 연속부분수열합의개수.Solution()
    solution.solution(intArrayOf(7,9,1,1,4))
}