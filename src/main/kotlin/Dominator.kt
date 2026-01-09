class codility2 {

    fun solution(A: IntArray): Int {
        val map = HashMap<Int, Pair<Int, Int>>()

        for (i in 0 until A.size) {
            if (map.containsKey(A[i])) {
                map[A[i]] = Pair(map[A[i]]!!.first + 1, map[A[i]]!!.second)
            }
            else {
                map[A[i]] = Pair(1, i)
            }
        }

        map.keys.forEach {
            if (map[it]!!.first > A.size / 2) {
                return map[it]!!.second
            }
        }

        return -1
        // Implement your solution here
    }
}

fun main() {
    val codility2 = codility2()
    println(codility2.solution(
        intArrayOf(3, 4, 3, 2, 3, -1, 3, 3)
    ))
}