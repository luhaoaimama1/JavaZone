package a_新手

/**
 * Created by fuzhipeng on 2018/9/7.
 */
fun main(args: Array<String>) {
    val arr1 = ArrayList<String?>()
    val arr2 = ArrayList<String?>()
    val arr3 = ArrayList<String?>()
    val listCollection = ListCollection2<String?>()
    listCollection.list.add(arr1)
    listCollection.list.add(arr2)
    listCollection.list.add(arr3)

    arr1.add("arg1_1")
    arr1.add("arg1_2")
    arr1.add("arg1_3")

    arr2.add("arg2_1")
    arr2.add("arg2_2")

    arr3.add("arg3_1")
    arr3.add("arg3_2")
    arr3.add("arg3_3")
    println(listCollection.count())
    for (i in -1..8) {
        println(listCollection.getItem(i))
    }
    listCollection.beginIndex(arr2)

}

class ListCollection2<T> {
    var list = ArrayList<ArrayList<T?>>()
    fun count(): Int {
        var tempCount = 0
        list.forEach { tempCount += it.count() }
        return tempCount
    }

    fun getItem(pos: Int): T? {
        if (pos < 0) return null //小于0
        var iterPos = 0
        list.forEachIndexed { index, arrayList ->
            arrayList.forEachIndexed { index2, t ->
                if (iterPos == pos) return t
                iterPos++
            }
        }
        return null //超过
    }

    //0开始
    fun beginIndex(item: ArrayList<T>): Int {
        var tempCount = 0
        var hasItem = false
        for (i in 0..list.size) {
            if (list[i] == item) {
                hasItem = true
                break
            }
            tempCount += list[i].count()
        }
        return if (hasItem) tempCount else -1
    }

}