class `3Sum` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<MutableList<Int>>()
        val check = HashMap<Int, Boolean>()

        for (i in 0 until nums.size) {
            if (check.containsKey(nums[i])) {
                continue
            }

            check[nums[i]] = true

            var left = i + 1
            var right = nums.size - 1

            var current = mutableListOf<Int>(nums[i])

            while (true) {
                if (left >= right) {
                    break
                }
                if (nums[left] + nums[right] == nums[i] * - 1) {
                    current.add(nums[left])
                    current.add(nums[right])
                    result.add(current)
                    current = mutableListOf(nums[i])
                    left++
                    continue
                }
                if (nums[left] + nums[right] < nums[i] * - 1) {
                    left++
                }
                else {
                    right--
                }
            }
        }

        val sortedList = result.sortedWith(
            compareBy<List<Int>> {it[0]}.thenBy {it[1]}.thenBy { it[2] }
        )

        val resultCheck = BooleanArray(sortedList.size)
        val answer = mutableListOf<MutableList<Int>>()

        for (i in 1 until sortedList.size) {
            if (sortedList[i][0] == sortedList[i-1][0] && sortedList[i][1] == sortedList[i-1][1] && sortedList[i][2] == sortedList[i-1][2]) {
                resultCheck[i] = true
            }
        }

        for (i in 0 until sortedList.size) {
            if(resultCheck[i] == false) {
                answer.add(sortedList[i])
            }
        }
        return answer
    }
}

fun main() {
    val solution = `3Sum`()
    println(solution.threeSum(intArrayOf(-1,0,1,2,-1,-4)))
}