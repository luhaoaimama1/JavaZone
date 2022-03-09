package ktshare.first.clas

/**
 * 接口语法结构：
 * interface 接口名{
 *  ...
 * }
 */
interface Interface1 {
    fun onBack()
}

interface Interface2 {
    fun onBack2()
}

/**
 * 类语法结构：
 * :类似java的继承可是类/接口
 * ,则是分割声明的多个接口
 *
 * class 类名:类/接口,接口1....{
 *   companion object { //静态的方法或者属性声明的地方
 *   }
 *   属性
 *   方法
 *   ...
 * }
 */
const  val CONSTANT_FIELD_C="a"

class BaseClasGrammar : Interface1, Interface2 {
    companion object { //类的静属性与方法
        const val CONSTANT_FIELD = "常量"// const声明
        val CONSTANT_FIELD_V2 = "常量"// const声明
        var variableFieldStatic: String = "variableFieldStatic" //可变属性

        @JvmStatic
        fun addStatic(a: Int, b: Int): Int {
            val c = a + b
            return c
        }

    }

    /**
     * 属性语法结构：
     * var:代表可变属性
     * val:代表不可变的属性
     * :代表属性的类型
     * = 则是赋值符号
     * var/val 属性名 : 类型 = 属性值
     */
    var variableField: String = "" //可变属性
    val field: String = ""  //不可变的属性

    constructor()//默认构造器
    constructor(variableField: String) { //构造器2
        this.variableField = variableField
    }


    /**
     * fun用来声明这个是方法
     *
     * 返回类型: 没有返回 类型则是Unit 则不用写return
     * fun 方法名(参数名:参数类型,...):返回类型{
     *   ...
     *   return //返回值类型Unit的话可以省略
     * }
     */
    fun add(a: Int, b: Int): Int {
        val c = a + b
        return c
    }

    fun addNoReturn(a: Int, b: Int): Unit {
        val c = a + b
    }

    override fun onBack() {
        TODO("Not yet implemented")
    }

    override fun onBack2() {
        TODO("Not yet implemented")
    }
}
