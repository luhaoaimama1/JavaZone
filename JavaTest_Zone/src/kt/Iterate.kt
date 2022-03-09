package kt

fun main(args: Array<String>) {

    iterateSection()
    iterateFor()
    iterateForIndex()
    iterateWhile()
    println("describe${describe("Hello")}")
}

private fun iterateSection() {
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

private fun iterateWhile() {
    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

private fun iterateForIndex() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
}

private fun iterateFor() {
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        if (item == "banana") break
        println(item)
    }
}

fun describe(obj: Any): String = when (obj) {
    1 -> "One"
    "Hello" -> "Greeting"
    is Long -> "Long"
    !is String -> "Not a string"
    else -> "Unknown"
}