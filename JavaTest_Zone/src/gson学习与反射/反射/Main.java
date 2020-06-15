package gson学习与反射.反射;

import java.lang.reflect.Field;

import Java.Zone.Utils.FieldTypeUtils;
import Java.Zone.Utils.FieldTypeUtils.FieldTypeInfo;
import Java.Zone.Utils.TypeUtils;
import gson学习与反射.entity.TopRef;

public class Main {
    private final int reflect = 1;

    public static void main(String[] args) {
        for (Field item : TopRef.class.getDeclaredFields()) {
            FieldTypeInfo temp = FieldTypeUtils.getFieldTypeInfo(item);
            System.out.print(item.getName() + "____" + TypeUtils.isPrimitiveWrap(temp.rawType));
            System.out.println("\t isPrimitive____" + TypeUtils.isPrimitive(temp.rawType));
        }

    }
}
