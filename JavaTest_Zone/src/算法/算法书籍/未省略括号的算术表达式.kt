package 算法.算法书籍

import java.lang.IllegalStateException
import java.util.*

object 未省略括号的算术表达式 {
    @JvmStatic
    fun main(args: Array<String>) { //遇到）就会计算前面三个的值，
        //因为是倒叙计算 就是 后进先出 存取
        //为什么用两个stack呢 因为泛型不同。
        val stack = Stack<Double>()
        val stackSymbol = Stack<Char>()
        val s = "(1+((2+3)*(4*5)))"
        for (i in 0 until s.length) {
            val indexChar = s[i]
            when (indexChar) {
                '*', '/', '+', '-' -> stackSymbol.push(indexChar)
                ')' -> {
                    val valueRight = stack.pop()
                    val valueLeft = stack.pop()
                    val symbol = stackSymbol.pop()
                    val any = when (symbol) {
                        '*' -> valueLeft * valueRight
                        '/' -> valueLeft/valueRight
                        '+' -> valueLeft+valueRight
                        '-' -> valueLeft-valueRight
                        else -> throw IllegalStateException("不支持符号！")
                    }
                    stack.push(any)
                }
                //跳过
                '(' -> {
                    println("跳过！")
                }
                else -> stack.push(indexChar.toString().toDouble())
            }
        }
        println(stack.pop())
    }
}