import java.util.*

class 이중우선순위큐 {
    class Solution {
        private fun pop(value: Int, map: TreeMap<Int, Int>, set: TreeSet<Int>) {
            map[value] = map.get(value)!! - 1
            if (map[value] == 0) {
                set.remove(value)
            }
        }

        fun solution(operations: Array<String>): IntArray {
            val set = TreeSet<Int>()
            val map = TreeMap<Int, Int>()

            operations.forEach { operation ->
                val number = operation.substring(2).toInt()

                if (operation[0] == 'I') {
                    set.add(number)
                    map[number] = map.getOrDefault(number, 0) + 1
                } else if (operation[0] == 'D') {
                    if (set.size == 0) {
                        return@forEach
                    }

                    if (number == 1) {
                        val maxValue = set.last()
                        pop(maxValue, map, set)
                    } else if (number == -1) {
                        val minValue = set.first()
                        pop(minValue, map, set)
                    }
                }
            }
            if (set.size == 0) {
                return intArrayOf(0, 0)
            }
            return intArrayOf(set.last(), set.first())
        }
    }
}

fun main() {
    val solution = 이중우선순위큐.Solution()
    solution.solution(arrayOf("I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"))
}