class `순위 검색` {
    class Solution {
        private fun getLanguageIndex(language: String): String {
            if (language == "cpp") {
                return 0.toString()
            }

            else if (language == "java") {
                return 1.toString()
            }

            else if (language == "python") {
                return 2.toString()
            }

            return language
        }

        private fun lowerBound(arr: MutableList<Int>, target: Int): Int {
            var left = 0
            var right = arr.size

            while (left < right) {
                val mid = (left + right) / 2
                if (arr[mid] < target) {
                    left = mid + 1
                }
                else {
                    right = mid
                }
            }

            return left
        }

        private fun getProfessionalIndex(professional: String): String {
            if (professional == "backend") {
                return 0.toString()
            }

            else if (professional == "frontend") {
                return 1.toString()
            }
            else {
                return professional
            }
        }

        private fun getCareerIndex(career: String): String {
            if (career == "junior") {
                return 0.toString()
            }

            else if (career == "senior") {
                return 1.toString()
            }
            else {
                return career
            }
        }

        private fun getSoulFood(food: String): String {
            if (food == "chicken") {
                return 0.toString()
            }

            else if (food == "pizza") {
                return 1.toString()
            }
            else {
                return food
            }
        }

        private fun setInfoList(infoKey: String, score: Int) {
            if (map.containsKey(infoKey)) {
                map[infoKey]!!.add(score)
            } else {
                map.put(infoKey, mutableListOf(score))
            }
        }

        private fun setByInfoKeyBySize(infoKey: String, size: Int, index: Int, score: Int, check: MutableSet<Int>) {
            if (check.size == size) {
                val newInfoKey = StringBuilder(infoKey)

                for (current in check) {
                    newInfoKey[current] = '-'
                }

                setInfoList(newInfoKey.toString(), score)
                return
            }
            for (i in index until infoKey.length) {
                if (!check.contains(i)) {
                    check.add(i)
                    setByInfoKeyBySize(infoKey, size, i + 1, score, check)
                    check.remove(i)
                }
            }
        }

        private fun setByInfoKey(infoKey: String, score: Int) {
            setInfoList(infoKey, score)

            for (i in 1..infoKey.length) {
                setByInfoKeyBySize(infoKey, i, 0, score, mutableSetOf())
            }
        }

        private val map = HashMap<String, MutableList<Int>>()

        fun solution(info: Array<String>, query: Array<String>): IntArray {
            val result = IntArray(query.size)

            for (i in 0 until info.size) {
                val infoList = info[i].split(' ')
                var indexString = ""

                var score = 0
                var index = ""

                for (infoKey in 0 until infoList.size) {
                    if (infoKey == 0) {
                        index = getLanguageIndex(infoList[infoKey])
                    }
                    else if (infoKey == 1) {
                        index = getProfessionalIndex(infoList[infoKey])
                    }
                    else if (infoKey == 2) {
                        index = getCareerIndex(infoList[infoKey])
                    }
                    else if (infoKey == 3) {
                        index = getSoulFood(infoList[infoKey])
                    }
                    else if (infoKey == 4) {
                        score = infoList[infoKey].toInt()
                        break
                    }
                    indexString += index
                }
                setByInfoKey(indexString, score)
            }

            for (list in map) {
                list.value.sort()
            }

            for (i in 0 until query.size) {
                val queryList = query[i].split(' ')
                var indexString = ""

                var index = ""
                var score = 0

                for (queryKey in 0 until queryList.size) {
                    if (queryKey == 0) {
                        index = getLanguageIndex(queryList[queryKey])
                        indexString += index
                    }
                    else if (queryKey == 1 * 2) {
                        index = getProfessionalIndex(queryList[queryKey])
                        indexString += index
                    }
                    else if (queryKey == 2 * 2) {
                        index = getCareerIndex(queryList[queryKey])
                        indexString += index
                    }
                    else if (queryKey == 3 * 2) {
                        index = getSoulFood(queryList[queryKey])
                        indexString += index
                    }

                    else if (queryKey == 7) {
                        score = queryList[queryKey].toInt()
                    }
                }


                val count = if (map[indexString] != null) {
                    map[indexString]!!.size - lowerBound(map[indexString]!!, score)
                } else {
                    0
                }

                result[i] = count
            }

            return result
        }
    }
}

fun main() {
    val solution = `순위 검색`.Solution()
    solution.solution(
        arrayOf(
            "java backend junior pizza 150","python frontend senior chicken 210","python frontend senior chicken 150","cpp backend senior pizza 260","java backend junior chicken 80","python backend senior chicken 50"
        ),
        arrayOf(
            "java and backend and junior and pizza 100","python and frontend and senior and chicken 200","cpp and - and senior and pizza 250","- and backend and senior and - 150","- and - and - and chicken 100","- and - and - and - 150"
        )
    )
}