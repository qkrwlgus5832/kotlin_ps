class `광고 삽입` {
    class Solution {
        private fun convertToTimeStamp(time: String): Int {
            val hour = time.substring(0, 2).toInt()
            val minute = time.substring(3, 5).toInt()
            val second = time.substring(6, 8).toInt()

            return hour * 60 * 60 + minute * 60 + second
        }

        private fun toTimeFormat(time: Int): String {
            return String.format("%02d", time)
        }

        private fun convertToTime(timeStamp: Int): String {
            var tmpTimeStamp = timeStamp

            val hour = tmpTimeStamp / 3600
            tmpTimeStamp %= 3600
            val minute = tmpTimeStamp / 60
            tmpTimeStamp %= 60
            val second = tmpTimeStamp

            return toTimeFormat(hour) + ":" + toTimeFormat(minute) + ":" + toTimeFormat(second)
        }

        fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
            val playTimeStamp = convertToTimeStamp(play_time)
            val advTimeStamp = convertToTimeStamp(adv_time)

            val dp = Array(playTimeStamp + 2) { 0L }

            for (log in logs) {
                val start = convertToTimeStamp(log.substring(0, 8))
                val end = convertToTimeStamp(log.substring(9, 17))

                dp[start] += 1L
                dp[end + 1] = -1L
            }

            for (i in 1 until dp.size) {
                dp[i] = dp[i] + dp[i - 1]
            }

            for (i in 1 until dp.size) {
                dp[i] = dp[i] + dp[i - 1]
            }

            var answerTimestamp = 0
            var maxTimePoint = dp[advTimeStamp]

            for (i in advTimeStamp..playTimeStamp) {
                val currentIndex = i - advTimeStamp

                if (dp[i] - dp[currentIndex] > maxTimePoint) {
                    answerTimestamp = currentIndex + 1
                    maxTimePoint = dp[i] - dp[currentIndex]
                }
            }

            return convertToTime(answerTimestamp)
        }
    }
}

fun main() {
    val solution = `광고 삽입`.Solution()
    println(solution.solution("02:03:55", "00:14:15",
        arrayOf("01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30")))
}