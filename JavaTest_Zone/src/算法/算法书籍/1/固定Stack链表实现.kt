package 算法.算法书籍.`1`

fun main(args: Array<String>) {
    val sf = StackFixedLinked<Int>()
    for (i in 0..60) {
        sf.push(i)
        print("${sf.size()} \t ")
    }
    println()

    while(!sf.isEmpty()){
        print("${sf.pop()} \t ")
    }

}

//链表的头一直在结尾即可实现 后进先出， 。
// 先进先出队列的话 就需要记录两个 节点一个头为开始节点 用来取 一个尾部节点用来存

//所以节点的方向是根据  取出的顺序。放置的
class StackFixedLinked<T> {
    class Node<T> {
        var item: T
        var next: Node<T>? = null

        constructor(item: T) {
            this.item = item
        }
    }

    var firstNode: Node<T>? = null //栈顶  逆向链表
    var cursorSize = 0
    fun isEmpty() = cursorSize == 0
    fun size() = cursorSize

    fun push(value: T) {
        val nodeNew = Node<T>(value)
        nodeNew.next = firstNode //新添加的 下一个是老的
        firstNode = nodeNew //栈顶
        cursorSize++
    }

    fun pop(): T {
        val value = firstNode!!.item
        firstNode = firstNode!!.next
        cursorSize--
        return value
    }

}
