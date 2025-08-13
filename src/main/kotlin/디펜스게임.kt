import java.util.TreeSet

class 디펜스게임 {
    class Solution {
        fun solution(n: Int, k: Int, enemy: IntArray): Int {
            var answer: Int = 0

            var tmpN = 0
            var tmpK = 0

            val map = HashMap<Int, Int>()
            val set = TreeSet<Int>()

            if (k >= enemy.size) {
                return enemy.size
            }

            for (i in 0 until k) {
                set.add(enemy[i])
                map[enemy[i]] = map.getOrDefault(enemy[i], 0) + 1
            }

            for (i in k until enemy.size) {
                if (enemy[i] > set.first()) {
                    if (tmpN + set.first() <= n) {
                        tmpN = tmpN + set.first()

                        set.add(enemy[i])
                        map[enemy[i]] = map.getOrDefault(enemy[i], 0) + 1

                        map[set.first()] = map.getOrDefault(set.first(), 0) - 1
                        if (map.getOrDefault(set.first(), 0) == 0) {
                            val first = set.first()
                            set.remove(first)
                        }
                    } else if (tmpN + enemy[i] <= n) {
                        tmpN = tmpN + enemy[i]
                    } else {
                        return i
                    }
                } else if (tmpN + enemy[i] <= n) {
                    tmpN = tmpN + enemy[i]
                } else {
                    return i
                }
            }

            return enemy.size
        }
    }
}

fun main() {
    val solution = 디펜스게임.Solution()
    System.out.println(solution.solution(2, 4, intArrayOf(3, 3, 3, 3)))
}