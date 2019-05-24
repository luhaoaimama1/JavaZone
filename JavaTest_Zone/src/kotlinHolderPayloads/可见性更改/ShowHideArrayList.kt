/*
 * Copyright (c) 2015-2019 BiliBili Inc.
 */

package com.bilibili.bplus.followingcard.helper.sys


/*
 * [19-4-29] by Zone
 */
fun main(args: Array<String>) {
    val arrlist = ShowHideArrayList<String>()
    arrlist.visibilityListener=object : ShowHideArrayList.VisibilityListener<String> {
        override fun hide(data: String) {
            println("hide:$data")
        }

        override fun show(data: String) {
            println("show:$data")
        }
    }
    val a1="1"
    val a2="2"
    val a3="3"
    val a4="4"
    arrlist.beginLoop()
    arrlist.loopShow(a1)
    arrlist.loopShow(a2)
    arrlist.loopShow(a3)
    arrlist.loopShow(a4)
    arrlist.endLoop()

    println("预期结果——show: 1，2，3，4")

    arrlist.beginLoop()
    arrlist.loopShow(a1)
    arrlist.loopShow(a2)
    arrlist.loopShow(a4)
    arrlist.endLoop()
    println("预期结果——hide:3")

    val a5="5"
    arrlist.beginLoop()
    arrlist.loopShow(a1)
    arrlist.loopShow(a2)
    arrlist.loopShow(a5)
    arrlist.endLoop()
    println("预期结果——show:5,hide:4")

}
class ShowHideArrayList<E> : ArrayList<E>() {
    var removeVisiIndex = -1
    var visibilityListener: VisibilityListener<E>? = null

    fun beginLoop() {
        // [A.B..removeVisiIndex] 这时候来个可见B 序列变为[A...removeVisiIndex,B],要移除的就是A...removeVisiIndex
        // 总结 就是  [0,removeVisiIndex] 要移除,(removeVisiIndex,size)之后的是可见的数据
        removeVisiIndex = size - 1
    }

    fun loopShow(data: E) {
        val index = indexOf(data)
        if (index != -1) {
            if (index <= removeVisiIndex) {
                //数据有,但是小于限定移除的index,把数据移动到最后的为止,并把index向前移动
                removeVisiIndex--
                remove(data)
                add(data)
            } else {
                // 如果数据 有,并且是限定移除的index之后 则不管
            }
        }else{
            add(data)
            visibilityListener?.show(data)
        }
    }

    fun endLoop() {
        //倒序移除  可见列表中的 不可见数据
        for (index in removeVisiIndex downTo 0) {
            //可以触发不可见
            val element = get(index)
            visibilityListener?.hide(element)
            remove(element)
        }
    }

    interface VisibilityListener<E> {
        fun show(data: E)
        fun hide(data: E)
    }
}