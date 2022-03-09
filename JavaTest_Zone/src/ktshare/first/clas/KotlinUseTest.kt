package ktshare.first.clas


fun main(args: Array<String>) {
    println(BaseClasGrammar::class.java)
    println(BaseClasGrammar.addStatic(1, 1))

    val baseClasGrammar = BaseClasGrammar()
    println(baseClasGrammar.add(1,1))
}