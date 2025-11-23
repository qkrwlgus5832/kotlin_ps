import java.lang.Math.pow

class `표현 가능한 이진트리` {
    class Solution {
        private fun convertToBinary(number: Long): StringBuilder {
            val result = StringBuilder()
            var standard = 1L
            var currentNumber = number.toLong()

            while(true) {
                if (standard > number) {
                    standard /= 2
                    break
                }
                standard *= 2
            }

            while(true) {
                if (standard == 0L) {
                    break
                }
                if (currentNumber >= standard) {
                    result.append("1")
                }
                else {
                    result.append("0")
                }

                currentNumber %= standard
                standard /= 2
            }

            return result
        }

        private fun binaryAdapter(binaryString: StringBuilder): StringBuilder {
            var standard = 1
            val binarySize = binaryString.length
            var treeDepth = 0

            while(true) {
                if ((standard - 1) >= binarySize) {
                    break
                }

                standard *= 2
                treeDepth++
            }

            val zeroRepeat = "0".repeat((standard - 1) - binarySize)

            return binaryString.insert(0, zeroRepeat)
        }

        private fun isExpressable(binary: StringBuilder, start: Int, end: Int, rootValue: Char): Boolean {
            if (start == end) {
                if (rootValue == '0') {
                    if (binary[start] == '1') {
                        return false
                    }
                }
                return true
            }

            val mid = (start + end ) / 2

            if (rootValue == '0' && binary[mid] == '1') {
                return false
            }

            return if (binary[mid] == '0') {
                isExpressable(binary, start, mid - 1, '0') && isExpressable(binary, mid + 1, end, '0')
            } else {
                isExpressable(binary, start, mid - 1, '1') && isExpressable(binary, mid + 1, end, '1')
            }
        }

        fun solution(numbers: LongArray): IntArray {
            var result = mutableListOf<Int>()

            for (i in 0 until numbers.size) {
                if (numbers[i] == 0L) {
                    result.add(0)
                    continue
                }

                var binary = convertToBinary(numbers[i]).let {
                    binaryAdapter(it)
                }

                result.add(if (isExpressable(binary, 0, binary.length - 1, '1')) 1 else 0)
            }
            return result.toIntArray()
        }
    }
}

fun main() {
    // 10888
    val solution = `표현 가능한 이진트리`.Solution()
    solution.solution(longArrayOf(96))
}