package 正则pattern

fun main() {
//    val s = "邀请码【AW3Y9M】8um:/_NWQ5MWQwZWQ1NzBkZjNkOGVkMDAwY2I5XzIyMi4yMTIuOTAuMTM1XzE2MzU0ODgwNzMxOTdfV0xEeUo=_\$\$"
//    val s = "8um:/_NWQ5MWQwZWQ1NzBkZjNkOGVkMDAwY2I5XzIyMi4yMTIuOTAuMTM1XzE2MzU0ODgwNzMxOTdfV0xEeUo=_\$\$"
//    val s = ""
    val s = " ".trim()

    if(s.isEmpty()){
        println("空1")
        return
    }

    val s1 = s.split("8um")[0]
    println(s.subSequence(s1.length,s.length))

//    if(s.contains("8um")){
//
//    }
}