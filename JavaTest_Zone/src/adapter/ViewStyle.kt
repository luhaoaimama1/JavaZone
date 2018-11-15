
package adapter

/**
 *[2018] by Zone
 *
 * 间隔就不要弄了吧  因为那个和布局关系很大
 */
interface ViewStyle<T> {
    fun generateViewStyleOBJ(item: T): ViewStyleOBJ?
    fun getItemViewType(position: Int, itemConfig: ViewStyleOBJ)
}

open class ViewStyleDefault<T> : ViewStyle<T> {
    override fun getItemViewType(position: Int, itemConfig: ViewStyleOBJ) {}

    override fun generateViewStyleOBJ(item: T): ViewStyleOBJ? {
        return null
    }
}

enum class Part { HEADER, FOOTER, CONTENT }

class ViewStyleOBJ {
    var viewStyle: Int = -1
    var isPlaceholder: Boolean = false
    var isSticky: Boolean = false
    var part: Part = Part.CONTENT
    internal var isGenerate = false

    fun part(part: Part): ViewStyleOBJ {
        this@ViewStyleOBJ.part = part
        return this
    }

    fun viewStyle(viewStyle: Int): ViewStyleOBJ {
        this@ViewStyleOBJ.viewStyle = viewStyle
        return this
    }

    fun isPlaceholder(isPlaceholder: Boolean): ViewStyleOBJ {
        this@ViewStyleOBJ.isPlaceholder = isPlaceholder
        return this
    }

    fun isSticky(isSticky: Boolean): ViewStyleOBJ {
        this@ViewStyleOBJ.isSticky = isSticky
        return this
    }

    internal fun setNowValue(other: ViewStyleOBJ) {
        other.viewStyle = viewStyle
        other.isPlaceholder = isPlaceholder
        other.isSticky = isSticky
        other.part = part
        other.isGenerate = true
    }
}