class 문자열뒤집기 {
    class Solution {
        fun solution(my_string: String, s: Int, e: Int): String {
            var answer: String = ""

            val list = mutableListOf<Char>()

            for (i in s..e) {
                list.add(my_string[i])
            }

            val myStringBuffer = StringBuffer(my_string)

            for (i in 0 until list.size) {
                myStringBuffer.setCharAt(e -i, list[i])
            }
            return myStringBuffer.toString()
        }
    }
}

