package gson学习与反射.反射类型;

import gson学习与反射.gson.gsonList等测试.Person;
import gson学习与反射.反射类型.实体类.Child;

import java.lang.reflect.Type;

/**
 * Created by fuzhipeng on 2016/10/18.
 */
public class Main_ {
    public static void main(String[] args) {
//        new Sun().getClass().getGenericSuperclass();
        Child child = new Child<Short,Float,Person>();

//        TypeVariable<? extends Class<? extends Child>> a = ReflectGenericUtils.Super_.getType(child.getClass(), 0).class_Unsafe;
        Type b = ReflectGenericUtils.Super_.getType(child.getClass(),0, 0).type();
//        System.out.println(a.toString()+b);
//        Cannot cast 'sun.reflect.generics.reflectiveObjects.TypeVariableImpl' to 'java.lang.Class<?>'

    }
}
