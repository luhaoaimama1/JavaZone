package 算法.算法书籍.压缩

import 算法.算法书籍.字符串.查找树.StringST
import java.io.*

class LZW {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            easyTest()
            jpgTest()
        }

        private fun jpgTest() {
            val source = File(".", "${s}a.jpg")
            val compress = File(".", "${s}a-lzw-compress.jpg")
            val uncompress = File(".", "${s}a-lzw-uncompress.jpg")
            LZW().compress(source, compress)
            println()
            LZW().unCompress(compress, uncompress)
        }

        private fun easyTest() {
            val source = File(".", "${s}a2.txt")
            val compress = File(".", "${s}a2-lzw-compress.txt")
            val uncompress = File(".", "${s}a2-lzw-uncompress.txt")
            val out = BufferedOutputStream(FileOutputStream(source))
            out.write('A'.toInt())
            out.write('B'.toInt())
            out.write('A'.toInt())
            out.write('B'.toInt())
            out.write('A'.toInt())
            out.write('B'.toInt())
            out.write('A'.toInt())
            out.flush()
            out.close()

            LZW().compress(source, compress)
            println()
            LZW().unCompress(compress, uncompress)
        }
    }

    val R = 256
    var W = 12
    var L = 4096 //2^12


    fun compress(sourceFile: File, compressFile: File) {
        //因为是 key是字符串，value是int.所以字典采用单词查找树 st
        // 建立单词查找树 字典
        val st = StringST<Int>()

        //基础byte表写入字典
        for (i in 0 until R) {
            st.put(i.toChar().toString(), i)
        }
        // 256 终止标识也存入
        var stIndex = R//R是结束编码

        val inbs = BufferedInputStream(FileInputStream(sourceFile))
        val outbs = BufferedOutputStream(FileOutputStream(compressFile))

        var p = ""
        var c = ""
        while (true) {
            //读取byte ,-1的话退出
            val read = inbs.read()
            if (read == -1) break
            //更新当前值
            c = read.toChar().toString()//read.toByte().toChar().toString() 这种会导致越界 很怪
            //建立词组key=pc
            val key = p + c

            if (st.get(key) != null) {//字典找到了词组
                //更新p, p=key ,继续循环读取值
                p = key
            } else {//未找到
                //写入编码
                val content = st.get(p)!!
                outbs.writeBit(content, W)

                //造词 注意造词不要超过最大量4096 就是2^12 因为我们每次写入都是12位 所以想改的话可以同时改
                if (stIndex < L - 1) {
                    val value = ++stIndex
                    print("写入${content},造词${key}/${value} \t")
                    st.put(key, value)
                }
                //更新p ,p=c
                p = c
            }
        }

        val content = st.get(p)!!
        outbs.writeBit(content, W)
        print("写入${content}和终止代码${R}")
        //写入终止代码
        outbs.writeBit(R, 12)
        outbs.writeBitFlush()
        outbs.close()
    }

    fun unCompress(compressFile: File, unCompressFile: File) {
        //因为是 key是Int，value是String.所以数组当字典 st
        val st = Array<String>(L) { "" }

        //基础byte表写入字典
        for (i in 0 until R) {
            st[i] = i.toChar().toString()
        }
        var stIndex = R + 1//R是结束编码 跳过

        val inbs = BufferedInputStream(FileInputStream(compressFile))
        val outbs = BufferedOutputStream(FileOutputStream(unCompressFile))

        //读取第一个值 把p赋上 c=""
        val parseInt1 = Integer.parseInt(inbs.readBit(W), 2)
        var p = st[parseInt1]
        var c = ""
        //循环处理值 并扩展词组 根据字典写入对应的数据
        while (true) {
            //写入p  为什么先写入？ 因为刚刚已经读了第一个P了 为了读完就写 所以放入开头
            for (j in 0..p.lastIndex) {
                outbs.write(p[j].toInt())
            }

            //读取当前值
            val parseInt = Integer.parseInt(inbs.readBit(W), 2)
            c = st[parseInt]
            if (parseInt == R) break//碰到终止符 就退出了

            //不再字典中  .不会出现不再字典还不造的。因为压缩的时候已经控制了
            if (parseInt == stIndex)  c = p + p[0]// 推断字典中词组

            //在字典中
            if (stIndex < L) st[stIndex++] = p + c[0] //扩展词汇

            p = c
        }
        outbs.close()
    }

    private fun readString(st: Array<String>, inbs: BufferedInputStream) = st[Integer.parseInt(inbs.readBit(12), 2)]
}