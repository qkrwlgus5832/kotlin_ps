class `다단계 칫솔 판매` {
    class Solution {
        val staffMap = HashMap<String, Int>()
        val staffGraph = HashMap<String, MutableList<String>>()

        private fun DFS(current: String, amount: Int) {
            if (current == "-" || amount <= 0) {
                return
            }

            val span = (amount * 0.1).toInt()

            staffMap[current] = staffMap[current]!! + (amount - span)
            DFS(staffGraph[current]?.get(0) ?: "-", span)
        }

        private fun setStaffSellingAmount(sellerList: Array<String>, amount: IntArray) {
            for (i in 0 until sellerList.size) {
                val seller = sellerList[i]
                staffMap[seller] = staffMap[seller]!! + (amount[i] * 100 * 0.9).toInt()
                DFS(staffGraph[seller]?.get(0) ?: "-", (amount[i] * 100 * 0.1).toInt())
            }
        }

        fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
            var answer = IntArray(enroll.size) { 0 }

            for (i in 0 until enroll.size) {
                staffMap[enroll[i]] = 0

                if (referral[i] != "-") {
                    staffGraph[enroll[i]] = mutableListOf<String>().apply {
                        this.add(referral[i])
                    }
                }
            }

            setStaffSellingAmount(seller, amount)

            for (i in 0 until enroll.size) {
                answer[i] = staffMap[enroll[i]]!!
            }

            return answer
        }
    }
}

fun main() {
    val solution = `다단계 칫솔 판매`.Solution()
    solution.solution(
        arrayOf("john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"),
        arrayOf("-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"),
        arrayOf("sam", "emily", "jaimie", "edward"),
        intArrayOf(2, 3, 5, 4)
    )
}