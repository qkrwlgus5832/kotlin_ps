import java.lang.Math.pow
import kotlin.math.max

class `수식 복원하기` {
    class Solution {
        data class ConvertedExpression(
            val firstValue: String,
            val secondValue: String,
            var result: String? = null,
            val operation: Char,
            val isXValue: Boolean
        )

        private fun isPossible(convertedExpression: ConvertedExpression, numericSystem: Int): Boolean {
            val firstValue = convertedExpression.firstValue.toInt(numericSystem)
            val secondValue = convertedExpression.secondValue.toInt(numericSystem)
            val result = convertedExpression.result!!.toInt(numericSystem)

            if (convertedExpression.operation == '-') {
                return (firstValue - secondValue == result)
            }
            return (firstValue + secondValue == result)
        }

        private fun getPossibleNumeralSystem(range: List<Int>, convertedExpression: ConvertedExpression): List<Int> {
            val result = mutableListOf<Int>()

            for (i in range[0]..range[1]) {
                if (isPossible(convertedExpression, i)) {
                    result.add(i)
                }
            }

            return result
        }

        private fun getResultOrNull(convertedExpression: ConvertedExpression, possibleNumericList: List<Int>): String? {
            var result: String? = null

            if (convertedExpression.isXValue) {
                for (possibleNumeric in possibleNumericList) {
                    val firstValue = convertedExpression.firstValue.toInt(possibleNumeric)
                    val secondValue = convertedExpression.secondValue.toInt(possibleNumeric)

                    val current = (if (convertedExpression.operation == '+') {
                        firstValue + secondValue
                    } else {
                        firstValue - secondValue
                    }).toString(possibleNumeric)

                    if (result == null) result = current else if (result != current) return null
                }
            }
            return result
        }

        private fun setRange(range: MutableList<Int>, expressions: List<String>) {
            val maxLimit = expressions.flatMap {
                it.filter(Char::isDigit).toList()
            }.maxOf { it - '0' } + 1

            range[0] = maxLimit
        }

        fun solution(expressions: Array<String>): Array<String> {
            var answer: MutableList<String> = mutableListOf<String>()

            val range = mutableListOf(2, 9)

            val convertedExpressions = mutableListOf<ConvertedExpression>()
            val convertedExpressionsWithX = mutableListOf<ConvertedExpression>()

            setRange(range, expressions.toList())

            for (expression in expressions) {
                var result: String? = null
                var isXValue = false

                val list = expression.split(' ')
                if (list[list.size - 1] == "X") isXValue = true else result = list[list.size - 1]

                val convertedExpression = ConvertedExpression(
                    list[0],
                    list[2],
                    result,
                    list[1][0],
                    isXValue
                )

                if (isXValue) {
                    convertedExpressionsWithX.add(convertedExpression)
                } else {
                    convertedExpressions.add(convertedExpression)
                }
            }

            var possibleNumericList = (range[0]..range[1]).toList()

            convertedExpressions.forEach {
                possibleNumericList = possibleNumericList.intersect(getPossibleNumeralSystem(range, it)).toList()
            }

            convertedExpressionsWithX.forEach { convertedExpression ->
                getResultOrNull(convertedExpression, possibleNumericList).let {
                    convertedExpression.result = it
                }
            }

            for (expression in convertedExpressionsWithX) {
                answer.add("${expression.firstValue} ${expression.operation} ${expression.secondValue} = ${expression.result ?: '?'}")
            }

            return answer.toTypedArray()
        }
    }
}

fun main() {
    val solution = `수식 복원하기`.Solution()
    solution.solution(
        arrayOf("2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X")
    )
}