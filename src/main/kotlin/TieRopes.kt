class TieRopes {
    fun solution(K: Int, A: IntArray): Int {
        // Implement your solution here

        var sum = 0
        var answer = 0

        for (i in 0 until A.size) {
            sum += A[i]
            if (sum >= K) {
                answer++
                sum = 0
            }
        }

        return answer
    }
}

fun main() {
    val timeRopes = TieRopes()
}