class `롤케이크 자르기` {
    class Solution {
        val set = HashSet<Int>()
        val map = HashMap<Int, Int>()

        fun solution(topping: IntArray): Int {
            var answer: Int = 0

            var flavorCount = 0

            for (i in 0 until topping.size) {
                if (map.containsKey(topping[i])) {
                    map.put(topping[i], map[topping[i]]!! + 1)
                }
                else {
                    flavorCount++
                    map.put(topping[i], 1)
                }
            }

            for (i in 0 until  topping.size) {
                val count = map.get(topping[i])!!
                if (count == 1) {
                    flavorCount--
                }
                map.put(topping[i], count - 1)
                set.add(topping[i])

                if (set.size == flavorCount) {
                    answer++
                }
            }
            return answer
        }
    }
}

fun main() {
    val solution = `롤케이크 자르기`.Solution()
    solution.solution(intArrayOf(1, 2, 3, 1, 4))
}