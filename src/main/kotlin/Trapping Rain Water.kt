import kotlin.math.max
import kotlin.math.min

class `Trapping Rain Water` {
    class Solution {
        fun trap(height: IntArray): Int {
            var answer = 0

            for (i in 0 until height.size) {
                var maxLeft = -1
                var maxRight = -1

                for (j in i - 1 downTo 0) {
                    maxLeft = max(maxLeft, height[j])
                }

                for (j in i + 1 until height.size) {
                    if (height[j] >= maxLeft) {
                        maxRight = height[j]
                        break
                    }

                    maxRight = max(maxRight, height[j])
                }

                if (maxLeft == -1 || maxRight == -1) {
                    continue
                }
                if (maxLeft < height[i] || maxRight < height[i]) {
                    continue
                }
                answer += min(maxLeft, maxRight) - height[i]
            }

            return answer
        }
    }
}

fun main() {
    val solution = `Trapping Rain Water`.Solution()
    solution.trap(intArrayOf(4,2,0,3,2,5))
}