package ktshare.second.method_

open class Parent{
    fun add(){

    }

    open fun addOpen(){

    }
}


class Child: Parent() {
    override fun addOpen() {
        super.addOpen()
    }
}