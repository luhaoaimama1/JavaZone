package adapter2

import adapter.Part
import adapter.ViewStyle
import adapter.ViewStyleDefault
import adapter.ViewStyleOBJ

class ListCollection<T> {

    var list = ArrayList<ArrayList<T>>()

    private fun posIsIllegal(pos: Int) = pos < 0 || pos >= count()

    fun count(): Int {
        var tempCount = 0
        list.forEach { tempCount += it.count() }
        return tempCount
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

    //0开始
    fun endIndex(item: ArrayList<T>): Int {
        val index = beginIndex(item)
        return if (index == -1) -1 else index + (item.count() - 1)
    }

    fun loop(method: (index: Int, item: T) -> Boolean) {
        var iterPos = 0
        for (index in 0..list.size) A@ {
            for (index2 in 0..list[index].size) {
                if (method(iterPos, list[index][index2])) return@A
                iterPos++
            }
        }
    }

    fun getItem(pos: Int): T? {
        if (posIsIllegal(pos)) return null
        var result: T? = null
        loop { index, item ->
            if (index == pos) {
                result = item
                true
            } else false
        }
        return result
    }

    fun setItem(pos: Int, item: T) {
        if (posIsIllegal(pos)) return
        var iterPos = 0
        for (index in 0..list.size) A@ {
            for (index2 in 0..list[index].size) {
                if (iterPos == pos) {
                    list[index].set(index2, item)
                    return@A
                }
                iterPos++
            }
        }
    }

    fun index(item: T): Int {
        var resultIndex = -1
        for (i in 0..list.size) {
            val indexTemp = list[i].indexOf(item)
            if (indexTemp != -1) {
                resultIndex = indexTemp
                break
            }
        }
        return resultIndex
    }

    fun remove(positionStart: Int, itemCount: Int, method: (removeItem: T) -> Unit): Boolean {
        if (posIsIllegal(positionStart) || posIsIllegal(positionStart + itemCount)) return false
        var iterPos = count() - 1 //从0开始 总共个数

        // return true false就行 因为位置我做了验证 所以如果结尾能删除就代表成功了
        var removeSuccess = false
        var isBreak = false
        for (i in list.size - 1 downTo 0) {
            if (isBreak) break
            for (j in list.get(i).size - 1 downTo 0) {
                if (iterPos >= positionStart && iterPos < positionStart + itemCount) {
                    method(list.get(i).get(j))
                    list.get(i).removeAt(j)
                    if (!removeSuccess) removeSuccess = true
                }
                if (iterPos < positionStart) {
                    isBreak = true
                    break
                }
                iterPos--
            }
        }
        return removeSuccess
    }

    fun clear(item: ArrayList<T>) {

    }
}

class DataWarp<T>(var data: T)

enum class HFMode { ADD, ADD_OR_CHANGE; }

//为啥用包装类呢？ 因为empty loading这种都可以用添加一个假数据，而用T的话 没办法通过反射 添加实例
//那为什么用包装类 还用 extraConfigMap 呢 是为了防止一个 数据从新生成配置。 其次未添加的也可以配置数据
//为啥不用占位符处理呢？因为占位符 处理 增加删除动画  太麻烦了  通过NineGrid 的时候我才反应过来。用notifyChanged没问题 但是用动画则。。。

//最终都是通过数据进行控制的！
open class HFList2<T>() {

    val headerViewStyleOrder by lazy { ArrayList<Int>() } // 顺序都是往下来的
    val footerViewStyleOrder by lazy { ArrayList<Int>() }
    var addHeaderEnable = true
    var addFooterEnable = true

    var headerMode = HFMode.ADD_OR_CHANGE
    var footerMode = HFMode.ADD_OR_CHANGE
    val headerDatas by lazy { ArrayList<DataWarp<T>>() }
    val footerDatas by lazy { ArrayList<DataWarp<T>>() }
    val contentDatas by lazy { ArrayList<DataWarp<T>>() }
    val mListCollection = ListCollection<DataWarp<T>>()
    var styleExtra: ViewStyle<T> = ViewStyleDefault()
    var extraConfigMap = HashMap<T, ViewStyleOBJ>()
    internal val T.extra: ViewStyleOBJ
        get() = extraConfigMap.get(this) ?: {
            val value = ViewStyleOBJ()
            extraConfigMap.set(this, value)
            value
        }.invoke()


