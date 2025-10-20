class `봉인된 주문` {
    class Solution {
        private fun plus(order: StringBuilder): String {
            for (i in order.length - 1 downTo 0) {
                if (order[i] == 'z') {
                    order[i] = 'a'
                }
                else {
                    order[i] = 'a' + (order[i] - 'a' + 1)
                    return order.toString()
                }
            }

            order.insert(0, 'a')
            return order.toString()
        }

        private fun getOrderByNumber(n: Long): String {
            var currentN = n
            val order = StringBuilder()

            while (true) {
                if (currentN == 0L) {
                    break
                }
                val span = (currentN % 26).toInt()
                if (span == 0) {
                    order.append('z')
                    currentN = currentN / 26 - 1
                    continue
                }
                else {
                    order.append('a' + (span - 1))
                }
                currentN /= 26
            }
            return order.toString().reversed()
        }

        fun solution(n: Long, bans: Array<String>): String {
            var currentN = n
            var digitMultipliedNum = 1L
            var digitCount = 0L

            bans.sortWith { a, b ->
                if (a.length == b.length) {
                    a.compareTo(b)
                } else {
                    a.length - b.length
                }

            }


            var order = getOrderByNumber(n)

            var count = 0

            for (i in 0 until bans.size) {
                if (bans[i].length < order.length) {
                    count++
                    continue
                }
                else if (bans[i].length == order.length) {
                    if (bans[i] < order) {
                        count++
                    }
                }
                else {
                    break
                }
            }

            val banSet = bans.toSet()

            while(true) {
                if (banSet.contains(order)) {
                    count++
                }

                if (count == 0) {
                    break
                }

                order = plus(StringBuilder(order))
                count--

            }

            return order
        }
    }
}

fun main() {
    val solution = `봉인된 주문`.Solution()
//    solution.solution(700, arrayOf())
    solution.solution(3817158266467285, arrayOf("dfdfdfd"))
}