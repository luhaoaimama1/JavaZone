package kotlinHolderPayloads


/**
 * Created by fuzhipeng on 2019/3/14.
 *
 * miles 让参考：CommunicationAdapter
 */

//notifyItemChanged(int position, @Nullable Object payload)
//notifyItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload)
// 为什么是List呢我怀疑 如果临近时间(16ms)内对某个对象notifyItemChanged change多个 并带payload会导致下次更新视图16ms的时候把他们合并成list。要是我会这样做的

fun payloads(payloads: List<Any>) {
    //为了计算某个例如 是否有商品的属性 防止第二次计算
    val parObj = ParseObj()

    if (payloads.isEmpty()) {
        //界面上一行一个方法
        updateHeader()
        //...
        updatefooter()
    }

    //版本1
    for (payload in payloads) {
        if (payload is ChangeObj) {
            if (payload.updateFooterLike) updateFooterLike()
            if (payload.updateHeaderLike) updateHeaderLike()
        }
    }

    //版本2： 省略了一些属性用HashSet弄。保证不重复 。其次不用判断所有属性。例如上个方法需要对两个属性进行判断。而set则可能只有一个
    for (payload in payloads) {
        if (payload is ChangeObjV2) {
            payload.set2.forEach {
                when (it) {
                    ChangeObjV2Constant.updateFooterLike -> updateFooterLike()
                    ChangeObjV2Constant.updateHeaderLike -> updateHeader()
                    else -> {
                    }
                }
            }
        }
    }
}


//这里每个方法都是独立的 既都有基础的item数据 就完事了，每个方法参数里都有parObj
fun updateHeaderLike() {}

fun updateFooterLike() {}
fun updateHeader() {
    ///其他逻辑
    updateHeaderLike()
}

fun updatefooter() {
    ///其他逻辑
    updateFooterLike()
}

//版本1
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

//版本2： 省略了一些属性用HashSet弄。保证不重复
class ChangeObjV2 {
    companion object {
        //为了notify方便生成对象 对单个属性更改最好弄个快捷方式的生成
        @JvmField
        val UPDATE_FOOTER_LIKE = ChangeObjV2().apply {
            set2.add(ChangeObjV2Constant.updateFooterLike)
        }

    }

    val set2 = HashSet<Int>()
}
//版本2
class ChangeObjV2Constant{
    companion object {
        @JvmField
        val updateFooterLike = 1
        @JvmField
        val updateHeaderLike = 2
    }
}

//Ps: 这个属性放到,实体类里如何？ 如果有payloads,就需要对Item里的ParseObj重置。既重新计算。
//（对已计算的数据 重复滚动仅仅计算一次。直接显示 提高效率）
// 考虑实体类可能有些属性更改了。但是notiyChange仅仅更新评论了呢？这个是错误的。因为滚动过去在滚动回来一样界面会刷新到正确的部分。

class ParseObj {
    private var like = -1
    fun getLike(obj: Any): Int {
        return if (like == -1) {
            like
        } else {
            //解析逻辑
            obj.hashCode() //暂时代替
        }
    }
}

