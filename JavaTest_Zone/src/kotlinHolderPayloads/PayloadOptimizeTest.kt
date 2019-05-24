package kotlinHolderPayloads


/**
 * Created by fuzhipeng on 2019/3/14.
 *
 * miles 让参考：CommunicationAdapter
 */

//notifyItemChanged(int position, @Nullable Object payload)
//notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload)
// 为什么是List呢我怀疑 如果临近时间(16ms)内对某个对象notifyItemChanged change多个 并带payload会导致下次更新视图16ms的时候把他们合并成list。要是我会这样做的

class ChangeObj {
    companion object {
        //为了notify方便生成对象 对单个属性更改最好弄个快捷方式的生成
        @JvmField
        val UPDATE_FOOTER_LIKE = ChangeObj().apply {
            updateFooterLike = true
        }
    }

    var updateFooterLike = false
    var updateHeaderLike = false
}



fun payloads(payloads: List<Any>) {
    //ui属性 则参考 UiPerprotyTest.kt

    if (payloads.isEmpty()) {
        //界面上一行一个方法
        updateHeader()
        //...
        updatefooter()
    }else{
        for (payload in payloads) {
            if (payload is ChangeObj) {
                if (payload.updateFooterLike) updateFooterLike()
                if (payload.updateHeaderLike) updateHeaderLike()
            }
        }
    }
}

//这里每个方法都是独立的 既都有基础的item数据 就完事了，每个方法参数里都有parObj
fun updateHeader() {
    ///其他逻辑
    updateHeaderLike()
}

fun updatefooter() {
    ///其他逻辑
    updateFooterLike()
}

fun updateHeaderLike() {}
fun updateFooterLike() {}

