class 연속된부분수열의합 {
    class Solution {
        fun solution(sequence: IntArray, k: Int): IntArray {
            var answer: IntArray = intArrayOf()
            val map = HashMap<Int, Int>()

            val newSequence: MutableList<Int> = mutableListOf()

            sequence.forEachIndexed { index, item ->
                if (index == 0) {
                    newSequence.add(item)
                    return@forEachIndexed
                }
                newSequence.add(item + newSequence[index - 1])
            }

            var range: Pair<Int, Int>? = null
            var length = Integer.MAX_VALUE

            newSequence.forEachIndexed { index, item ->
                if (newSequence[index] == k) {
                    if (length <= index + 1) {
                        map[item] = index
                        return@forEachIndexed
                    }
                    range = Pair(0, index)
                    length = index + 1
                }
                if (map.containsKey(newSequence[index] - k)) {
                    if (length <= index - map.get(newSequence[index] - k)!!) {
                        map[item] = index
                        return@forEachIndexed
                    }

                    range = Pair(map.get(newSequence[index] - k)!! + 1, index)
                    length = index - map.get(newSequence[index] - k)!!
                }

                map[item] = index
            }

            answer += range!!.first
            answer += range!!.second

            return answer
        }
    }
}

fun main() {
    val solution = 연속된부분수열의합.Solution()
    System.out.println(solution.solution(intArrayOf(2, 2, 2, 2, 2), 6))
}