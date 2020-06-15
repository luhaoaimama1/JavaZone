package 算法.算法书籍.压缩

import java.io.*
import java.util.*

val file = File(".", "JavaTest_Zone\\src\\算法\\算法书籍\\压缩\\content.txt")
fun main(args: Array<String>) {
    //字符串转化成整形
    println("字符串转化成整形:${Integer.parseInt("00000101", 2)}")
    weakMap()
//    writeBit()
//    writeBit2()
//    readBit2()
//    print(" \n Byte 5 的byte.toBit方法=>")
//    readBit()
}

private fun weakMap() {
    var key: Any? = Any()
    exMaps.put(key, Property())
    println("Map size :" + exMaps.size)
    key = null
    System.gc()
    Thread.sleep(500) //需要点延时 不没清除完毕
    println("Map size :" + exMaps.size)
}

private fun readBit2() {
    val ins = BufferedInputStream(FileInputStream(file))
    var value = 0
    while (ins.readBit().apply {
                value = this
            } != -1) {
        print(value)
    }
}

private fun readBit() {
    val a = BooleanArray(8) { false }
    5.toByte().toBits(a) //101
    a.forEachIndexed { index, b ->
        print("${if (b) "1" else "0"}")
    }
}

private fun writeBit() {
    val file = File(".", "JavaTest_Zone\\src\\算法\\算法书籍\\压缩\\content.txt")
    val out = BufferedOutputStream(FileOutputStream(file))
    out.writeBit(true)
    out.writeBit(false)
    out.writeBit(true)
    out.writeBit(true)
    out.writeBitFlush()
//    结果应该是00001011
//    结果就是
    out.flush()
    out.close()
}

private fun writeBit2() {
    val file = File(".", "JavaTest_Zone\\src\\算法\\算法书籍\\压缩\\content.txt")
    val out = BufferedOutputStream(FileOutputStream(file))
    out.writeBit(5, 16)
    out.writeBitFlush()
//    结果应该是00001011
//    结果就是
    out.flush()
    out.close()
}

//数组是逆序的。这样迭代的时候 就能是从第一位开始迭代了
fun Byte.toBits(a: BooleanArray): Unit {
    if (a.size < 8) throw  IllegalStateException("must be > 8")
    for (i in 7 downTo 0) {
        //00000101取出倒数第三位的时候 向右移动2位00000001 得到1 。但是取出最后一位的时候如果也想上面的话那么就是00000101我只想看最后一位所以&00000001就能得到了
        a[7 - i] = (this.toInt() shr i and 1) == 1
    }
}

//扩展属性的值
class Property : HashMap<String, Any>()

var exMaps = object : WeakHashMap<Any, Property>() {
    override fun get(key: Any): Property? {
        var get = super.get(key)
        if (get == null) get = Property()
        set(key, get)
        return get
    }
}

var BufferedOutputStream.n: Int
    get() = exMaps[this]?.get("n") as? Int ?: 0
    set(value) {
        exMaps[this]?.set("n", value)
    }

var BufferedOutputStream.buffer2: Int
    get() = exMaps[this]?.get("buffer2") as? Int ?: 0
    set(value) {
        exMaps[this]?.set("buffer2", value)
    }

var BufferedOutputStream.a: BooleanArray
    get() {
        var get = exMaps[this]?.get("a")
        if (get == null) {
            get = BooleanArray(8) { false }
            exMaps[this]?.set("a", get)
        }
        return get as BooleanArray
    }
    set(value) {
        exMaps[this]?.set("a", value)
    }

fun BufferedOutputStream.writeBitFlush(): Unit {
    if (n != 0) {
        write(buffer2)
        buffer2 = 0
        n = 0
    }
}

fun BufferedOutputStream.writeBit(content: Int, bitCount: Int): Unit {
    for (i in bitCount - 1 downTo 0) {
        val value = (content shr i and 1) == 1
        writeBit(value)
    }
}

fun BufferedOutputStream.writeBit(bit: Boolean): Unit {
//    范例 val i = (1 shl 4) + 1 //00010001

    //左移一位  然后加上 bit.计数n
    buffer2 = buffer2 shl 1
    buffer2 += if (bit) 1 else 0
    n++

    //满8位  写进去 然后清空
    if (n == 8) {
        writeBitFlush()
    }
}

var BufferedInputStream.n: Int
    get() = exMaps[this]?.get("n") as? Int ?: 0
    set(value) {
        exMaps[this]?.set("n", value)
    }


var BufferedInputStream.buffer2: Int
    get() = exMaps[this]?.get("buffer2") as? Int ?: 0
    set(value) {
        exMaps[this]?.set("buffer2", value)
    }

/**
 * @return -1 0 1 .-1代表结尾了
 */
fun BufferedInputStream.readBit(): Int {
    if (n == 0) {
        buffer2 = read()
        if (buffer2 == -1) {
            buffer2 = 0
            return -1
        }
        n = 8
    }
    n--
    return buffer2 shr n and 1 //使用的n:7-0
}

fun BufferedInputStream.readBit(count: Int): String {
    val sb = StringBuilder()
    for (i in 0 until count) {
        val readBit = readBit()
        if (readBit != -1) sb.append(readBit)
    }
    return sb.toString()
}
