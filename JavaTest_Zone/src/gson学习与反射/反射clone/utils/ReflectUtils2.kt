package gson学习与反射.反射clone.utils

import com.google.gson.Gson

import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType
import java.util.ArrayList
import java.util.HashMap

object ReflectUtils2 {
    fun <T : Any> clone(data: T): T? {
        return clone(data, false)
    }

    /**
     * 暂时支持  基础类型 基础类型的封装类 list Map
     * static 的不赋值
     * TODO final 暂时不会管   异常再说
     * 不支持枚丿 不过不会报错~ 剩下不支持的会报锿
     * @param data
     * *
     * @return
     */
    fun <T : Any> clone(data: T, log: Boolean): T? {
        //是基本类型与其封装类
        if (data.javaClass.isPrimitive() || isPrimitiveWrap(data.javaClass))
            return data
        //是集合类型 list Map
        if (List::class.java.isAssignableFrom(data.javaClass)) {
            val list = data as List<Any>
            val dataClone = ArrayList<Any>()
            for (i in list.indices) {
                val obj = clone(list[i])
                if (obj != null) dataClone.add(obj)
            }

            return dataClone as T
        }
        if (Map::class.java.isAssignableFrom(data.javaClass)) {
            val dataMap = data as Map<Any, Any>
            val dataClone = HashMap<Any, Any>()
            for ((key, value) in dataMap) {
                val key = clone(key)
                val value = clone(value)
                if (key != null && value != null) dataClone.put(key, value)
            }
            return dataClone as T
        }
        //是类
        return cloneClass(data, log)
    }

    private fun <T : Any> cloneClass(data: T, log: Boolean): T? {
        var dataClone: T? = null
        try {
            dataClone = data.javaClass.newInstance() as T
            val fields = data.javaClass.getDeclaredFields()
            for (field in fields) {
                val fieldClass = field.type
                val fieldClass2 = field.genericType
                if (log) {
                    //得到类型
                    println("getGenericType:" + fieldClass2)
                    println("getType:" + fieldClass)
                    println("field:" + field.name)
                    println("isStatic:" + Modifier.isStatic(field.modifiers))
                    println("isEnum:" + fieldClass.isEnum)
                }
                //不是静态  就赋值
                if (!Modifier.isStatic(field.modifiers)) {
                    field.isAccessible = true
                    if (!fieldClass.isEnum) {
                        if (fieldClass.isPrimitive || isPrimitiveWrap(fieldClass)) {
                            //判断是什么类垿  如果是基本类垿 就直接赋值
                            field.set(dataClone, field.get(data))
                        } else if (List::class.java.isAssignableFrom(fieldClass)) {
                            val listClone = ArrayList<Any>()
                            val list = field.get(data) as List<Any>
                            for (i in list.indices) {
                                val obj = clone(list[i])
                                if (obj != null) listClone.add(obj)
                            }
                            field.set(dataClone, listClone)
                        } else if (Map::class.java.isAssignableFrom(fieldClass)) {
//                            val fieldClass2Params = fieldClass2 as ParameterizedType
//                            val types = fieldClass2Params.actualTypeArguments
                            val map = field.get(data) as Map<Any, Any>
                            val mapClone = HashMap<Any, Any>()
                            for ((key, value) in map) {
                                val key = clone(key)
                                val value = clone(value)
                                if (key != null && value != null) mapClone.put(key, value)
                            }
                            field.set(dataClone, mapClone)
                        } else {
                            //不是  的话 就继续clone
                            field.set(dataClone, clone(field.get(data)))
                        }
                    } else {
                        field.set(dataClone, field.get(data))
                    }
                }
            }
        } catch (e: InstantiationException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }

        return dataClone
    }

    fun <T> toStringGson(data: T) {
        println(Gson().toJson(data))
    }


    //int, double, float, long, short, boolean, byte, char＿ void.也是有这个的
    fun isPrimitiveWrap(clas: Class<*>): Boolean {
        if (Int::class.java.isAssignableFrom(clas))
            return true
        if (Double::class.java.isAssignableFrom(clas))
            return true
        if (Float::class.java.isAssignableFrom(clas))
            return true
        if (Long::class.java.isAssignableFrom(clas))
            return true
        if (Short::class.java.isAssignableFrom(clas))
            return true
        if (Boolean::class.java.isAssignableFrom(clas))
            return true
        if (Byte::class.java.isAssignableFrom(clas))
            return true
        if (Char::class.java.isAssignableFrom(clas))
            return true
        if (Void::class.java.isAssignableFrom(clas))
            return true
        if (String::class.java.isAssignableFrom(clas))
            return true
        return false
    }

}
