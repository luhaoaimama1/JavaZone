package kt.扩展

/**
 * Created by fuzhipeng on 2018/11/15.
 */

//方法扩展
fun String.twoCount() = this.count() * 2

//泛型扩展
fun <E> MutableList<E>.second(): E? {
    return this.get(1)
}
