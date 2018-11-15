package kt.扩展

/**
 * Created by fuzhipeng on 2018/11/15.
 */
val String.lastIndex: Int
    get() = this.length - 1

val <E> MutableList<E>.lastObj: E?
    get() = this[this.size - 1]


