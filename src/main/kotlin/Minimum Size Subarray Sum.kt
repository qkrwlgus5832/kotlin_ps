import kotlin.math.min

class `Minimum Size Subarray Sum` {
    class Solution {
        fun minSubArrayLen(target: Int, nums: IntArray): Int {
            var left = 0
            var sum = 0
            var length = 0

            for (i in 0 until nums.size) {
                sum += nums[i]

                if (sum >= target) {
                    if (length == 0){
                        length = (i - left) + 1
                    } else {
                        length = min(length, (i - left) + 1)
                    }

                    while(true) {
                        sum -= nums[left]
                        left++

                        if (sum < target) {
                            break
                        }

                        length = min(length, (i - left) + 1)
                    }
                }
            }
            return length
        }
    }
}

fun main() {
    val solution = `Minimum Size Subarray Sum`.Solution()
    println(solution.minSubArrayLen(11, intArrayOf(1,1,1,1,1,1,1,1)))
}