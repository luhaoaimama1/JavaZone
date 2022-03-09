package gson学习与反射.注解;

import java.lang.reflect.Field;

public class ArgmentTest {
    @Argument()
    int abc;

    public static void main(String[] args) throws NoSuchFieldException {
        ArgmentTest argmentTest = new ArgmentTest();
        Field abc1 = argmentTest.getClass().getDeclaredField("abc");
        abc1.setAccessible(true);

        System.out.println(abc1.getAnnotation(Argument.class).value());
    }
}
