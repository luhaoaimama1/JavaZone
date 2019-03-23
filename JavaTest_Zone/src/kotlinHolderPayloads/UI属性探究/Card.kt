package kotlinHolderPayloads.UI属性探究

/**
 * Created by fuzhipeng on 2019/3/22.
 */
class Card {
    val map: HashMapNew by lazy {
        HashMapNew()
    }
}

class HashMapNew : HashMap<Class<*>, Any>() {

    /**
     * 如果第一次get是null。会生成一个放进map中
     */
    fun <T> getNonNull(key: Class<T>): T {
        var value = super.get(key)
        if (value == null) {
            value = key.newInstance()
            put(key, value!!)
        }
        return value as T
    }
}