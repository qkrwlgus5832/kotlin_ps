import kotlin.math.max

class `택배 배달 수거하기` {
    class Solution {
        fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
            var answer: Long = 0L

            var beforeDeliverIndex = deliveries.size - 1
            var beforePickupIndex = pickups.size - 1

            while (true) {
                var maxDeliverIndex = -1
                var maxPickupIndex = -1
                var currentCap = cap

                for (i in beforeDeliverIndex downTo 0) {
                    if (deliveries[i] != 0) {
                        if (deliveries[i] <= currentCap) {
                            currentCap -= deliveries[i]
                            deliveries[i] = 0
                        } else {
                            deliveries[i] = deliveries[i] - currentCap
                            beforeDeliverIndex = i
                            currentCap = 0
                        }

                        if (maxDeliverIndex == -1) {
                            maxDeliverIndex = i
                        }

                        if (currentCap == 0) {
                            break
                        }
                    }
                }

                currentCap = cap

                for (i in beforePickupIndex downTo 0) {
                    if (pickups[i] != 0) {
                        if (pickups[i] <= currentCap) {
                            currentCap -= pickups[i]
                            pickups[i] = 0
                        } else {
                            pickups[i] = pickups[i] - currentCap
                            beforePickupIndex = i
                            currentCap = 0
                        }

                        if (maxPickupIndex == -1) {
                            maxPickupIndex = i
                        }

                        if (currentCap == 0) {
                            break
                        }
                    }
                }

                if (maxDeliverIndex == -1 && maxPickupIndex == -1) {
                    break
                }
                else {
                    answer += max(maxDeliverIndex + 1, maxPickupIndex + 1) * 2
                }
            }

            return answer
        }
    }
}