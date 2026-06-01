class `Subarray Sum Equals K` {
    class Solution {
        private val set = HashSet<Int>()
        private val map = HashMap<Int, Int>()

        fun subarraySum(nums: IntArray, k: Int): Int {
            val dp = Array<Int>(nums.size + 1) { 0 }

            dp[0] = nums[0]
            set.add(dp[0])
            map[dp[0]] = 1

            var count = 0

            for (i in 1 until nums.size) {
                dp[i] = dp[i-1] + nums[i]

                if (dp[i] == k) {
                    count++
                }
                if (set.contains(dp[i] - k)) {
                    count += map[dp[i] - k]!!
                }

                set.add(dp[i])
                map[dp[i]] = (map[dp[i]] ?: 0) + 1
            }

            if (nums[0] == k) {
                count++
            }

            return count
        }
    }
}

fun main() {
    val solution = `Subarray Sum Equals K`.Solution()
    println(solution.subarraySum(intArrayOf(0,0,0,0,0,0,0,0,0,0), 0))
}