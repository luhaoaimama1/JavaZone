import hook.javassist.JavassistStudy.folderPath
import okio.ByteString
import okio.Okio
import java.io.File
import java.nio.charset.Charset
import java.util.*
import kotlin.collections.ArrayList

//https://blog.csdn.net/xiatiandefeiyu/article/details/77966237
fun main(args: Array<String>) {
    val arrayList = ArrayList<String>()
    val sink = Okio.source(File("/Users/fuzhipeng/JavaProjects/JavaZone/HttpTest3/src/main/java/XX"))
    val buffer = Okio.buffer(sink)
    var readBytes: String? = ""

    while (buffer.readUtf8Line().apply {
                readBytes = this
            } != null) {
        if (readBytes?.contains("#") ?: false) {

            arrayList.add(readBytes?.toLowerCase() ?: "")
        }
    }
    arrayList.sortWith(object : Comparator<String> {
        override fun compare(o1: String, o2: String): Int {
            val key1 = if (o1.split(" ").size > 1) o1.split(" ")[1] else "a"
            val key2 = if (o2.split(" ").size > 1) o2.split(" ")[1] else "a"
            return if (key1[0] > key2[0]) 1 else if(key1[0] == key2[0]) 0 else -1
        }
    })
//    arrayList.toSortedSet()
    for (s in arrayList) {
        println(s)
    }

}
