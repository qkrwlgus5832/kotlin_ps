import java.lang.Math.pow
import kotlin.math.min

class `외벽 점검` {
    class Solution {
        var answer = -1
        val visitied = IntArray(pow(2.toDouble(), 16.toDouble()).toInt() + 1) { -1 }

        private fun leftRotate(n: Int, weak: Int, dist: Int, weakCheck: IntArray, isPlus: Boolean = true, weakArray: IntArray, weakBitMask: Int): Int {
            var left = weak - dist
            var right = weak
            var result = weakBitMask

            if (left < 0) {
                left = n + left

                if (dist >= n) {
                    left = 0
                    right = n - 1
                }
            }

            for (i in 0 until weakArray.size) {
                if (left <= right && weakArray[i] >= left && weakArray[i] <= right) {
                    if (isPlus) {
                        weakCheck[i]++
                        result = result or (1 shl i)
                    }
                    else {
                        weakCheck[i]--
                        result = result and (1 shl i).inv()
                    }
                }
                else if (left > right && (weakArray[i] >= left || weakArray[i] <= right)) {
                    if (isPlus) {
                        weakCheck[i]++
                        result = result or (1 shl i)
                    }
                    else {
                        weakCheck[i]--
                        result = result and (1 shl i).inv()
                    }
                }
            }
            return result
        }

        private fun rightRotate(n: Int, weak: Int, dist: Int, weakCheck: IntArray, isPlus: Boolean = true, weakArray: IntArray, weakBitMask: Int): Int {
            var left = weak
            var right = weak + dist
            var result = weakBitMask

            if (right >=n) {
                right = right % n

                if (dist >= n) {
                    left = 0
                    right = n - 1
                }
            }

            for (i in 0 until weakArray.size) {
                if (left <= right && weakArray[i] >= left && weakArray[i] <= right) {
                    if (isPlus) {
                        weakCheck[i]++
                        result = result or (1 shl i)
                    }
                    else {
                        weakCheck[i]--
                        result = result and (1 shl i).inv()
                    }
                }
                else if (left > right && (weakArray[i] >= left || weakArray[i] <= right)) {
                    if (isPlus) {
                        weakCheck[i]++
                        result = result or (1 shl i)
                    }
                    else {
                        weakCheck[i]--
                        result = result and (1 shl i).inv()
                    }
                }
            }

            return result
        }

        fun recursion(n: Int, weak: IntArray, dist: IntArray, distIndex: Int, weakIndex: Int, weakCheck: IntArray,
                      distCheck: BooleanArray, distCount: Int, weakBitMask: Int): Boolean {

            val allTrapsOn = (1 shl weak.size) - 1

            if (weakBitMask == allTrapsOn) {
                if (answer == -1) {
                    answer = distCount
                    return true
                }

                answer = min(answer, distCount)
                return true
            }

            if (visitied[weakBitMask] != -1 && distCount >= visitied[weakBitMask]) {
                return false
            }

            visitied[weakBitMask] = distCount

            for (i in 0 until dist.size) {
                if (distCheck[i] == false) {
                    distCheck[i] = true
                    for (j in 0 until weak.size) {
                        if (weakCheck[j] == 0) {
                            var result = leftRotate(n, weak[j], dist[i], weakCheck, true, weak, weakBitMask)

                            recursion(
                                n,
                                weak,
                                dist,
                                distIndex + 1,
                                weakIndex + 1,
                                weakCheck,
                                distCheck,
                                distCount + 1,
                                result,
                            )

                            leftRotate(n, weak[j], dist[i], weakCheck, false, weak, weakBitMask)

                            result = rightRotate(n, weak[j], dist[i], weakCheck, true, weak, weakBitMask)

                            recursion(
                                n,
                                weak,
                                dist,
                                distIndex + 1,
                                weakIndex + 1,
                                weakCheck,
                                distCheck,
                                distCount + 1,
                                result,
                            )
                            rightRotate(n, weak[j], dist[i], weakCheck, false, weak, weakBitMask)
                        }
                    }
                    distCheck[i] = false
                }
            }

            return answer != -1
        }

        fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
            val sorted = dist.sortedByDescending { it }
            recursion(n, weak, sorted.toIntArray(), 0, 0, IntArray(n + 1) {0}, BooleanArray(9) {false}, 0, 0)

            return answer
        }
    }
}

fun main() {
    val solution = `외벽 점검`.Solution()
    println(solution.solution(200, intArrayOf(0, 10, 50, 80, 120, 160), intArrayOf(1, 10, 5, 40, 30)))
}