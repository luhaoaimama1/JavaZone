package 算法.算法书籍.字符串.查找树

//单词查找树
class StringST<V : Any> {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val st = StringST<Int>()
            st.put("", -100)
            st.put("sea", 1)
            st.put("shell", 2)
            st.put("zone", 3)
            st.put("seaHa", 5)

            st.delete("seaHa")
            println("sea 的a下面还有节点么？：${st.get(st.rootNode, "sea".toCharArray(), 0)?.next?.isEmpty()}")

            val arrayOf = arrayOf("", "sea", "seaHa", "shell", "zone", "la")
            for (s in arrayOf) {
                println("$s ->${st.get(s)}")
            }

            println("s开头的有")
            val list = st.keysWithPrefix("s")
            list.forEachIndexed { index, s ->
                print("$s ${if (index != list.lastIndex) "\t," else ""}")
                if (index == list.lastIndex) println()
            }
        }
    }

    val R = 256

    var rootNode = Node() //根节点不存值 ,这里修改一下 存""空字符串的值好了

    inner class Node {
        var value: V? = null
        var next = arrayOfNulls<Node>(R)

        fun isEmpty(): Boolean {
            for (node in next) {
                if (node != null) return false
            }
            return true
        }
    }

    //构建单词查找树
    fun put(key: String, value: V) {
        val toCharArray = key.toCharArray()
        val node = put(rootNode, toCharArray, 0)
        node.value = value
    }

    //因为 string会根据长度 从0-结尾的查找 这个就是个递归
    fun put(node: Node, charArray: CharArray, i: Int): Node {
        if (i > charArray.lastIndex) return node
        //tuichu条件

        val keyChar = charArray[i].toInt()
        if (node.next[keyChar] == null) {
            node.next[keyChar] = Node()
        }
        //找到下一个节点了。如果i!=lastIndex则继续找。

        return put(node.next[keyChar]!!, charArray, i + 1)
    }

    fun get(key: String): V? {
        if (key.isEmpty()) return rootNode.value
        return get(rootNode, key.toCharArray(), 0)?.value
    }

    fun get(node: Node, charArray: CharArray, i: Int): Node? { //终结：没找到 或者找到了
        val keyChar = charArray[i].toInt()
        val nextNode = node.next[keyChar]
        if (nextNode == null) { //没找到
            return null
        } else {
            if (i == charArray.lastIndex) // 找到了
                return nextNode
            else {
                return get(nextNode, charArray, i + 1) //这里可以知道  index不可能超出charArray的
            }
        }
//        可以简化成：
        //没找到
//        if (nextNode == null) return null
        // 找到了
//        if (i == charArray.lastIndex) return nextNode
//        return get(nextNode, charArray, i + 1) //这里可以知道  index不可能超出charArray的
    }

    //删除的话
    // 判断他子有没有child.如果有的话 仅仅删除值，  没有的话， 递找到，归的时候检查当前节点 的child是否是null和是否有值,都没有返回null。父亲重置该节点
    fun delete(key: String) {
        if (key.isEmpty()) rootNode.value == null
        //todo 重点 ：这里保证了0不会超出lastIndex
        delete(rootNode, key.toCharArray(), 0)
    }

    fun delete(node: Node, charArray: CharArray, i: Int): Node? {
        val keyChar = charArray[i].toInt()
        val nextNode = node.next[keyChar]
        if (nextNode == null) {  //未命中
            return null
        } else {
            if (i == charArray.lastIndex) {//找到了
                if (nextNode.isEmpty()) return null
                else return nextNode.apply {
                    nextNode.value = null
                }
            } else { //todo 重点 ：这里保证了 i+1不会超出lastIndex
                node.next[keyChar] = delete(nextNode, charArray, i + 1)
            }
        }
        //如果归的过程 子节点都没有了。并且当没存值 那么当前节点也不应该存在了
        if (node.isEmpty() && node.value == null) return null
        return node
    }

    fun keysWithPrefix(suffix: String): ArrayList<String> {
        val list = ArrayList<String>()
        val node = get(rootNode, suffix.toCharArray(), 0)
        collect(node, suffix, list)
        return list
    }

    fun collect(node: Node?, preSuffix: String, list: ArrayList<String>) {
        if (node == null) return
        if (node.value != null) list.add(preSuffix)
        for (i in 0..node.next.lastIndex) {
            collect(node.next[i], preSuffix + i.toChar(), list)
        }
    }

}