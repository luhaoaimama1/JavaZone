package 算法.算法书籍.NFA

import 算法.算法书籍.图.有向图.Diagraph
import 算法.算法书籍.图.有向图.DirectDFS
import java.util.*
import kotlin.collections.ArrayList

class NFA(val p: String) {
    val d: Diagraph

    //构造 d
    init {
        //+1 代表完成状态
        d = Diagraph(p.length + 1)

        val symbolStack = Stack<Int>()
        for (i in 0..p.lastIndex) {
            val char = p[i]
            if (char == '(' || char == '|') {
                symbolStack.push(i)
            }

            var left = i
            //如果碰到） 检查 （|）
            if (char == ')') {
                val pop1 = symbolStack.pop()
                if (p[pop1] == '|') {
                    val pop2 = symbolStack.pop()
                    d.addEdge(pop1, i)
                    d.addEdge(pop2, pop1 + 1)
                    left = pop2
                } else
                    left = pop1
            }
            //如果不是） 并且下一个字符是*   Logic2
            if (i < p.lastIndex && p[i + 1] == '*') {
                d.addEdge(left, i + 1)
                d.addEdge(i + 1, left)
            }

            //如果碰到（*）添加下一条边
            if (char == '(' || char == '*' || char == ')') {
                d.addEdge(i, i + 1)
            }
        }
    }


    fun recognize(s: String): Boolean {
        val maybeStateList = ArrayList<Int>()
        //第一个字符可能的状态 收集起来
        var dfs = DirectDFS(d, 0)
        for (i in 0 until d.v) {
            if (dfs.marked(i)) maybeStateList.add(i)
        }

        //内容整体扫描识别
        for (i in 0..s.lastIndex) {
            val matchList = ArrayList<Int>()
            //先判断和背包中的是否有相同的
            for (j in 0..maybeStateList.lastIndex) {
                //tip:忘记了 如果他匹配了完成的状态 i+1就越界了 。所以 这时候就不管继续下面就好了
                val stateIndex = maybeStateList[j]
                if (stateIndex == p.length) continue

                //模式串如果是.的话就代表任意字符了
                if (p[stateIndex] == s[i] || p[stateIndex] == '.') {
                    matchList.add(stateIndex + 1)//达到下一个状态
                }
            }

            //匹配当前字符发现没有找到一种可能的状态那么就说明已经匹配失败了
            if (matchList.isEmpty()) return false

            maybeStateList.clear()
            //收集 下一个字符可能的状态  tip:即使这里收集到可完成状态也要把整体内容全部扫描完毕。因为我们要做是对整个内容是否匹配p.而不是包含p
            dfs = DirectDFS(d, matchList)
            for (j in 0 until d.v) {
                if (dfs.marked(j)) maybeStateList.add(j)
            }
        }

        //扫描完毕
        //如果最后一个字符的状态 是可达完成状态的话 代表识别成功
        return dfs.marked(p.length)
    }
}