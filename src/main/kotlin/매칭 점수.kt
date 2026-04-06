class `매칭 점수` {
    class Solution {
        data class Document(
            val index: Int,
            var urlContent: String = "",
            var hrefUrlList: List<String>? = null,
            var defaultScore: Int = 0,
            var externalLinkScore: Double = 0.toDouble(),
            var externalLinkCount: Int = 0
        )

        private fun Char.isAlpha(): Boolean {
            return (this in 'a'..'z') || (this in 'A'..'Z')
        }

        private fun getHrefList(lines: List<String>): List<String> {
            val result = mutableListOf<String>()

            for (line in lines) {
                var current = 0

                while (true) {
                    val findedStart = line.indexOf("<a href=", current)
                    if (findedStart != -1) {
                        val findedEnd = line.indexOf(">", findedStart)
                        result.add(
                            line.substring(findedStart, findedEnd).drop(9).dropLast(1)
                        )
                        current = findedEnd
                    } else {
                        break
                    }
                }
            }
            return result
        }

        private fun isSame(line: String, current: Int, word: String): Boolean {
            for (i in 0 until word.length) {
                if (current + i >= line.length) {
                    return false
                }
                if (line[current +i].lowercaseChar() != word[i]) {
                    return false
                }
            }

            if (current == 0 || !line[current - 1].isAlpha()) {
                if (current + word.length >= line.length || !line[current + word.length].isAlpha()) {
                    return true
                }
            }
            return false
        }

        private fun getDefaultScore(line: String, word: String): Int {
            var defaultScore = 0

            var index = 0

            while(index < line.length) {
                if (isSame(line, index, word) == true) {
                    defaultScore++
                    index += word.length
                }
                else {
                    index++
                }
            }
            return defaultScore
        }

        private fun parsingHTML(lines: List<String>, index: Int, word: String): Document {
            val html = Document(index)
            var defaultScore = 0

            for (line in lines) {
                if (line.trim().startsWith("<meta property=")) {
                    val content = line.trim().split(" ")[2].drop(9).dropLast(3)
                    html.urlContent = content
                }
            }

            html.hrefUrlList = getHrefList(lines.subList(6, lines.size))
            html.externalLinkCount = html.hrefUrlList?.size ?: 0

            for (i in 6 until lines.size) {
                if (lines[i].trim().startsWith("</body>")) {
                    break
                }

                defaultScore += getDefaultScore(lines[i], word)
            }

            html.defaultScore = defaultScore

            return html
        }

        fun solution(word: String, pages: Array<String>): Int {
            val urlMap = HashMap<String, Int>()

            val documentList: MutableList<Document> = mutableListOf()

            pages.forEachIndexed { index, page ->
                val document = parsingHTML(page.split("\n"), index, word.lowercase())
                documentList.add(document)
            }

            for (i in 0 until documentList.size) {
                urlMap[documentList[i].urlContent] = documentList[i].index

            }

            for (i in 0 until documentList.size) {
                for (url in documentList[i].hrefUrlList!!) {
                    val childIndex = urlMap[url]
                    val linkScore = documentList[i].defaultScore / documentList[i].externalLinkCount.toDouble()
                    childIndex?.let {
                        documentList[it].externalLinkScore += linkScore
                    }
                }
            }

            var answer = 0
            var maxSum = documentList[0].defaultScore + documentList[0].externalLinkScore

            for (i in 1 until documentList.size) {
                if (documentList[i].defaultScore + documentList[i].externalLinkScore > maxSum) {
                    answer = i
                    maxSum = documentList[i].defaultScore + documentList[i].externalLinkScore
                }
            }

            return answer
        }
    }
}

fun main() {
    val solution = `매칭 점수`.Solution()
    solution.solution(
        "blind",
        arrayOf("<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>")
    )
}