    init {
        mListCollection.list.add(headerDatas)
        mListCollection.list.add(contentDatas)
        mListCollection.list.add(footerDatas)
        emptyNotify()
    }

    // ======================================= Config系列=====================================

    protected open fun generateConfig(item: T) {
        styleExtra.generateViewStyleOBJ(item)?.setNowValue(item.extra)
    }

    open fun updateExtraconfig(item: T, deal: (obj: ViewStyleOBJ) -> Unit) {
        if (!item.extra.isGenerate) generateConfig(item)
        deal(item.extra)
    }

    open fun getExtraConfig(item: T): ViewStyleOBJ? = extraConfigMap.get(item)

    fun getItemViewType(position: Int): Int {
        if (posIsIllegal(position)) return -100 //todo  换成我那个 无效的类型
        val item = mListCollection.getItem(position)
        styleExtra.getItemViewType(position, item?.data?.extra!!)
        return item.data.extra.viewStyle
    }

    open fun findFirstViewStyle(viewStyle: Int): Int {
        var resultIndex = -1
        mListCollection.loop { index, item ->
            if (item.data.extra.viewStyle == viewStyle) {
                resultIndex = index
                true
            } else false
        }
        return resultIndex
    }

// ======================================= empty系列=====================================

    protected open fun emptyToItemNotify(): Boolean {
        printlnFun("emptyToItemNotify")
        return false
    }

    protected open fun emptyNotify(): Boolean {
        printlnFun("emptyNotify")
        return false
    }

    protected open fun removeCheckEmpty() {

    }
    // ======================================= 数据操作 =====================================

    private fun posIsIllegal(pos: Int): Boolean {
        val endIndex = mListCollection.endIndex(contentDatas)
        return if (pos > endIndex || pos < endIndex - contentDatas.size) true else false
    }

    fun add(item: T) {
        add(mListCollection.endIndex(contentDatas), item)
    }

    fun add(pos: Int, item: T) {
        add(pos, listOf(item))
    }

    fun add(itemList: List<T>?) {
        add(mListCollection.endIndex(contentDatas), itemList)
    }

    fun add(pos: Int, moreItems: List<T>?) {
        if (posIsIllegal(pos) || moreItems == null || moreItems.isEmpty()) return
        //如果添加的数据不为空, 考虑空数据状态 移除空数据状态
        emptyToItemNotify()

        //先记录content 中的位置 不然头部添加后 index 会偏移
        val contentList = ArrayList<DataWarp<T>>()
        moreItems.forEach {
            if (!it.extra.isGenerate) generateConfig(it)
            if (headerViewStyleOrder.contains(it.extra.viewStyle)) {
                it.extra.part = Part.HEADER
                if (!addHeaderEnable) throw  IllegalStateException("从映射 不支持 添加头部")
                addOrderList(it, headerDatas, headerViewStyleOrder)
            } else if (footerViewStyleOrder.contains(it.extra.viewStyle)) {
                it.extra.part = Part.FOOTER
                if (!addFooterEnable) throw  IllegalStateException("从映射 不支持 添加底部")
                addOrderList(it, footerDatas, footerViewStyleOrder)
            } else {
                it.extra.part = Part.CONTENT
                contentList.add(DataWarp<T>(it))
            }
        }
        if (contentList.size == 0) return
        //头部和底部都完成了
        contentDatas.addAll(contentList)
        notifyItemRangeInsertedInner(mListCollection.beginIndex(contentDatas), contentList.size)
    }

