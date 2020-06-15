package 算法.算法书籍.压缩

import java.io.*

const val s = "JavaTest_Zone\\src\\算法\\算法书籍\\压缩\\"

object RunLength {
    @JvmStatic
    fun main(args: Array<String>) {
        compress()
        uncompress()
    }

    val source = File(".", "${s}a.jpg")
    val compress = File(".", "${s}a-runlength-compress.jpg")
    val uncompress = File(".", "${s}a-runlength-uncompress.jpg")

    fun compress() {
        val inbs = BufferedInputStream(FileInputStream(source))
        val outbs = BufferedOutputStream(FileOutputStream(compress))
        val bytes = ByteArray(8192) { 0.toByte() }
        var len = 0
        val maxCount = 255
        var count = 0
        val a = BooleanArray(8) { false }
        var b = false //交替写入的
        while (inbs.read(bytes).apply { len = this } != -1) {
            for (i in 0 until len) {
                bytes[i].toBits(a) //byte->解析成bit
                for (j in a) { //对每个bit记录重复的值
                    if (j == b) {
                        count++
                        if (count > maxCount) { //超过 写入 并把count记录成1
                            outbs.write(maxCount)
                            outbs.write(0)
                            count = 1
                        }
                    } else { //不同写入  并记录count=1
                        outbs.write(count)
                        b = !b
                        count = 1
                    }
                }
            }
        }
        outbs.flush()
        outbs.close()
    }

    fun uncompress() {
        val inbs = BufferedInputStream(FileInputStream(compress))
        val outbs = BufferedOutputStream(FileOutputStream(uncompress))
        val bytes = ByteArray(8192) { 0.toByte() }
        var len = 0
        var b = false //交替写入的
        while (inbs.read(bytes).apply { len = this } != -1) {
            for (i in 0 until len) {
                //每读一byte则  转成次数 写入 交替写b  写完byte的话交替b
                for (j in 0 until bytes[i]) {
                    outbs.writeBit(b)
                }
                b = !b
            }
        }
        outbs.writeBitFlush()
        outbs.flush()
        outbs.close()
    }
}