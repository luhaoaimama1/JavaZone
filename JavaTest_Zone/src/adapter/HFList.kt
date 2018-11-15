package adapter


enum class HFMode { ADD, ADD_OR_CHANGE; }

open class HFList<T>() {

    protected val INT_NOT_EXIST = Integer.MAX_VALUE
    val headerViewStyleOrder by lazy { ArrayList<Int>() }
    val footerViewStyleOrder by lazy { ArrayList<Int>() }
    var addHeaderEnable = true
    var addFooterEnable = true

    var headerMode = HFMode.ADD_OR_CHANGE
    var footerMode = HFMode.ADD_OR_CHANGE
    val headerDatas by lazy { ArrayList<T>() }
    val footerDatas by lazy { ArrayList<T>() }
    val contentDatas by lazy { ArrayList<T>() }

    var styleExtra: ViewStyle<T> = ViewStyleDefault()
    var extraConfigMap = HashMap<T, ViewStyleOBJ>()
    internal val T.extra: ViewStyleOBJ
        get() = extraConfigMap.get(this) ?: {
            val value = ViewStyleOBJ()
            extraConfigMap.set(this, value)
            value
        }.invoke()

    protected open fun generateConfig(item: T) {
        styleExtra.generateViewStyleOBJ(item)?.setNowValue(item.extra)
    }

    fun getItemViewType(position: Int): Int {
        if (posIsIllegal(position)) return -100
        val data = getCHFDatas().get(position)
        styleExtra.getItemViewType(position, data?.extra!!)
        return data.extra.viewStyle
    }

    open fun findViewStyle(viewStyle: Int): Int {
        getCHFDatas().forEachIndexed { index, it ->
            if (it.extra.viewStyle == viewStyle) return index
        }
        return -1
    }

    open fun updateExtraconfig(item: T, deal: (obj: ViewStyleOBJ) -> Unit) {
        if (!item.extra.isGenerate) generateConfig(item)
        deal(item.extra)
    }

    open fun getExtraConfig(item: T): ViewStyleOBJ? =
            if (getCHFDatas().indexOf(item) != -1) item.extra
            else null

    /**
     * 这个是不可操作的
     */
    fun getCHFDatas(): List<T> {
        val list = ArrayList<T>()
        list.addAll(headerDatas)
        list.addAll(contentDatas)
        list.addAll(footerDatas)
        return list.toList()
    }

    fun getCHFItemCount(): Int = contentDatas.size + headerDatas.size + footerDatas.size


    protected open fun emptyToItemNotify(): Boolean {
        printlnFun("emptyToItemNotify")
        return false
    }

    fun posIsIllegal(pos: Int) = pos < 0 || pos > getCHFItemCount()

    fun setData(nowIndex: Int, it: T) {
        if (nowIndex >= headerDatas.size && nowIndex < headerDatas.size + contentDatas.size) {
            contentDatas.set(nowIndex - headerDatas.size, it)
        } else if (nowIndex < headerDatas.size) { //header
            headerDatas.set(nowIndex, it)
        } else { //footer
            footerDatas.set(nowIndex - headerDatas.size - contentDatas.size, it)
        }
    }

    // ======================================= 数据操作 =====================================
    fun addOrChange(item: T?) {
        if (item == null) return
        val index = getCHFDatas().indexOf(item)
        if (index == -1) add(item)
        else {
            setData(index, item)
            notifyItemChangedInner(index)
        }
    }

    fun add(item: T?) {
        add(contentDatas.size + headerDatas.size, item)
    }

    fun add(pos: Int, item: T?) {
        if (item == null) return
        add(pos, listOf(item))
    }

    fun add(itemList: List<T>?) {
        add(contentDatas.size + headerDatas.size, itemList)
    }

