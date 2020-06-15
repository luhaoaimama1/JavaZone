package 算法.算法书籍.压缩

import edu.princeton.cs.algs4.MinPQ
import java.io.*


/**
 * tips: 这里面注意的点
 * 因为是压缩是读取两次。所以你的关闭流后重新打开流不然 你读取的值 直接是结尾 更好的办法是用数组装下 那么第二次就不用读取了 但是这样会占用大量内存 如果一个很大的东西这就不行了 。所以还是这样写把
 *
 * 这里面写的全是位  如果你用byte就会导致缓存失败
 */
class Huffman {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Huffman().compress()
            Huffman().unCompress()
        }
    }

    val source = File(".", "${s}a.jpg")
    val compress = File(".", "${s}a-huffman-compress.jpg")
    val uncompress = File(".", "${s}a-huffman-uncompress.jpg")

    private val R = 256

    fun unCompress() {
        val inbs = BufferedInputStream(FileInputStream(compress))
        val outbs = BufferedOutputStream(FileOutputStream(uncompress))
        //读取霍夫曼单词查找树
        val root = readTree(inbs)
        println(root)
        //读取byte频率
        val freq = Integer.parseInt(inbs.readBit(16), 2)
        var node = root
        //循环频率 根据霍夫曼编码 在 霍夫曼单词树中查找值。查到了 回到root
        for (i in 0 until freq) {
            var value = 0
            while (inbs.readBit().apply {
                        value = this
                    } != -1) {
                node = if (value == 0) node.left!!
                else node.right!!
                if (node.byteValue != -1) {
                    outbs.writeBit(node.byteValue, 8)
                    node = root
                    break
                }
            }
        }
        outbs.writeBitFlush()
        outbs.close()
    }


    fun compress() {
        val freqs = IntArray(R)

        //读取一遍  记录每个byte 的频率
        var inbs = BufferedInputStream(FileInputStream(source))
        var byte = 0
        while (inbs.read().apply {
                    byte = this
                } != -1) {
            freqs[byte] = freqs[byte] + 1
        }
        inbs.close()
        inbs = BufferedInputStream(FileInputStream(source))

        //构建霍夫曼 单词查找树
        val root = buildTree(freqs)

        //构建个字典（数组也行 key就是下标）  key byte,value是霍夫曼编码 (value我认为是"字符串比较好" 因为要写入不同的bit如果用byte活int记录的话还得记录几个bit)。 就是从上到下遍历。直到叶节点结束
        val st = Array<String>(R) { "" }
        buildMap(root, "", st)

        val outbs = BufferedOutputStream(FileOutputStream(compress))

        // 写入霍夫曼单词查找树
        writeTree(root, outbs)
        //写入频率
        outbs.writeBit(root.freq, 16)

        //读取 byte 根据字典查到 霍夫曼编码 写入
        while (inbs.read().apply {
                    byte = this
                } != -1) {
            val s = st[byte]
            for (i in 0..s.lastIndex) {
                outbs.writeBit(s[i] == '1')
            }
        }
        outbs.writeBitFlush() //防止最后一个bit缓存丢失
        outbs.close()
    }

    //从上到下 从左到右读读  读到叶子节点的话 把映射码写入。不是叶子节点的话。继续读
    fun readTree(ins: BufferedInputStream): Node {
        val value = ins.readBit()
        if (value == 1) return Node(0, Integer.parseInt(ins.readBit(8), 2), null, null)
        return Node(0, -1, readTree(ins), readTree(ins))
    }


    //从上到下 从左到右 写入。 如果是叶子节点写入1
    fun writeTree(node: Node, bos: BufferedOutputStream) {
        if (node.isLeaf()) {
            bos.writeBit(true)
            bos.writeBit(node.byteValue, 8)
        } else {
            bos.writeBit(false)
            node.left?.let {
                writeTree(it, bos)
            }
            node.right?.let {
                writeTree(it, bos)
            }
        }
    }

    //构建个数组  key byte,value是霍夫曼编码 (value我认为是"字符串比较好" 因为要写入不同的bit如果用byte活int记录的话还得记录几个bit)。 就是从上到下遍历。直到叶节点结束
    fun buildMap(node: Node, s: String, st: Array<String>) {
        if (node.isLeaf()) {
            st[node.byteValue] = s
            return
        }
        node.left?.let {
            buildMap(it, s + "0", st)
        }
        node.right?.let {
            buildMap(it, s + "1", st)
        }
    }

    /**
     * @return 返回 霍夫曼 单词查找树 的根节点
     */
    fun buildTree(freq: IntArray): Node {

        val pq = MinPQ<Node>(object : Comparator<Node> {
            override fun compare(o1: Node?, o2: Node?): Int {
                val v1 = o1?.byteValue ?: 0
                val v2 = o2?.byteValue ?: 0
                return v1 - v2
            }

        })
        //对所有字符  建立节点 并加入到 最小队列中
        for (i in 0 until R) {
            if (freq[i] != 0) {
                pq.insert(Node(freq[i], i, null, null))
            }
        }

        //取出两个最小的 建立一个空的父亲（连接到这两最小的 并且频率为子的和）。把这个节点放入队列中 。循环到完毕即可
        while (pq.size() > 1) {
            val min1 = pq.delMin()
            val min2 = pq.delMin()
            pq.insert(Node(min1.freq + min2.freq, -1, min1, min2))
        }
        return pq.delMin()
    }

    inner class Node {
        var freq = 0
        var byteValue = -1 //对应的 byte值
        var left: Node? = null
        var right: Node? = null

        constructor(freq: Int, byteValue: Int, left: Node?, right: Node?) {
            this.freq = freq
            this.byteValue = byteValue
            this.left = left
            this.right = right
        }

        fun isLeaf() = left == null && right == null
    }
}