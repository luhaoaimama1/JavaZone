package 软硬引用的了解

import java.lang.ref.WeakReference
import java.util.ArrayList

//证明了 如果一个类引用了另一个类的数据。 另一个类是硬引用 当前类会被回收吗？
//答案是：会

//延伸思考： 如何才会内存泄漏？就是当前应该被回收的时候， 却被别人持有(就是硬持有 例如静态变量)。

//延伸思考2：为什么类A匿名内部类B。会导致内存泄漏？。当匿名内部类对象B被静态变量C或者别的对象C持有。就相当于被另一个对象C持有 A。
// 因为他是匿名的默认得到就需要A.B。所以持有B就相当于持有A
// 参考：﻿http://eb71329a.wiz03.com/share/s/3Hsjaq1-lQ9Q2SChN02Hkyvk2aHgHj1TFk5T2v6d3-1VcsOI


object WeakTest2 {
    @JvmStatic fun main(args: Array<String>) {
        val innerData = InnerData()
        val abcWeakRef = WeakReference(Data(innerData))
        println("回收前：Data->${abcWeakRef.get()} \t innerData->>$innerData")
        System.gc()
        println("回收后：Data->${abcWeakRef.get()} \t innerData->>$innerData")
    }

    class InnerData {}
    class Data(innerData: InnerData) {}
}
