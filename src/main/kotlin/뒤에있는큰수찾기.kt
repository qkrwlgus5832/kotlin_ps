import java.util.*

class 뒤에있는큰수찾기 {
    class Solution {
        private val stack = Stack<Int>()

        fun solution(numbers: IntArray): IntArray {
            val answer = IntArray(numbers.size)

            for (i in numbers.size - 1 downTo 0) {
                var backwardBigNumber = -1

                while(!stack.empty()) {
                    if (stack.peek() > numbers[i]) {
                        backwardBigNumber = stack.peek()
                        break
                    }
                    else {
                        stack.pop()
                    }
                }

                stack.push(numbers[i])
                answer[i] = backwardBigNumber
            }
            return answer
        }
    }
}

fun main() {
    val solution = 뒤에있는큰수찾기.Solution()
    solution.solution(intArrayOf(2, 3, 3, 5))
}