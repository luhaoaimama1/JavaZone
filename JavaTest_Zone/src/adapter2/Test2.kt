package adapter2

import adapter.ViewStyleDefault
import adapter.ViewStyleOBJ


/**
 * Created by fuzhipeng on 2018/9/5.
 */

fun main(args: Array<String>) {
    ADD(args)
    removeContent(args)
    removePos()
    ADD_OR_CHANGE(args)
}

private fun removePos() {
    val adapter = HFListHistory2<String>()
    adapter.styleExtra = styleExtra
     arrayList(adapter)
    val list = ArrayList<String>()
    for (i in 0..4) {
        val content = "insert one item!+${i}"
        list.add(content)
    }
    list.add("header1_1")
    adapter.add(list)
    adapter.remove(1,1)
    print("")
}

var styleExtra = object : ViewStyleDefault<String>() {
    override fun generateViewStyleOBJ( item: String): ViewStyleOBJ? {
        val viewStyle = when (item) {
            "header1_1" -> 3
            "header1_2" -> 3
            "header2" -> 4
            "header3_1" -> 5
            "header3_2" -> 5
            "footer1_1" -> 6
            "footer1_2" -> 6
            "footer2" -> 7
            "footer3_1" -> 8
            "footer3_2" -> 8
            else -> -1
        }
        return ViewStyleOBJ().viewStyle(viewStyle)
    }

    override fun getItemViewType(position: Int, itemConfig: ViewStyleOBJ) {
    }
}

fun removeContent(args: Array<String>) {
    val adapter = HFListHistory2<String>()
    adapter.styleExtra = styleExtra
    val list = arrayList(adapter)
    adapter.add(list)
    adapter.clearFooterDatas()
    adapter.clearHeaderDatas()
    adapter.clearContentDatas()

    val notifyHistoryAdapter = ArrayList<HFListHistory2.Operate>()
    notifyHistoryAdapter.add(adapter.notifyHistory.get(adapter.notifyHistory.size - 3))
    notifyHistoryAdapter.add(adapter.notifyHistory.get(adapter.notifyHistory.size - 2))
    notifyHistoryAdapter.add(adapter.notifyHistory.get(adapter.notifyHistory.size - 1))

    val notifyHistory = ArrayList<HFListHistory2.Operate>()
    val operateClearFooter = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 8
            size = 3
            mode = HFListHistory2.Operate.MMOde.REMOVE
        }
    }
    notifyHistory.add(operateClearFooter)

    val operateClearHeader = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 0
            size = 3
            mode = HFListHistory2.Operate.MMOde.REMOVE
        }
    }
    notifyHistory.add(operateClearHeader)

    val operateClearContent = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 0
            size = 5
            mode = HFListHistory2.Operate.MMOde.REMOVE
        }
    }
    notifyHistory.add(operateClearContent)

    if (equalsContent(notifyHistory, notifyHistoryAdapter)) println("clear 操作正确")
    else throw IllegalStateException("clear 操作错误")

}

fun ADD_OR_CHANGE(args: Array<String>) {
    val adapter = HFListHistory2<String>()
    adapter.styleExtra = styleExtra
    val list = arrayList(adapter)
    adapter.add(list)

    // 判定是否一直
    var notifyHistory = ArrayList<HFListHistory2.Operate>()
    val operate = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 0
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate)

    val operate2 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 1
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate2)

    val operate3 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 1
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate3)

    val operate4 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 2
            mode = HFListHistory2.Operate.MMOde.CHANGE
        }
    }
    notifyHistory.add(operate4)

    val operate5 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 0
            mode = HFListHistory2.Operate.MMOde.CHANGE
        }
    }

    notifyHistory.add(operate5)

    //底部操作
    val operate6 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 3
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate6)

    val operate7 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 3
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate7)

    val operate8 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 4
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate8)

    val operate9 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 3
            mode = HFListHistory2.Operate.MMOde.CHANGE
        }
    }

    notifyHistory.add(operate9)

    val operate10 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 5
            mode = HFListHistory2.Operate.MMOde.CHANGE
        }
    }

    notifyHistory.add(operate10)

    val operate11 = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 3
            size = 5
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate11)

    if (equalsContent(notifyHistory, adapter.notifyHistory)) println("ADD_OR_CHANGE 添加 操作正确")
    else throw IllegalStateException("ADD_OR_CHANGE 添加 操作错误")

    //删除操作
    adapter.remove(1, 9)
    val operate12 = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 1
            size = 9
            mode = HFListHistory2.Operate.MMOde.REMOVE
        }
    }
    notifyHistory.add(operate12)

    if (equalsContent(notifyHistory, adapter.notifyHistory)) println("ADD_OR_CHANGE 删除 操作正确")
    else throw IllegalStateException("ADD_OR_CHANGE 删除 操作错误")//这里仅省 header1和footer1就是对的了
}

