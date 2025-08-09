import java.util.*

class 택배상자 {
    class Solution {
        fun solution(order: IntArray): Int {
            var answer: Int = 0

            val stack = Stack<Int>()

            var currentBoxIndex = 1
            var currentOrderIndex = 0

            while(true) {
                if (currentOrderIndex >= order.size) {
                    break
                }

                if (order[currentOrderIndex] != currentBoxIndex) {
                    if (stack.size > 0 && stack.peek() == order[currentOrderIndex]) {
                        stack.pop()
                        answer++
                        currentOrderIndex++
                    }
                    else if (stack.size > 0 && stack.peek() > order[currentOrderIndex]) {
                        break
                    }
                    else {
                        stack.push(currentBoxIndex)
                        currentBoxIndex++
                    }
                }
                else if (currentBoxIndex == order[currentOrderIndex]) {
                    answer++
                    currentBoxIndex++
                    currentOrderIndex++
                }

            }
            return answer
        }
    }
}

fun main() {
    val solution = 택배상자.Solution()
    solution.solution(intArrayOf(5, 4, 3, 2, 1))
}