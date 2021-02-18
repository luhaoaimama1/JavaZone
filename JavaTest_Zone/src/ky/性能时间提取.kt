package ky

fun main() {
    val str = "10-15 19:10:31.135 25349 25349 I boost-flush: application 友盟初始化耗时： 406\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: application 其他项初始化耗时： 52\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: application 切换到Launcher耗时：295\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: Launcher 启动耗时：39\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: Launcher 切换到Main耗时： 749\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: main查询配置信息数据库耗时：90\n" +
            "10-15 19:10:31.135 25349 25349 I boost-flush: 切换到主进程耗时：191\n" +
            "10-15 19:10:31.136 25349 25349 I boost-flush: 设置main contentView耗时：216\n" +
            "10-15 19:10:31.136 25349 25349 I boost-flush: 音乐feed数据设置完毕耗时：879\n" +
            "10-15 19:10:31.136 25349 25349 I boost-flush: 音乐feed数据显示完毕耗时：966\n" +
            "10-15 19:10:31.136 25349 25349 I boost-flush: 总启动时长：3897"

    val split = str.split("\n")
    for (s in split) {
        val split1 = s.split("：")
        val s1 = split1[split1.size - 1]

        println(s1.trim())
    }
}