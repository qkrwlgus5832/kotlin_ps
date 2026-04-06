import java.util.Collections.swap

class `Minimum Swaps` {
    // Complete the minimumSwaps function below.

    fun minimumSwaps(arr: Array<Int>): Int {
        val indexMapper = IntArray(arr.size + 1) { 0 }
        val list = arr.toMutableList()

        for (i in 0 until arr.size) {
            indexMapper[arr[i]] = i
        }

        var count = 0

        for (i in 0 until list.size) {
            if (list[i] != i+1) {
                val index = indexMapper[i + 1]

                indexMapper[i + 1] = i
                indexMapper[list[i]] = index

                swap(list, i, index)
                count++
            }
        }

        return count
    }
}

fun main() {
    val solution = `Minimum Swaps`()
    solution.minimumSwaps(arrayOf(
        7, 1, 3, 2, 4, 5, 6
    ))
}