import java.util.*

class LFUCache(private val capacity: Int) {
    private val cacheMap = HashMap<Int, Int>()
    private val cacheLinkedList = Array( 20001) {
        ArrayDeque<Int>()
    }

    private var minIndex = 1

    private val currentCacheSet = HashSet<Int>()
    private val frequencyCountMap = HashMap<Int, Int>()

    private fun updateCacheLinkedList(key: Int, isDelete: Boolean = false) {
        val frequency = frequencyCountMap.get(key) ?: 0

        if (isDelete) {
            val frequency = frequencyCountMap.get(key) ?: 0
            cacheLinkedList[frequency].remove(key)
            if (cacheLinkedList[frequency].isEmpty()) {
                if (minIndex == frequency) {
                    minIndex++
                }
            }
        }

        cacheLinkedList[frequency + 1].addFirst(key)
    }

    fun get(key: Int): Int {
        if (currentCacheSet.contains(key)) {
            updateCacheLinkedList(key, true)
            frequencyCountMap[key] = (frequencyCountMap[key] ?: 0) + 1
            return cacheMap.get(key)!!
        }

        return -1
    }

    private fun insertCache(key: Int, value: Int) {
        currentCacheSet.add(key)
        updateCacheLinkedList(key)
        frequencyCountMap[key] = 1
        cacheMap[key] = value
        minIndex = 1
    }

    fun put(key: Int, value: Int) {
        if (currentCacheSet.contains(key)) {
            cacheMap[key] = value
            updateCacheLinkedList(key, true)
            frequencyCountMap[key] = (frequencyCountMap[key] ?: 0) + 1
            return
        }
        if (currentCacheSet.size < capacity) {
            insertCache(key, value)
            return
        }

        val last = cacheLinkedList[minIndex].removeLast()
        currentCacheSet.remove(last)
        frequencyCountMap[last] = 0
        insertCache(key, value)
    }
}

fun main() {
    val lfu = LFUCache(2)
    lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
    lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
    println(lfu.get(1))      // return 1
    // cache=[1,2], cnt(2)=1, cnt(1)=2
    lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
    // cache=[3,1], cnt(3)=1, cnt(1)=2
    println(lfu.get(2))      // return -1 (not found)
    println(lfu.get(3))      // return 3
    // cache=[3,1], cnt(3)=2, cnt(1)=2
    lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
    // cache=[4,3], cnt(4)=1, cnt(3)=2
    println(lfu.get(1))      // return -1 (not found)
    println(lfu.get(3))      // return 3
    // cache=[3,4], cnt(4)=1, cnt(3)=3
    println(lfu.get(4))      // return 4
    // cache=[4,3], cnt(4)=2, cnt(3)=3
}