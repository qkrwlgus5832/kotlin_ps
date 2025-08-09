import java.lang.Long.max
import kotlin.math.abs

class 수식최대화 {
    class Solution {
        private val operatorArray: CharArray = charArrayOf('+', '-', '*')

        private var answer: Long = 0

        private fun checkExpressionValue(operatorsOrder: CharArray, originExpression: String): Long {
            var expression: StringBuilder = StringBuilder(originExpression)

            var numberList: MutableList<Long> = mutableListOf()
            var number: StringBuilder = StringBuilder()
            var operatorList: MutableList<Char> = mutableListOf()

            expression.forEachIndexed { index, it ->
                if (it >= '0' && it <= '9') {
                    number.append(it)
                } else if (it == '-' || it == '*' || it == '+') {
                    numberList += number.toString().toLong()
                    number.clear()
                    operatorList += it
                }
            }

            numberList += number.toString().toLong()

            operatorsOrder.forEach { operatorOrder ->
                val operatorIterator = operatorList.iterator()

                var index = 0

                while (operatorIterator.hasNext()) {
                    val operator = operatorIterator.next()

                    if (operator == operatorOrder) {
                        val number: Long = if (operator == '+') {
                            numberList[index] + numberList[index + 1]
                        } else if (operator == '-') {
                            numberList[index] - numberList[index + 1]
                        } else {
                            numberList[index] * numberList[index + 1]
                        }
                        numberList[index] = number

                        numberList.removeAt(index + 1)
                        operatorIterator.remove()
                        index--
                    }
                    index++
                }
            }

            return numberList[0]
        }

        private fun recursion(operators: CharArray, count: Int, expression: String) {
            if (count == 3) {
                answer = max(answer, abs(checkExpressionValue(operators, expression)))
                return
            }

            operatorArray.forEachIndexed { index, c ->
                if (operators.contains(operatorArray[index])) {
                    return@forEachIndexed
                }

                recursion(operators.plus(operatorArray[index]), count + 1, expression)
            }
        }

        fun solution(expression: String): Long {
            recursion(charArrayOf(), 0, expression)
            return answer
        }
    }
}

fun main() {
    val solution = 수식최대화.Solution()
    solution.solution("50*6-3*2")
}