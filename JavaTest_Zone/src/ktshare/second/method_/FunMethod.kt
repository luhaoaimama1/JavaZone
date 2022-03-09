package ktshare.second.method_

fun abc(first: String, varFun: (a: String, b: String) -> String) {
    println(first + varFun.invoke("a", "b"))
}

/**
 *  varFun: Int.(a: String, b: String) -> String)
 *   类似扩展方法 而方法名则是参数名
 *   所以 1.varFun("a", "b") 这样使用可以。也可以 varFun.invoke(1, "a", "b")
 *
 *  Int.代表这个方法体实现的时候
 *  { a, b -> 这里的this 就是这个Int
 *         return ""
 *  }
 */
fun highFun(first: String, varFun: Int.(a: String, b: String) -> String) {
//    println(first + 1.varFun("a", "b"))
    println(first + varFun.invoke(1, "a", "b"))
}

fun main(args: Array<String>) {
    //在 Kotlin 中有一个约定：如果函数的最后一个参数接受函数，那么作为相应参数传入的 lambda 表达式可以放在圆括号之外：
    abc("a~", { a, b ->
        return@abc "$a======>$b"
    })

    abc("a~") { a, b ->
        return@abc "$a======>$b"
    }


    //这里的用法 就可以用sample去看 就能感觉到有用了
    highFun("a~") { a, b ->
        val toString = toString()
        return@highFun "$a======>$b"
    }
}