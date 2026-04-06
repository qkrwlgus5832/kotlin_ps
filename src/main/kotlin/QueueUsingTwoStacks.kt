import java.util.*

class QueueUsingTwoStacks {
    class Queue {
        val st1 = Stack<Int>()
        val st2 = Stack<Int>()

        fun enqueue(x: Int) {
            st1.add(x)
        }

        fun dequeue() {
            if (st2.size == 0) {
                while(st1.size > 0) {
                    val front = st1.peek()
                    st2.add(front)
                    st1.pop()
                }
            }

            if (st2.size > 0) {
                st2.pop()
            }
        }


        fun print() {
            if (st2.size == 0) {
                while(st1.size > 0) {
                    val front = st1.peek()
                    st2.add(front)
                    st1.pop()
                }
            }

            if (st2.size > 0) {
                println(st2.peek())
            }
        }
    }
}

fun main() {
    val queue = QueueUsingTwoStacks.Queue()
    queue.enqueue(42)
    queue.dequeue()
    queue.enqueue(14)
    queue.print()
    queue.enqueue(28)
    queue.print()
    queue.enqueue(60)
    queue.enqueue(78)
    queue.dequeue()
    queue.dequeue()
}