package a_b_改善

/**
 * Created by fuzhipeng on 2018/11/13.
 *
 * page页面为问题
 */
fun main(args: Array<String>) {
//    println("==:${Page.HOME.isHome()}")
//    println(Page.values()[Page.values().indexOf(Page.HOME)])
//    println("==:${Page.STICKY.isHome()}")

//    Translates.values().forEach {
//        println("name:${it.chinese}  \t aaa:${it.name} ")
//    }
    Translates.valueOf("FEMALE").let {
        println("name:${it.chinese}  \t aaa:${it.name} ")
    }

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


enum class Translates(val chinese: String) {

    //    男性：MALE，女性：FEMALE
    MALE("男性"),
    FEMALE("女性"),

    //    。会阴区：PERINEUM，
//    右大腿：RIGHTTHIGH，
//    右膝盖：RIGHTKNEE，
//    右小腿：RIGHTCALF，
//    左大腿： LEFTTHIGH，
//    左膝盖：LEFTKNEE，
//    左小腿：LEFTCALF，
//    右足：RIGHTFOOT，
//    左足： LEFTFOOT，
//    腰部：WAIST，
//    臀部：HIP
//    ，骶部：CROTCH
    PERINEUM("会阴区"),

    RIGHTTHIGH("右大腿"),
    RIGHTKNEE("右膝盖"),
    RIGHTCALF("右小腿"),

    LEFTTHIGH("左大腿"),
    LEFTKNEE("左膝盖"),
    LEFTCALF("左小腿"),

    RIGHTFOOT("右足"),
    LEFTFOOT("左足"),
    WAIST("腰部"),
    HIP("臀部"),
    CROTCH("骶部")
}