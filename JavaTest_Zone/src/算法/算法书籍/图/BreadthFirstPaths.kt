package 算法.算法书籍.图

import 算法.算法书籍.工具.IteratorNode
import java.util.*

/**
 * 【目的获取最短路径】
 *  分裂探测 ， 分裂的元素也算人，所有人顺序走一步，才能继续走 marked过的不走
 *
 *  就是for循环吧元素都循环完毕，然后挨个进入在循环，用队列实现 先进先出，
 *  先入列一个0 取出来 然后迭代所有的入列， 然后再取出去 迭代所有入列 这个时候他就最后面了 。
 *  相当于多个人一起走。同时进行一步后。在所有人都走一步
 *
 *  标记时机：如果比较是true就不能入列，false就入并标记true
 *
 *  fixme 这个多人一起走 就用队列记好了。
 *  fixme 也可以理解成 同等级分裂执行。 类似细胞分裂那种感觉，所有大分裂以此分裂成小的。小的在依次分裂成小小的这样递进
 */
class BreadthFirstPaths(g: Graph, val s: Int) {
    private val marked = arrayOfNulls<Boolean>(g.vertex) as Array<Boolean>

    val path = arrayOfNulls<Int>(g.vertex) as Array<Int>

    val nodeArray = arrayOfNulls<IteratorNode2>(g.vertex) as Array<IteratorNode2>

    init {
        for (i in 0 until g.vertex) {
            marked[i] = false
            nodeArray[i] = IteratorNode2(i)
        }
        bfs(g)
    }

    class IteratorNode2(value: Int) : IteratorNode<Int>(value) {
        var aa = false
        override fun formatString(node: IteratorNode<Int>): String {
            val string = if (!(node as IteratorNode2).aa) "" else "【X】"
            return "${node.value}$string"
        }
    }

    fun bfs(g: Graph) {
        val queue = LinkedList<Int>() as Queue<Int>
        marked[s] = true
        queue.offer(s)
        while (!queue.isEmpty()) {
            val v = queue.poll()
            for (i in g.adj(v)) {
                if (!marked[i]) {
                    nodeArray[v].arraylist.add(nodeArray[i].apply {
                        this.aa = false
                    })
                    path[i] = v
                    marked[i] = true
                    queue.offer(i)
                } else {
                    //怕形成回环 反正到这里停止了
                    nodeArray[v].arraylist.add(IteratorNode2(i).apply {
                        this.aa = true
                    })
                }
            }
        }
    }

    fun marked(v: Int) = marked[v]

    fun getPath(v: Int) = kotlin.run {
        val stack = Stack<Int>()
        var cursorKey = v
        //到起点就停了
        while (cursorKey != s) {
            stack.add(cursorKey)
            cursorKey = path[cursorKey]
        }
        stack.add(s)
        stack
    }

}