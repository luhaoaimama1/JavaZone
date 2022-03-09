package Urites

object UriTest {

    val s = "http://play.rd.kaixinyf.cn/?name=haha#/game/new_walk"
    val s1 = "http://play.rd.kaixinyf.cn/#/game/new_walk?name=haha"
    val s3 = "http://play.rd.kaixinyf.cn/#/game/new_walk?name=haha&name2=haha"
    val s4 = "http://play.rd.kaixinyf.cn/?name=haha&name2=haha#/game/new_walk"

    @JvmStatic
    fun main(args: Array<String>) {


        println(getRightFulH5HashPath(s1))
        println(getRightFulH5HashPath(s))
        println(getRightFulH5HashPath(s3))
        println(getRightFulH5HashPath(s4))

//        println(removeQueryParams(s1, "name"))
//        println(removeQueryParams(s, "name"))
//        println(removeQueryParams(s3, "name"))
//        println(removeQueryParams(s4, "name"))
    }

    private fun getH5HashPath(url: String): String {
        var content = ""
        if (!url.contains("#")) {
            return url
        }
        content = url.split("#")[1]
        if (url.contains("?")) {
            content = content.split("?")[0]
        }
        return content
    }

    private fun getRightFulH5HashPath(url: String): String {
        var content = ""
        if (!url.contains("#")) {
            return content
        }
        content = url.split("#")[1]
        if (url.contains("?")) {
            content = content.split("?")[0]
        }

        return url.replace("#${content}", "")
                .plus("#${content}")
    }

//
//    private fun getQueryParameters(url: String): HashMap<String, String>? {
//        var hashMap: HashMap<String, String>? = null
//        if (url.contains("?")) {
//            hashMap = HashMap<String, String>()
//
//            var queryStr = url.split("?")[1]
//            if (queryStr.contains("#")) {
//                queryStr = url.split("#")[0]
//            }
//            val split = queryStr.split("&")
//            split.forEach {
//                if(it.contains("=")){
//
//                }
//            }
//
//        }
//        return hashMap
//    }


    @JvmStatic
    fun removeQueryParams(url: String, vararg params: String): String {
        if (!url.contains("?")) {
            return url
        }

        val query = url.split("?")[1]
                .split("#")[0]//如果查询中 有#部分 去掉
        val querys = query.split("&")
        var result = url

        var needRemoveQueryDot = true
        for (queryItem in querys) {
            val key = queryItem.split("=")[0]
            if (contains(params, key)) {
                println("queryItem:" + queryItem + "\t key:${key}")
                result = result.replace(queryItem, "")
            } else {
                needRemoveQueryDot = false
            }
        }
        if (needRemoveQueryDot) {
            result = result.replace("?", "")
        }
        return result
    }

    private fun contains(params: Array<out String>, query: String): Boolean {
        for (param in params) {
            if (query.equals(param)) {
                return true
            }
        }
        return false
    }
}