class `Merge k Sorted Lists` {
    /**
     * Example:
     * var li = ListNode(5)
     * var v = li.`val`
     * Definition for singly-linked list.
     * class ListNode(var `val`: Int) {
     *     var next: ListNode? = null
     * }
     */

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    class Solution {
        fun mergeKLists(lists: Array<ListNode?>): ListNode? {
            var result = ListNode(0)
            var tmp = result

            while (true) {
                var minIndex = -1
                var minValue = Int.MAX_VALUE

                for (i in 0 until lists.size) {
                    if (lists[i] == null) {
                        continue
                    }

                    if (lists[i]!!.`val` < minValue) {
                        minValue = lists[i]!!.`val`
                        minIndex = i
                    }
                }

                if (minIndex == -1) {
                    break
                }
                tmp.next = ListNode(lists[minIndex]!!.`val`)
                tmp = tmp.next!!
                lists[minIndex] = lists[minIndex]!!.next
            }

            return result.next
        }
    }
}