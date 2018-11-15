package 软硬引用的了解

import java.lang.ref.WeakReference
import java.util.ArrayList

object WeakTest2 {
    @JvmStatic fun main(args: Array<String>) {
        var innerData = InnerData()
        val abcWeakRef = WeakReference(Data(innerData))
        println("回收前：Data->${abcWeakRef.get()} \t innerData->>$innerData")
        System.gc()
        println("回收后：Data->${abcWeakRef.get()} \t innerData->>$innerData")
    }

    class InnerData {}
    class Data(innerData: InnerData) {}
}
