class Contacts {
    data class Trie(
        var count: Int = 0,
        var childList: Array<Trie?> = Array(26) { null }
    )

    fun find(root: Trie, contact: String, currentIndex: Int): Int {
        if (currentIndex == contact.length) {
            return root.count
        }

        val alphaIndex = contact[currentIndex] - 'a'

        if (root.childList[alphaIndex] == null) {
            return 0
        }

        return find(root.childList[alphaIndex]!!, contact, currentIndex + 1)
    }

    fun add(root: Trie, contact: String, currentIndex: Int) {
        if (currentIndex == contact.length) {
            return
        }

        val alphaIndex = contact[currentIndex] - 'a'

        if (root.childList[alphaIndex] == null) {
            root.childList[alphaIndex] = Trie()
        }

        root.childList[alphaIndex]!!.count++

        add(root.childList[alphaIndex]!!, contact, currentIndex + 1)
    }

    fun contacts(queries: Array<Array<String>>): Array<Int> {
        // Write your code here

        val root = Trie()
        val result = mutableListOf<Int>()

        for (query in queries) {
            if (query[0] == "add") {
                add(root, query[1], 0)
            }
            else if (query[0] == "find") {
                result.add(find(root, query[1], 0))
            }
        }

        return result.toTypedArray()
    }
}

fun main() {
    val contacts = Contacts()
    contacts.contacts(arrayOf(
        arrayOf("add", "hack"),
        arrayOf("add", "hackerrank"),
        arrayOf("find", "hac"),
        arrayOf("find", "hak"),
    ))
}