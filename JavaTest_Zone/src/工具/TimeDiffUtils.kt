package 工具

fun main(args: Array<String>) {

    println("${RecordUtils.count(RecordUtils::class.java)}")
    println("${RecordUtils.isGreaterMax(RecordUtils::class.java, 3)}")
    println("${RecordUtils.isGreaterMax(RecordUtils::class.java, 3)}")

}
object RecordUtils {
    internal var timeMaps: MutableMap<String, Int> = HashMap()

    @JvmStatic
    fun count(tag: Class<out Any>): Int {
        return count(tag.canonicalName)
    }

    @JvmStatic
    fun count(tag: String): Int {
        val result = (timeMaps[tag] ?: 0) + 1
        timeMaps[tag] = result
        return result
    }

    @JvmStatic
    fun isGreaterMax(tag: String, max: Int): Boolean {
        return count(tag) >= max
    }

    @JvmStatic
    fun isGreaterMax(tag: Class<out Any>, max: Int): Boolean {
        return isGreaterMax(tag.canonicalName, max)
    }
}
