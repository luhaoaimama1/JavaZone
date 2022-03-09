import okio.ByteString
import okio.Okio
import java.io.File
import java.nio.charset.Charset

//https://blog.csdn.net/xiatiandefeiyu/article/details/77966237
fun main(args: Array<String>) {
//    Okio相当于一个工具类为我们返回各种需要的流工具

//    File("1.txt") 路径是JavaZone目录下
    val folderPath = "HttpTest3/ignoreFolder"
    val file = File(folderPath)
    file.mkdirs()

//    //吸入
//    write(folderPath)
//
//    //读取
//    read(folderPath)
//
//    //复制
    readWrite(file)

//    en2decode()
}

private fun en2decode() {
    //    编解码
    val content = "haha,我觉得还是不破尚是男一号！"
    val charset = Charset.forName("utf-8")
    val toByteArray = content.toByteArray(charset)
    val bs = ByteString.of(*toByteArray)
/*
 编解码：
    base64Url专门给url用的
    base64这个是除了url用的
    hex 16进制
*/

    println("base64 编码==>:${bs.base64()}")
    println("base64 解码==>:${ByteString.decodeBase64(bs.base64())?.string(charset)}")
    // 返回十六进制编码的字节字符串

    println("hex 编码==>:${bs.hex()}")
    println("hex 解码==>:${ByteString.decodeHex(bs.hex())?.string(charset)}")
//    IOUtils.writeHexStringToBytes的这个不对

/*
*  加密解密：
*  MD5,shaXXX
*/
    //为什么要这么写.hex呢
    // 因为结果用的是String  而md5则返回的是bytes hex直接返回字符串，不然就要用string(charset)相当于省略了chaset
    //url用MD5这样更安全 所有人都无法根据值查看你浏览的网站
    val cacheKey = ByteString.encodeUtf8("url".toString()).md5().hex();
    val str2 = "channel=13863&openid=5017570&time=1617967481&nick=一朵小红花噢噢噢&avatar=http://static1.kaixinyf.cn/img/lza5e8ed0642c09c784159427.png&sex=1&phone=b567fb71db0f446b8b2ed5395a48e16d"
    val cacheKey2 = ByteString.encodeUtf8(str2).md5().hex();
    println("hex cacheKey2==>:${cacheKey2}")
}

private fun readWrite(file: File) {
    val readFile = File(file, "README.md")
    val totalSize = readFile.length()
    val readOkio = Okio.source(readFile)
    val readBuffer = Okio.buffer(readOkio)

    val writeFile = File(file, "README2.md")
    val writeOkio = Okio.sink(writeFile)
    val writeBuffer = Okio.buffer(writeOkio)//扩展功能  真正带有buffer功能的kio
    val bytes = 1024 * 2L
    val buffer = writeBuffer.buffer() //真正的缓存区

    var calBytes = 0L
    var readBytes = 0L
    //读取内容到  写入kio的缓存区
    while (readBuffer.read(buffer, bytes).apply {
                readBytes = this
            } != -1L) {
        buffer.emit()
        calBytes += readBytes

        println("进度：totlesize: $totalSize   now:$calBytes   progress:${calBytes * 1F / totalSize}")
    }
    writeBuffer.close()
}

//最基本的写用法
fun write(folderPath: String) {
    val sink = Okio.sink(File("$folderPath/1.txt"))
    val buffer = Okio.buffer(sink)//根据流那种 zhuang'shi
    buffer.writeString("www", Charset.forName("UTF-8"))
    buffer.flush()
}

//最基本的读用法
private fun read(folderPath: String) {
    val sink = Okio.source(File("$folderPath/1.txt"))
    val buffer = Okio.buffer(sink)
    val readString = buffer.readString(Charset.forName("UTF-8"))
    print("result:$readString")
}