import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
import java.util.Collections.swap
import java.util.concurrent.*
import java.util.function.*
import java.util.regex.*
import java.util.stream.*
import kotlin.collections.*
import kotlin.comparisons.*
import kotlin.io.*
import kotlin.jvm.*
import kotlin.jvm.functions.*
import kotlin.jvm.internal.*
import kotlin.ranges.*
import kotlin.sequences.*
import kotlin.text.*

class `New Year Chaos` {
    private fun printFailedMessage() {
        println("Too chaotic")
    }

    fun minimumBribes(q: Array<Int>): Unit {
        // Write your code here

        val map = HashMap<Int, Int>()

        val qList = q.toMutableList()
        var count = 0

        for (i in q.size - 1 downTo 0) {
            if (qList[i] == i + 1) {
                continue
            }
            else if (i - 1 >= 0 && qList[i -1] == i + 1) {
                if ((map[qList[i - 1]] ?: 0) >= 2) {
                    printFailedMessage()
                    return
                }

                map[qList[i - 1]] = (map[qList[i - 1]] ?: 0) + 1
                swap(qList, i, i - 1)
                count++
            }
            else if (i - 2 >= 0 && qList[i - 2] == i+ 1) {
                if ((map[qList[i - 2]] ?: 0) >= 2 || (map[qList[i - 1]] ?: 0) >= 2) {
                    printFailedMessage()
                    return
                }
                map[qList[i - 2]] = (map[qList[i - 2]] ?: 0) + 2

                swap(qList, i - 2, i - 1)
                swap(qList, i - 1, i)

                count += 2
            }
            else {
                printFailedMessage()
                return
            }
        }

        print(count)
    }
}

fun main() {
    val solution = `New Year Chaos`()
    solution.minimumBribes(intArrayOf(1, 2, 5, 3, 7, 8, 6, 4).toTypedArray())
}