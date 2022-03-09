package a_新手

import gson学习与反射.gson.gsonList等测试.GsonUtils
import ktshare.second.cls_.User
import java.io.Serializable

data class User(var name: String, var name2:String?):Serializable
data class User2(var name: String="", var name2:String=""):Serializable

interface I{
    fun a()
}
fun main(args: Array<String>) {
    val user = User("name",null)
    val toJson = GsonUtils.toJson(user)
    val fromJson = GsonUtils.fromJson(toJson, User2::class.java)
    println()

    var a= create()
    var b= create()
    println()
}

private fun create(): I {
    return object : I {
        override fun a() {
        }
    }
}