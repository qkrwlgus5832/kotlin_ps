import java.util.PriorityQueue
import java.util.Scanner
import kotlin.math.max

class 강의실 {
    class Solution {
        fun solution(list: MutableList<Triple<Int, Int, Int>>): Int {
            list.sortWith(Comparator { o1, o2 -> o1.second.compareTo(o2.second) })

            val queue = PriorityQueue<Int>()

            var count = 0

            list.forEach {
                if (queue.size > 0 && it.second >= queue.first()) {
                    queue.poll()
                }

                queue.add(it.third)

                count = max(count, queue.size)
            }

            return count
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val list = mutableListOf<Triple<Int, Int, Int>>()

    for (i in 0 until n) {
        val lesionNumber = nextInt()
        val lesionStrat = nextInt()
        val lesionEnd = nextInt()

        list.add(Triple(lesionNumber, lesionStrat, lesionEnd))
    }

    val solution = `강의실`.Solution()
    println(solution.solution(list))
}