    fun add(pos: Int, moreItems: List<T>?) {
        if (posIsIllegal(pos) || moreItems == null || moreItems.isEmpty()) return
        //如果添加的数据不为空, 考虑空数据状态 移除空数据状态
        emptyToItemNotify()

        //先记录content 中的位置 不然头部添加后 index 会偏移
        val contentDatasPosIndex = pos - headerDatas.size
        val contentList = ArrayList<T>()
        moreItems.forEach {
            if (!it.extra.isGenerate) generateConfig(it)
            if (headerViewStyleOrder.contains(it.extra.viewStyle)) {
                it.extra.part = Part.HEADER
                addHeader(it)
            } else if (footerViewStyleOrder.contains(it.extra.viewStyle)) {
                it.extra.part = Part.FOOTER
                addFooter(it)
            } else {
                it.extra.part = Part.CONTENT
                contentList.add(it)
            }
        }
        if (contentList.size == 0) return
        //头部和底部都完成了
        val posReal = contentDatasPosIndex + headerDatas.size
        contentDatas.addAll(contentDatasPosIndex, contentList)
        notifyItemRangeInsertedInner(posReal, contentList.size)
    }

    fun addFooter(item: T) { //不判断空
        if (!addFooterEnable) throw  IllegalStateException("从映射 不支持 添加底部")
        val insertOrderIndex = getFooterOrderIndex(item)
        if (insertOrderIndex == INT_NOT_EXIST) throw  IllegalStateException("底部顺序中没有此 viewStyle")
        val footerBeginIndex = contentDatas.size + headerDatas.size

        for (index: Int in getCHFDatas().size - 1 downTo 0) {
            val footerDatasIndex = index - footerBeginIndex
            if (footerDatasIndex < 0) break //让他走临界点 去添加数据
            try {
                val nowOrderIndex = getFooterOrderIndex(footerDatas[footerDatasIndex])
                if (footerMode == HFMode.ADD_OR_CHANGE && insertOrderIndex == nowOrderIndex) {
                    footerDatas.set(footerDatasIndex, item)
                    notifyItemChangedInner(index)
                    return
                }
                if (insertOrderIndex <= nowOrderIndex) { //  等于的时候是 Mode为add模式
                    footerDatas.add(footerDatasIndex + 1, item) // 因为是倒叙的 所以要加1
                    printlnFun("插入：找到比 插入的排序 大的值了")
                    notifyItemInsertedInner(index + 1)
                    return
                }
            } catch (e: Exception) {
                print(e)
            }
        }
        //都当底部现在是1 然后添加2 这时候仅仅有底部 并且是add模式。因为没有找到>2的值所以 需要自己去add
        //就是临界点
        printlnFun("底部插入：临界点")
        val index = headerDatas.size + contentDatas.size
        footerDatas.add(0, item)
        notifyItemInsertedInner(index)
    }

    fun addHeader(item: T) { //不判断空
        if (!addHeaderEnable) throw  IllegalStateException("从映射 不支持 添加头部")
        val insertOrderIndex = getHeaderOrderIndex(item)
        if (insertOrderIndex == INT_NOT_EXIST) throw  IllegalStateException("头部顺序中没有此 viewStyle")
        getCHFDatas().forEachIndexed { index, it ->
            val nowOrderIndex = getHeaderOrderIndex(it)
            if (headerMode == HFMode.ADD_OR_CHANGE && insertOrderIndex == nowOrderIndex) {
                headerDatas.set(index, item)
                notifyItemChangedInner(index)
                return
            }
            if (insertOrderIndex < nowOrderIndex) { //  等于的时候是 Mode为add模式
                headerDatas.add(index, item)
                notifyItemInsertedInner(index)
                return
            }
        }
        //都当头类型现在是1，2 然后添加2 这时候仅仅有头部 并且是add模式。因为没有找到>2的值所以 需要自己去add
        printlnFun("头部插入：临界点")
        val index = headerDatas.size //add 相当于set 然后把其他的数据往后移动
        headerDatas.add(index, item)
        notifyItemInsertedInner(index)
    }

    private fun getHeaderOrderIndex(item: T): Int {
        val index = headerViewStyleOrder.indexOf(item.extra.viewStyle)
        return if (index == -1) INT_NOT_EXIST else index
    }

    private fun getFooterOrderIndex(item: T): Int {
        val index = footerViewStyleOrder.indexOf(item.extra.viewStyle)
        return if (index == -1) INT_NOT_EXIST else index
    }

