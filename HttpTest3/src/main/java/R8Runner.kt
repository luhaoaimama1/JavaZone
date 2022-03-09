import okio.Okio
import java.io.BufferedInputStream
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.util.*

val r8Path = "/Users/fuzhipeng/cmdline-tools/bin/retrace"
val mappingPath = "/Users/fuzhipeng/Desktop/mapping/4.13.00/mapping/mapping.txt"


fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    while (true) {
        var sb = StringBuilder()
        println("请输入:")
        while (true) {
            val nextLine = sc.nextLine()
            val toLowerCase = nextLine.toLowerCase()
            val endSuffix = ":wq"
            if (toLowerCase.equals(endSuffix)) {
                onEnd(sb, r8Path, mappingPath)
                //重新设置内容
                sb = StringBuilder()
                break
            } else if (toLowerCase.endsWith(endSuffix)) {
                sb.appendln(nextLine)

                onEnd(sb, r8Path, mappingPath)
                //重新设置内容
                sb = StringBuilder()
                break
            } else {
                sb.appendln(nextLine)
            }
        }
    }
}

private fun onEnd(sb: StringBuilder, r8Path: String, mappingPath: String) {
    val folderPath = "HttpTest3/ignoreFolder"
    val filePath = "r8Buggly.txt"
    val traceFilePath = writeFile(folderPath, filePath, sb.toString())
    try {
        val cmd = "${r8Path} ${mappingPath} ${traceFilePath} --verbose"
        println("cmd:"+cmd)
        println("================================执行retrace命令 请等待===================================")
        val p: Process = Runtime.getRuntime().exec(cmd)

        val reader = InputStreamReader(BufferedInputStream(p.inputStream), "UTF-8")
        println(reader.readText())
        println("=======================================================================================")
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


//最基本的写用法
private fun writeFile(folderPath: String, filePath: String, content: String): String {
    val file = File(folderPath)
    if (!file.isDirectory || !file.exists()) {
        file.mkdirs()
    }
    val pathname = "$folderPath/${filePath}"
    val sink = Okio.sink(File(pathname))
    val buffer = Okio.buffer(sink)//根据流那种 zhuang'shi
    buffer.writeUtf8(content)
    buffer.flush()
    return pathname
}