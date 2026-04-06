import java.util.*

class `마법사 상어와 블리자드` {
    class Solution {
        private lateinit var beadArray: Array<IntArray>
        private val dirArray = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        private lateinit var shark: Pair<Int, Int>

        private val bombCount = BombCount()

        data class BombCount(
            var one: Int = 0,
            var two: Int = 0,
            var three: Int = 0
        )

        fun init(beadArray: Array<IntArray>) {
            this.beadArray = beadArray
            shark = Pair(beadArray.size / 2, beadArray.size / 2)
        }

        fun getBombCount(): BombCount {
            return this.bombCount
        }

        private fun gather(): MutableList<Int> {
            val dirArray = arrayOf(
                intArrayOf(0, -1),
                intArrayOf(1, 0),
                intArrayOf(0, 1),
                intArrayOf(-1, 0)
            )

            var current = 1
            var currentDirIndex = 0
            var count = 0
            var flag = false

            val resultList = mutableListOf<Int>()

            var currentShark = shark

            while (true) {
                val nextX = currentShark.first + dirArray[currentDirIndex][0]
                val nextY = currentShark.second + dirArray[currentDirIndex][1]

                if (beadArray[nextX][nextY] != 0) {
                    resultList.add(beadArray[nextX][nextY])
                    beadArray[nextX][nextY] = 0
                }

                if (nextX == 0 && nextY == 0) {
                    break
                }

                if (++count == current) {
                    currentDirIndex = (currentDirIndex + 1) % dirArray.size
                    count = 0

                    if (flag) {
                        current++
                        flag = false

                    } else {
                        if (current != beadArray.size - 1) {
                            flag = true
                        }
                    }
                }
                currentShark = Pair(nextX, nextY)
            }

            return resultList
        }

        private fun bomb(list: MutableList<Int>): MutableList<Int> {
            val check = BooleanArray(list.size) { false }
            val resultList = mutableListOf<Int>()

            while (true) {
                var flag = false

                var previous = list[0]
                var count = 1
                val indexList = mutableListOf<Int>(0)

                for (i in 1 until list.size) {
                    if (check[i] == false && list[i] == previous) {
                        count++
                        indexList.add(i)
                    }
                    else if (check[i] == false && list[i] != previous){
                        if (count >= 4) {
                            for (i in 0 until indexList.size) {
                                check[indexList[i]] = true
                            }
                            flag = true
                        }

                        indexList.clear()
                        previous = list[i]
                        indexList.add(i)
                        count = 1
                    }
                }

                if (count >= 4) {
                    for (i in 0 until indexList.size) {
                        check[indexList[i]] = true
                    }
                    flag = true
                }

                if (!flag) {
                    break
                }
            }

            for (i in 0 until list.size) {
                if (check[i] == false) {
                    resultList.add(list[i])
                } else {
                    if (list[i] == 1) {
                        bombCount.one++
                    }
                    else if (list[i] == 2) {
                        bombCount.two++
                    }
                    else if (list[i] == 3) {
                        bombCount.three++
                    }
                }
            }

            return resultList
        }

        private fun grouping(list: MutableList<Int>): MutableList<Int> {
            val resultList = mutableListOf<Int>()

            var previous = list[0]
            var count = 1

            for (i in 1 until list.size) {
                if (list[i] != previous) {
                    resultList.add(count)
                    resultList.add(previous)
                    previous = list[i]
                    count = 1
                }
                else {
                    count++
                }
            }

            resultList.add(count)
            resultList.add(previous)
            return resultList
        }

        private fun move(list: MutableList<Int>) {
            val dirArray = arrayOf(
                intArrayOf(0, -1),
                intArrayOf(1, 0),
                intArrayOf(0, 1),
                intArrayOf(-1, 0)
            )

            var current = 1
            var currentDirIndex = 0
            var count = 0
            var totalCount = 0
            var flag = false

            var currentShark = shark

            while (true) {
                val nextX = currentShark.first + dirArray[currentDirIndex][0]
                val nextY = currentShark.second + dirArray[currentDirIndex][1]

                beadArray[nextX][nextY] = list[totalCount++]

                if (++count == current) {
                    currentDirIndex = (currentDirIndex + 1) % dirArray.size
                    count = 0

                    if (flag){
                        current++
                        flag = false
                    } else {
                        flag = true
                    }
                }

                currentShark = Pair(nextX, nextY)

                if (totalCount == list.size) {
                    break
                }
                if (totalCount == beadArray.size * beadArray[0].size - 1) {
                    break
                }
            }
        }

        fun blizard(direction: Int, distance: Int) {
            val dir = dirArray[direction - 1]

            for (i in 1..distance) {
                val nextX = shark.first + i * dir[0]
                val nextY = shark.second + i * dir[1]

                if (nextX < 0 || nextY < 0 || nextX >= this.beadArray.size || nextY >= this.beadArray[0].size) {
                    continue
                }

                this.beadArray[nextX][nextY] = 0
            }

            gather()
                .takeIf { it.isNotEmpty() }
                ?.let { bomb(it) }
                ?.takeIf { it.isNotEmpty() }
                ?.let { grouping(it) }
                ?.takeIf { it.isNotEmpty() }
                ?.let { move(it) }
        }
    }
}

fun main() = with(Scanner(System.`in`)) {
    val n = nextInt()
    val m = nextInt()

    val beadArray = Array(n) {
        IntArray(n) { 0 }
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val bead = nextInt()
            beadArray[i][j] = bead
        }
    }

    val solution = `마법사 상어와 블리자드`.Solution()

    solution.init(beadArray)

    for (i in 0 until m) {
        val direction = nextInt()
        val distance = nextInt()

        solution.blizard(direction, distance)
    }

    val bombCount = solution.getBombCount()

    println(bombCount.one * 1 + bombCount.two * 2 + bombCount.three * 3)
}