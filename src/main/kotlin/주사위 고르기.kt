class `주사위 고르기` {
    class Solution {
        private lateinit var originDiceGraph: Array<IntArray>
        private var maxWinCount = -1
        private var maxWinDiceList = mutableListOf<Int>()

        private fun numberRecursion(
            chosenDiceList: MutableList<Int>,
            count: Int,
            sum: Int,
            result: MutableList<Int>
        ) {
            if (chosenDiceList.size == count) {
                result.add(sum)
                return
            }
            var currentDiceIndex = chosenDiceList[count]

            for (i in 0 until 6) {
                val next = originDiceGraph[currentDiceIndex][i]
                numberRecursion(chosenDiceList, count + 1,sum + next, result)
            }
        }

        fun lowerBound(arr: MutableList<Int>, target: Int): Int {
            var left = 0
            var right = arr.size  // ⚠️ size까지

            while (left < right) {
                val mid = (left + right) / 2
                if (arr[mid] < target) {
                    left = mid + 1
                } else {
                    right = mid
                }
            }
            return left
        }

        private fun diceRecursion(dice: Array<IntArray>, aChosendiceList: MutableList<Int>, diceIndex: Int, used: BooleanArray) {
            if (aChosendiceList.size == dice.size / 2) {
                val aSumList = mutableListOf<Int>()
                val bSumList = mutableListOf<Int>()
                val bChosendiceList = mutableListOf<Int>()

                for (i in 0 until dice.size) {
                    if (used[i] == false) {
                        bChosendiceList.add(i)
                    }
                }
                numberRecursion(aChosendiceList,   0, 0, aSumList)
                numberRecursion(bChosendiceList, 0, 0, bSumList)

                bSumList.sort()
                var winCount = 0

                for (i in 0 until aSumList.size) {
                    winCount += lowerBound(bSumList, aSumList[i])
                }

                if (winCount > maxWinCount) {
                    maxWinDiceList = aChosendiceList.toMutableList()
                    maxWinCount = winCount
                }

                return
            }

            else if (aChosendiceList.size < dice.size) {
                for (i in diceIndex until dice.size) {
                    used[i] = true
                    aChosendiceList.add(i)
                    diceRecursion(dice, aChosendiceList, i + 1, used)
                    aChosendiceList.removeLast()
                    used[i] = false
                }
            }

        }
        fun solution(dice: Array<IntArray>): IntArray {
            originDiceGraph = dice
            diceRecursion(dice, mutableListOf(), 0, BooleanArray(dice.size + 1) { false })
            return maxWinDiceList.map {
                it + 1
            }.toIntArray()
        }
    }
}

fun main() {
    val solution = `주사위 고르기`.Solution()
    solution.solution(arrayOf(intArrayOf(1, 2, 3, 4, 5, 6), intArrayOf(2, 2, 4, 4, 6, 6,)))
}