import java.io.*
import java.math.*
import java.security.*
import java.text.*
import java.util.*
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

/*
 * Complete the 'pairs' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER k
 *  2. INTEGER_ARRAY arr
 */

fun pairs(k: Int, arr: Array<Int>): Int {
    // Write your code here
    val map = HashMap<Int, Int>()
    var result = 0

    for (i in 0 until arr.size) {
        map[arr[i]] = (map[arr[i]] ?: 0) + 1
    }

    for (i in 0 until arr.size) {
        result += map[arr[i] + k] ?: 0
        result += map[arr[i] - k] ?: 0
        map[arr[i]] = (map[arr[i]] ?: 0) - 1
    }

    return result
}

fun main(args: Array<String>) {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n = first_multiple_input[0].toInt()

    val k = first_multiple_input[1].toInt()

    val arr = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()

    val result = pairs(k, arr)

    println(result)
}
