package ktshare.third

/**
 * 非空 3种那个都行
 * 当然能用 懒加载属性 或者 延迟加载属性更好了
 */
fun nullState0(var1: String?) {
    var1?.let {
        // 做逻辑
    }
}

fun nullState1(var1: String?) {
    val sVar = var1 ?: return
    val intVar = var1 as? Int ?: return
    // 做逻辑
}

fun nullState2(var1: String?) {
    if (var1 == null) {
        return
    }
    var1[1]
}

fun nullState3(var1: String?) {
    //android 好使 可能是高版本的问题 return是可以添加let等方法的
//    val sideTask = var1 ?: return let {
//        println("abc")
//    }
}