private fun arrayList(adapter: HFListHistory2<String>): ArrayList<String> {
    adapter.headerViewStyleOrder.add(3)
    adapter.headerViewStyleOrder.add(4)
    adapter.headerViewStyleOrder.add(5)

//    adapter.footerViewStyleOrder.add(6)
//    adapter.footerViewStyleOrder.add(7)
//    adapter.footerViewStyleOrder.add(8)
    adapter.footerViewStyleOrder.add(8)
    adapter.footerViewStyleOrder.add(7)
    adapter.footerViewStyleOrder.add(6)

    val list = ArrayList<String>()
    for (i in 0..4) {
        val content = "insert one item!+${i}"
        list.add(content)
    }
    list.add("header1_1")
    list.add("header3_1")
    list.add("header2")
    list.add("header3_2")
    list.add("header1_2")

    list.add("footer1_1")
    list.add("footer3_1")
    list.add("footer2")
    list.add("footer3_2")
    list.add("footer1_2")
    return list
}

fun equalsContent(notifyHistory: ArrayList<HFListHistory2.Operate>, otherNotifyHistory: ArrayList<HFListHistory2.Operate>): Boolean {
    if (notifyHistory.size != otherNotifyHistory.size) return false
    notifyHistory.forEachIndexed { index, operate ->
        val otherOperate = otherNotifyHistory.get(index)
        if (operate.move != null && otherOperate.move != null) {
            if (operate.move!!.pos == otherOperate.move!!.pos && operate.move!!.toPos == otherOperate.move!!.toPos) {
            } else {
                return false;
            }
        }
        if (operate.range != null && otherOperate.range != null) {
            if (operate.range!!.pos == otherOperate.range!!.pos
                && operate.range!!.size == otherOperate.range!!.size
                && operate.range!!.mode.name == otherOperate.range!!.mode.name
            ) {
            } else {
                return false;
            }
        }
        if (operate.single != null && otherOperate.single != null) {
            if (operate.single!!.index_ == otherOperate.single!!.index_
                && operate.single!!.mode.name == otherOperate.single!!.mode.name
            ) {
            } else {
                return false;
            }
        }
    }
    return true;
}

fun ADD(args: Array<String>) {
    println("----------------------------")

    val adapter = HFListHistory2<String>()
    adapter.footerMode = HFMode.ADD
    adapter.headerMode = HFMode.ADD
    adapter.styleExtra = styleExtra
    val list = arrayList(adapter)
    adapter.add(list)

    // 头部操作
    var notifyHistory = ArrayList<HFListHistory2.Operate>()
    val operate = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 0
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate)

    val operate2 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 1
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate2)

    val operate3 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 1
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate3)

    val operate4 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 3
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }
    notifyHistory.add(operate4)

    val operate5 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 1
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate5)

    //底部操作
    val operate6 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 5
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate6)

    val operate7 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 5
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate7)

    val operate8 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 6
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate8)

    val operate9 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 6
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate9)

    val operate10 = HFListHistory2.Operate().apply {
        single = HFListHistory2.Operate.Single().apply {
            index_ = 9
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate10)

    val operate11 = HFListHistory2.Operate().apply {
        range = HFListHistory2.Operate.Range().apply {
            this@apply.pos = 5
            size = 5
            mode = HFListHistory2.Operate.MMOde.ADD
        }
    }

    notifyHistory.add(operate11)

    if (equalsContent(notifyHistory, adapter.notifyHistory)) println("ADD 添加 操作正确")
    else throw IllegalStateException("ADD 添加 操作错误")
}