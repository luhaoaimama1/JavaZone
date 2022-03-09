package ktshare.second.field_

/**
 * var <propertyName>[: <PropertyType>] [= <property_initializer>]
[<getter>]
[<setter>]
 */
class SetGetField {
    var abc: String = "abc"
        get() {
            println("get=>${field}")
            return field
        }
        set(value) {
            println("set=>value:$value \t field:$field")
            field = value
        }


}

fun main(args: Array<String>) {
    val setGetField = SetGetField()
    setGetField.abc = "新value"
    println("final==>${setGetField.abc}")
}