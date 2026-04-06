import java.util.Scanner

class 안테나 {
    class Solution {
        fun solution(list: MutableList<Int>): Int {
            var secondGroup = 0L
            var firstGroup = 1L
            var currentSum = 0L

            for (i in 1 until list.size) {
                currentSum += (list[i] - list[0])
                secondGroup += 1
            }

            var minIndex = list[0]
            var minSum = currentSum

            for (i in 0 until list.size - 1) {
                currentSum += firstGroup * (list[i+1] - list[i])
                currentSum -= secondGroup * (list[i + 1] - list[i])

                if (currentSum < minSum) {
                    minSum = currentSum
                    minIndex = list[i + 1]
                }
                firstGroup += 1
                secondGroup -= 1
            }

            return minIndex
        }
    }
}



fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()

    val list = mutableListOf<Int>()

    for (i in 0 until n) {
        val location = nextInt()
        list.add(location)
    }

    list.sort()

    val solution = `안테나`.Solution()
    println(solution.solution(list))
}