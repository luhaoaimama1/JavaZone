package 泛型.继承链式测试.kotlin

/**
 * Created by fuzhipeng on 2018/8/21.
 */

class HolderEx2 {
    fun Holder.put2(key: String, value: String): Holder {
        this.map.put(key, value)
        return this
    }

    fun Holder.adds2(): Holder {
        for (i in 1..10) {
            this.map.put("$i", "value:$i")
        }
        return this
    }
}

