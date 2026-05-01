class `Minimum Window Substring` {
    class Solution {
        fun minWindow(s: String, t: String): String {
            val tSmallCaseCount = Array(26) { 0 }
            val tLargeCaseCount = Array(26) { 0 }

            for (i in 0 until t.length) {
                if (t[i] in 'A'..'Z') {
                    tLargeCaseCount[t[i] - 'A']++
                } else if (t[i] in 'a'..'z') {
                    tSmallCaseCount[t[i] - 'a']++
                }
            }

            val slidingWindowSmallCaseCount = Array(26) { 0 }
            val slidingWindowLargeCaseCount = Array(26) { 0 }

            var minPartition = Pair(-1, -1)

            val deque = ArrayDeque<Pair<Int, Char>>()

            for (i in 0 until s.length) {
                var tCount: Array<Int>
                var standard: Char
                var slidingWindowCount: Array<Int>


                if (s[i] >= 'A' && s[i] <= 'Z') {
                    tCount = tLargeCaseCount
                    standard = 'A'
                    slidingWindowCount = slidingWindowLargeCaseCount
                } else {
                    tCount = tSmallCaseCount
                    standard = 'a'
                    slidingWindowCount = slidingWindowSmallCaseCount
                }


                if (tCount[s[i] - standard] > 0) {
                    deque.add(Pair(i, s[i]))
                    slidingWindowCount[s[i] - standard]++

                    while(true) {
                        val current = deque.first().second

                        if (current >= 'A' && current <= 'Z') {
                            tCount = tLargeCaseCount
                            standard = 'A'
                            slidingWindowCount = slidingWindowLargeCaseCount
                        } else {
                            tCount = tSmallCaseCount
                            standard = 'a'
                            slidingWindowCount = slidingWindowSmallCaseCount
                        }

                        if (tCount[current - standard] < slidingWindowCount[current - standard]) {
                            deque.removeFirst()
                            slidingWindowCount[current - standard]--
                        }
                        else {
                            break
                        }
                    }

                    var flag = true

                    for (i in 0 until 26) {
                        if (tSmallCaseCount[i] > slidingWindowSmallCaseCount[i] || tLargeCaseCount[i] > slidingWindowLargeCaseCount[i]) {
                            flag = false
                            break
                        }
                    }

                    if (flag) {
                        if (minPartition.first == -1 && minPartition.second == -1) {
                            minPartition = Pair(deque.first().first, deque.last().first)
                        }
                        else if (minPartition.second - minPartition.first > deque.last().first - deque.first().first) {
                            minPartition = Pair(deque.first().first, deque.last().first)
                        }
                    }
                }
            }

            if (minPartition.first == -1 && minPartition.second == -1) {
                return ""
            }
            return s.substring(minPartition.first, minPartition.second + 1)
        }
    }
}

fun main() {
    val solution = `Minimum Window Substring`.Solution()
    println(solution.minWindow("a", "aa"))
}