    fun remove(positionStart: Int, itemCount: Int) {
        if (positionStart < 0 || (positionStart + itemCount > getCHFItemCount())) return
        var itemCountEnd = itemCount-1 + positionStart
        //移除尾部
        if (itemCountEnd >= headerDatas.size + contentDatas.size) {//footer
            var startPosi = 0
            if (positionStart < headerDatas.size + contentDatas.size) {//
                //positionStart..footer[....itemCount..]  后面的算中间移除的部分
                startPosi = headerDatas.size + contentDatas.size
            } else {
                //footer[..positionStart..itemCount..]
                startPosi = positionStart
            }
            val count = itemCountEnd - startPosi
            for (i in count downTo 0) {
                extraConfigMap.remove(footerDatas.get(i))
                footerDatas.removeAt(i)
            }
            val footerRemoveCount = count + 1
            notifyItemRangeRemovedInner(startPosi, footerRemoveCount)
            itemCountEnd -= footerRemoveCount //这里直接结束地点
        }
        //移除中间部分
        if (itemCountEnd >= headerDatas.size && itemCountEnd < headerDatas.size + contentDatas.size
                ) {// content
            var startPosi = 0
            if (positionStart < headerDatas.size) {
                // positionStart.. content[..itemCount..]
                startPosi = headerDatas.size
            } else {
                // .. content[..positionStart..itemCount..]
                startPosi = positionStart
            }
            val count = itemCountEnd - startPosi
            for (i in count downTo 0) {
                extraConfigMap.remove(contentDatas.get(i))
                contentDatas.removeAt(i)
            }
            val contentRemoveCount = count + 1
            notifyItemRangeRemovedInner(startPosi, contentRemoveCount)
            itemCountEnd -= contentRemoveCount //这里直接结束地点
        }
        //移除header部分
        if (itemCountEnd < headerDatas.size) {//header
            // head[.positionStart..itemCount]
            for (i in itemCountEnd downTo positionStart) {
                extraConfigMap.remove(headerDatas.get(i))
                headerDatas.removeAt(i)
            }
            val headerRemoveCount = itemCountEnd - positionStart + 1
            notifyItemRangeRemovedInner(positionStart, headerRemoveCount)
            //over! //这里直接结束地点
        }
    }


    fun changed(item: T) {
        changed(item, null)
    }

    fun changed(item: T, payload: Any?) {
        val list = getCHFDatas()
        val posi = list.indexOf(item)
        changed(posi, listOf(item), payload)
    }

    fun changed(pos: Int, moreItems: List<T>, payload: Any?) {
        if (pos == -1) return
        moreItems.forEachIndexed { index, it ->
            setData(index + pos, it)
        }
        notifyItemRangeChangedInner(pos, moreItems, payload)
    }

    fun moved(fromPosition: Int, toPosition: Int) {
        if (fromPosition < 0 || (fromPosition > contentDatas.size - 1)) return
        if (toPosition < 0 || (toPosition > contentDatas.size - 1)) return

        val tempObj = contentDatas.get(fromPosition)
        setData(fromPosition, contentDatas.get(toPosition))
        setData(toPosition, tempObj)
        notifyItemMovedInner(fromPosition, toPosition)

    }

    //todo 变成空数据的时候没有 通知啊,
    open fun clearAll() {
        val count = getCHFItemCount()
        clearWithExtraConfig(contentDatas, headerDatas, footerDatas)
        notifyItemRangeRemovedInner(0, count)
    }

    private fun clearWithExtraConfig(vararg lists: ArrayList<T>) {
        for (list in lists) {
            for (item in list) extraConfigMap.remove(item)
            list.clear()
        }
    }

    open fun clearHeaderDatas() {
        val count = headerDatas.size
        clearWithExtraConfig(headerDatas)
        notifyItemRangeRemovedInner(0, count)
    }

    open fun clearContentDatas() {
        val count = contentDatas.size
        clearWithExtraConfig(contentDatas)
        notifyItemRangeRemovedInner(headerDatas.size, count)
    }


    open fun clearFooterDatas() {
        val count = footerDatas.size
        clearWithExtraConfig(footerDatas)
        notifyItemRangeRemovedInner(headerDatas.size + contentDatas.size, count)
    }


    // ======================================= notify系列=====================================

    protected open fun notifyItemChangedInner(index: Int) {
    }

    protected open fun notifyItemRangeChangedInner(pos: Int, moreItems: List<T>, payload: Any?) {
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

