import java.io.*
import java.util.*

class TextEditor {
    val undoStack = Stack<StringBuilder>()
    var current = StringBuilder()

    fun append(w: String) {
        undoStack.add(current)
        current.append(w)
    }

    fun delete(k: Int) {
        undoStack.add(current)
        current.delete(current.length - k, current.length)
    }

    fun print(k: Int) {
        println(current.get(k))
    }

    fun undo() {
        if (undoStack.size > 0) {
            val top = undoStack.peek()
            undoStack.pop()
            current = top
        }
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val textEditor = TextEditor()

    for (i in 0 until n) {
        val c1 = readLine()!!
        val splited = c1.split(' ')

        if (splited[0] == 1.toString()) {
            textEditor.append(splited[1])
        }
        else if (splited[0] == 2.toString()) {
            textEditor.delete(splited[1].toInt() - 1)
        }
        else if (splited[0] == 3.toString()) {
            textEditor.print(splited[1].toInt() - 1)
        }
        else if (splited[0] == 4.toString()) {
            textEditor.undo()
        }
    }

}