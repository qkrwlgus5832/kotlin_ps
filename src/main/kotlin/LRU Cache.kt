import java.util.*

class `LRU Cache` {
    class LRUCache(private val capacity: Int) {
        private val queue = LinkedHashSet<Int>()
        private val cache = IntArray(10001) { -1 }
        private var currentCapacity = 0

        fun get(key: Int): Int {
            if (cache[key] != -1) {
                queue.remove(key)
                queue.add(key)
            }
            return cache[key]
        }

        fun put(key: Int, value: Int) {
            if (cache[key] == -1) {
                currentCapacity++
            }

            if (currentCapacity > capacity) {
                val first = queue.first()
                queue.remove(first)
                cache[first] = -1
                currentCapacity--
            }

            queue.remove(key)
            queue.add(key)
            cache[key] = value
        }

    }
}

fun main() {
    val lruCache = `LRU Cache`.LRUCache(2)
    lruCache.put(2, 1)
    lruCache.put(1, 1)
    lruCache.put(2, 3)
    lruCache.put(4, 1)
    println(lruCache.get(1))
    println(lruCache.get(2))
}