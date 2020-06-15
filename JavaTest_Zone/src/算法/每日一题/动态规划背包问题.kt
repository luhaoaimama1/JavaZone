package 算法.每日一题

//编号 1 2 3 4
//体积 2 3 4 5
//价值 3 4 5 6

object 动态规划背包问题 {
    var objSize = 4
    var vs = arrayOf(2, 3, 4, 5)
    var moneys = arrayOf(3, 4, 5, 6)


    @JvmStatic
    fun main(args: Array<String>) {
        var space = 8

        val dp = Array(space) { 0 }
        val sp = Array(space) { -1 }

        for (i in 1 until space) {
            for (k in 0..vs.lastIndex) {
                if (i - vs[k] < 0) break
                val sv = i - vs[k]
                val value = moneys[k] + dp[sv]
                if (value > dp[i]) {
                    dp[i] = value
                    sp[i] = k //记录的 物品序号
                }
            }
        }

        println(dp[space - 1])

        var index = space - 1
        while (sp[index] >= 0) {
            println("分割：+${vs[sp[index]]} \t ${moneys[sp[index]]}")
            index -= vs[sp[index]]
        }
    }
}