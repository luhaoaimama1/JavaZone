package gson学习与反射.反射类型

import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Type 可以直接强转成class
 * 使用范例: T result=((Class<T>) ReflectGenericUtils.getSuperGenericClass(this)).newInstance();
</T> */
class ReflectGenericUtils2 {

    //        getGenericInterfaces()
    //        getGenericSuperclass

    class Self_//---------------------------1.在本类中使用--------------------------------------
    /**
     * 在本类中使用  可以这样
     * public class Parent<M></M>, B> {
     * public String tag="Parent";
     * public Class<M> class_Unsafe;
     * public M entity;
     * public Parent(M entity,B bi) {
     * }
     * }
    </M> */
    //---------------------------2.在外部类中使用--------------------------------------
    /**
     * new Child<Short></Short>,Float,Person>(){} 有这个{} 相当于匿名类继承 本类
     * 所有可以获得相当于本类的参数
     * new Child<Short></Short>,Float,Person>(){}.getClass().getGenericSuperclass()
     * [Super_]
     */


    object Super_ {

        @JvmOverloads fun getType(subclass: Class<*>, superIndex: Int = 0, index: Int = 0): TypeToken_ {
            var subclass = subclass
            for (i in 0..superIndex - 1)
                subclass = subclass.superclass
            val superclass = subclass.genericSuperclass
            if (superclass !is ParameterizedType)
                return TypeToken_(superclass)
            val params = superclass.actualTypeArguments
            if (index >= params.size || index < 0)
                throw RuntimeException("Index outof bounds")
            return TypeToken_(params[index])
        }
    }


    object Interface_ {

        @JvmOverloads fun getType(subclass: Class<*>, interfaceIndex: Int = 0, typeIndex: Int = 0): TypeToken_ {
            val superclass = subclass.genericInterfaces
            val fieldClass = subclass.genericInterfaces[interfaceIndex]
            if (fieldClass !is ParameterizedType)
                return TypeToken_(fieldClass)
            val params = fieldClass.actualTypeArguments
            if (typeIndex >= params.size || typeIndex < 0)
                throw RuntimeException("Index outof bounds")
            return TypeToken_(params[typeIndex])
        }
    }

    object Field_ {

        @JvmOverloads fun getType(field: Field, index: Int = 0): TypeToken_ {
            val fieldClass = field.genericType
            if (fieldClass !is ParameterizedType)
                return TypeToken_(fieldClass)
            val params = fieldClass.actualTypeArguments
            if (index >= params.size || index < 0)
                throw RuntimeException("Index outof bounds")
            return TypeToken_(params[index])
        }
    }

    class TypeToken_(private val type: Type) {

        fun type(): Type {
            return type
        }

        fun class_Unsafe(): Class<*> {
            return type as Class<*>
        }

        fun class_(): Class<*> {
            try {
                return type as Class<*>
            } catch (e: Exception) {
                return Any::class.java
            }

        }
    }
}
