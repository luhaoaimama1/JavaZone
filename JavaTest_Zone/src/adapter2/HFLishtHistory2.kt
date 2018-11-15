package adapter2

/**
 *[2018/9/5] by Zone
 */
open class HFListHistory2<T> : HFList2<T>() {
    var notifyHistory = ArrayList<Operate>()

    class Operate {
        var range: Range? = null
        var move: Move? = null
        var single: Single? = null

        class Range {
            var pos = 0
            var size = 0
            var mode = MMOde.ADD

        }

        class Move {
            var pos = 0
            var toPos = 0
        }

        class Single {
            var index_ = 0
            var mode = MMOde.ADD
        }

        enum class MMOde {
            ADD, CHANGE, REMOVE
        }
    }

    override fun notifyItemChangedInner(index: Int) {
        printlnFun("notifyItemChangedInner：index:$index")
        val operate = Operate().apply {
            single = Operate.Single().apply {
                index_ = index
                mode = Operate.MMOde.CHANGE
            }
        }
        notifyHistory.add(operate)
    }

    override fun notifyItemMovedInner(fromPosition: Int, toPosition: Int) {
        printlnFun("notifyItemMovedInner：fromPosition:$fromPosition , toPosition:$toPosition")
        val operate = Operate().apply {
            move = Operate.Move().apply {
                pos = fromPosition
                toPos = toPosition
            }
        }
        notifyHistory.add(operate)

    }

    // 调用内部通知  这样覆盖方法即可

    override fun notifyItemRangeRemovedInner(positionStart: Int, itemCount: Int) {
        printlnFun("notifyItemRangeRemovedInner：positionStart:$positionStart , itemCount:$itemCount")
        val operate = Operate().apply {
            range = Operate.Range().apply {
                this@apply.pos = positionStart
                size = itemCount
                mode = Operate.MMOde.REMOVE
            }
        }
        notifyHistory.add(operate)

    }

    override fun notifyItemInsertedInner(position: Int) {
        printlnFun("notifyItemInsertedInner：position:" + position)
        val operate = Operate().apply {
            single = Operate.Single().apply {
                index_ = position
                mode = Operate.MMOde.ADD
            }
        }
        notifyHistory.add(operate)
    }

    override fun notifyItemRangeInsertedInner(positionStart: Int, itemCount: Int) {
        printlnFun("notifyItemRangeInsertedInner：positionStart:$positionStart , itemCount:$itemCount")
        val operate = Operate().apply {
            range = Operate.Range().apply {
                this@apply.pos = positionStart
                size = itemCount
                mode = Operate.MMOde.ADD
            }
        }
        notifyHistory.add(operate)
    }

}