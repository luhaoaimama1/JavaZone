package a_b_改善


fun main(args: Array<String>) {

    println("first:"+margin(12.5f,129f,474f))
    println("first:"+margin(12.5f,167f,474f))
    println("first:"+margin(12.5f,191f,474f))
    println("first:"+margin(12.5f,229f,474f))
    println("first:"+margin(12.5f,287f,474f))
    println("first:"+margin(12.5f,327f,474f))
    println("first:"+margin(12.5f,387f,474f))
}

fun margin(margin: Float, length: Float, total: Float) = (length + margin) / (margin + total)
