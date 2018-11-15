package a_b_改善

/**
 * Created by fuzhipeng on 2018/11/13.
 *
 * page页面为问题
 */
fun main(args: Array<String>) {
    println("==:${Page.HOME.isHome()}")
    println(Page.values()[Page.values().indexOf(Page.HOME)])
    println("==:${Page.STICKY.isHome()}")
}

/**
 * val pageIndex: Int。已经被 枚举代替了
 * @param traceString 页面埋点用的字符串
 * @param pageFrom 页面from 上报的数字
 */
enum class Page(val traceString: String, val pageFrom: String) {
    HOME("home", "10"),
    STICKY("sticky", "11"),
    other("other", "-1");

    fun isHome() = (this == HOME)
}