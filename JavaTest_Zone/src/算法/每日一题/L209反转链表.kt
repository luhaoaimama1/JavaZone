package 算法.每日一题


/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 */


class L209反转链表 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var temp: ListNode? = null
            var head: ListNode? = null
            var last: ListNode? = null
            for (i in 5 downTo 0) {
                if (temp != null) {
                    temp.next = ListNode(i).apply {
                        if(this.`val`==0) {
                            last=this
                        }
                    }
                    temp=temp.next
                } else {
                    temp = ListNode(i)
                    head=temp
                }
            }
            print(head)

            reverseList(head)

            println()
            print(last)
        }

        fun reverseList(now: ListNode?): ListNode? {
            val next = now?.next?:return now
            val returnNode = reverseList(next)
            now.next=null
            next.next = now
            return returnNode
        }

        fun print(head: ListNode?): ListNode? {
            var temp = head
            while (temp != null) {
                print("${temp.`val`} , \t")
                temp = temp.next
            }
            return temp
        }

        class ListNode(var `val`: Int) {
            var next: ListNode? = null
        }

    }


}
