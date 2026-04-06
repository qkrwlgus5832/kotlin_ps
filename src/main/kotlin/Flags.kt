import kotlin.math.max

class Flags {
    private var answer = 0

    private fun isAnswer(peeksIndexList: List<Int>, k: Int): Pair<Boolean, Int> {
        var sum = 0
        var count = 0
        var flag = false

        for (i in 0 until peeksIndexList.size) {
            if (sum + peeksIndexList[i] >= k) {
                sum = 0
                count++
            }
            else {
                sum += peeksIndexList[i]
            }

            if (count + 1 >= k) {
                flag = true
            }
        }

        if (k == 1) {
            return Pair(true, 1)
        }
        return Pair(flag, k)
    }

    private fun binarySearch(peeksIndexList: List<Int>, start: Int, end: Int) {
        if (start == end) {
            isAnswer(peeksIndexList, start).let {
                if (it.first) {
                    answer = max(answer, it.second)
                }
            }
            return
        }
        else if (start + 1 == end) {
            isAnswer(peeksIndexList, start).let {
                if (it.first) {
                    answer = max(answer, it.second)
                }
            }

            isAnswer(peeksIndexList, end).let {
                if (it.first) {
                    answer = max(answer, it.second)
                }
            }
            return
        }

        val middle = (start + end) / 2
        val result = isAnswer(peeksIndexList, middle)

        if (result.first) {
            binarySearch(peeksIndexList, middle, end)
        }
        else {
            binarySearch(peeksIndexList, start, middle - 1)
        }
    }

    fun solution(A: IntArray): Int {
        val peeksIndexList = mutableListOf<Int>()

        for (i in 0 until A.size) {
            if (i > 0 && i < A.size - 1 && A[i - 1] < A[i] && A[i] > A[i + 1]) {
                peeksIndexList.add(i)
            }
        }

        val peeksIndexDiffList = mutableListOf<Int>()

        for (i in 0 until peeksIndexList.size - 1) {
            if (peeksIndexList.size >= 1) {
                peeksIndexDiffList.add(peeksIndexList[i + 1] - peeksIndexList[i])
            }
        }

        if (peeksIndexList.size >= 1) {
            binarySearch(peeksIndexDiffList, 1, peeksIndexDiffList.size + 1)
        }

        return this.answer
    }
}

fun main() {
    val flags = Flags()
    flags.solution(intArrayOf(1, 3, 2))
}