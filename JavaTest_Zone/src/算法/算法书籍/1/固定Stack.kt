package 算法.算法书籍.`1`

fun main(args: Array<String>) {
    val sf = StackFixed<Int>()
    for (i in 0..60) {
        sf.push(i)
        print("${sf.size()} \t ")
    }
    println()

    while(!sf.isEmpty()){
        print("${sf.pop()} \t ")
    }

    println("迭代器")
    for (i in sf) {
        print("$i \t ")
    }
}

class StackFixed<T> : Iterable<T> {
    var arrays = arrayOfNulls<Any>(30) as Array<T?>
    var cursorSize = 0
    fun isEmpty() = cursorSize == 0
    fun size() = cursorSize
    private fun resize(size: Int) {
        println()
        println("resize====>:$size")
        var arraysNew = arrayOfNulls<Any>(size) as Array<T?>
        for (i in 0 until cursorSize) { //不包括游标 因为游标这个时候是超出数组内容的
            arraysNew[i] = arrays[i]
        }
        arrays = arraysNew
    }

    fun push(value: T) {
        val size = arrays.size
        if (cursorSize == size) {
            resize(size * 2)
        }
        arrays[cursorSize++] = value
    }

    //为什么不对 数组越界做检测 因为这个错误 你检测出来也要抛出  你检测干啥
    fun pop(): T{
        val t = arrays[--cursorSize]
        arrays[cursorSize] = null//弹出的元素 不应该在数组中保存了
        //如果内容小于1/4的话 size减半
        if (cursorSize > 0 && cursorSize == arrays.size / 4) resize(arrays.size / 2)
        return  t as T
    }

    override fun iterator(): Iterator<T> {
        return ReverseIterator()
    }

    inner class ReverseIterator : Iterator<T> {
        var index = cursorSize
        override fun hasNext(): Boolean = index > 0

        override fun next(): T = arrays[--index] as T
    }

}