    private fun addOrderList(item: T, datas: ArrayList<DataWarp<T>>, viewStyleOrder: ArrayList<Int>) {
        val insertOrderIndex = viewStyleOrder.indexOf(item.extra.viewStyle)
        if (insertOrderIndex == -1) throw  IllegalStateException("viewStyleOrder 顺序中没有此 viewStyle")
        datas.forEachIndexed { index, it ->
            val nowOrderIndex = viewStyleOrder.indexOf(it.data.extra.viewStyle)
            val mode = if (datas == headerDatas) headerMode else footerMode
            if (mode == HFMode.ADD_OR_CHANGE && insertOrderIndex == nowOrderIndex) {
                datas.set(index, DataWarp(item))
                notifyItemChangedInner(index + mListCollection.beginIndex(datas))
                return
            }
            if (insertOrderIndex < nowOrderIndex) { //  等于的时候是 Mode为add模式
                datas.add(index, DataWarp(item))
                notifyItemInsertedInner(index + mListCollection.beginIndex(datas))
                return
            }
        }
        //都当头类型现在是1，2 然后添加2 这时候仅仅有头部 并且是add模式。因为没有找到>2的值所以 需要自己去add
        printlnFun("插入：临界点")
        val index = datas.size //add 相当于set 然后把其他的数据往后移动
        datas.add(index, DataWarp(item))
        notifyItemInsertedInner(index + mListCollection.beginIndex(datas))
    }


    fun remove(positionStart: Int, itemCount: Int) {
        if (mListCollection.remove(positionStart, itemCount) { removeItem ->
            extraConfigMap.remove(removeItem.data)
        }) notifyItemRangeRemovedInner(positionStart, itemCount)
        removeCheckEmpty()
    }

    fun changed(item: T) = changed(item, null)

    fun changed(item: T, payload: Any?) {
        var posi = -1
        var dataWarp: DataWarp<T>? = null
        mListCollection.loop { index, it ->
            if (it.data == item) {
                posi = index
                dataWarp = it
                true
            } else false
        }

        if (posi != -1) {
            dataWarp!!.data = item
            mListCollection.setItem(posi, dataWarp!!)
            notifyItemRangeChangedInner(posi, payload)
        }
    }

    //头部尾部都是有顺序的所以不能移动
    fun movedContent(fromPosition: Int, toPosition: Int) {
        val beginIndex = mListCollection.beginIndex(contentDatas)
        val endIndex = mListCollection.endIndex(contentDatas)
        //仅仅支持 content
        if (fromPosition < beginIndex || fromPosition > endIndex ||
                endIndex < beginIndex || endIndex > endIndex) return

        val tempObj = mListCollection.getItem(fromPosition)
        mListCollection.remove(fromPosition, 1) { _ -> }
        if (tempObj == null) return
        mListCollection.setItem(toPosition, tempObj)
        notifyItemMovedInner(fromPosition, toPosition)

    }

    open fun clearAll() {
        clearHeaderDatas();
        clearContentDatas();
        clearFooterDatas();
    }

    private fun clearWithExtraConfig(vararg lists: ArrayList<DataWarp<T>>) {
        for (list in lists) {
            for (item in list) extraConfigMap.remove(item.data)
        }
    }

    open fun clearHeaderDatas() {
        clear(headerDatas)

    }

    open fun clearContentDatas() {
        clear(contentDatas)
    }

    open fun clearFooterDatas() {
        clear(footerDatas)
    }

    private fun clear(item: ArrayList<DataWarp<T>>) {
        val beginIndex = mListCollection.beginIndex(item)
        val itemCount = item.size
        if (beginIndex == -1 || itemCount == 0) return
        clearWithExtraConfig(item)
        item.clear()
        notifyItemRangeRemovedInner(beginIndex, itemCount)
        removeCheckEmpty()
    }


    // ======================================= notify系列=====================================

    protected open fun notifyItemChangedInner(index: Int) {
    }

    protected open fun notifyItemRangeChangedInner(pos: Int, payload: Any?) {
    }

    protected open fun notifyItemMovedInner(fromPosition: Int, toPosition: Int) {
    }

    // 调用内部通知  这样覆盖方法即可

    protected open fun notifyItemRangeRemovedInner(positionStart: Int, itemCount: Int) {
    }

    protected open fun notifyItemInsertedInner(position: Int) {
    }

    protected open fun notifyItemRangeInsertedInner(positionStart: Int, itemCount: Int) {
    }

    protected fun printlnFun(s: String) {
//        println(s)
    }
}

