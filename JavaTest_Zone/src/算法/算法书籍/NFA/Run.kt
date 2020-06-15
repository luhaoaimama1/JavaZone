package 算法.算法书籍.NFA

fun main(args: Array<String>) {
    println("pattern ((A*B|AC)D) recognize DAB :edu ${edu.princeton.cs.algs4.NFA("((A*B|AC)D)").recognizes("DAB")}")
    println("pattern ((A*B|AC)D) recognize DAB :local ${算法.算法书籍.NFA.NFA("((A*B|AC)D)").recognize("DAB")}")


    println("pattern ((A*B|AC)D) recognize ABD :edu ${edu.princeton.cs.algs4.NFA("((A*B|AC)D)").recognizes("ABD")}")
    println("pattern ((A*B|AC)D) recognize ABD :local ${算法.算法书籍.NFA.NFA("((A*B|AC)D)").recognize("ABD")}")
}