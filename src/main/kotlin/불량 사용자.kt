class `불량 사용자` {
    class Solution {
        private var answer = 0
        private var checkSet = HashSet<String>()

        fun isSame(userId: String, bannerId: String): Boolean {
            if (userId.length != bannerId.length) {
                return false
            }

            userId.forEachIndexed { index, element ->
                if (userId[index] != bannerId[index] && bannerId[index] != '*') {
                    return false
                }
            }

            return true
        }

        private fun getSetValue(check: Array<Boolean>): String {
            var result: String = ""
            check.forEachIndexed { index, element ->
                if (check[index]) {
                    result += (index + 1)
                }
            }
            return result
        }

        fun recursion(check: Array<Boolean>, userId: Array<String>, bannerId: Array<String>, index: Int, count: Int) {
            if (count == bannerId.size) {
                val setValue = getSetValue(check)
                if (checkSet.contains(setValue)) {
                    return
                }
                checkSet.add(setValue)
                answer++
            }

            if (index >= bannerId.size) {
                return
            }
            userId.forEachIndexed { userIdIndex, element ->
                if (!check[userIdIndex] && isSame(element, bannerId[index])) {
                    check[userIdIndex] = true
                    recursion(check, userId, bannerId, index + 1, count + 1)
                    check[userIdIndex] = false
                }
                else {
                    recursion(check, userId, bannerId, index + 1, count)
                }
            }
        }

        fun solution(user_id: Array<String>, banned_id: Array<String>): Int {
            recursion(Array<Boolean>(9){false}, user_id, banned_id, 0, 0)

            return answer
        }
    }
}

fun main() {
    val solution = `불량 사용자`.Solution()
    System.out.println(solution.solution(arrayOf("frodo", "fradi", "crodo", "abc123", "frodoc"), arrayOf("fr*d*", "*rodo", "******", "******")))
}