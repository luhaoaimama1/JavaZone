package classload;

import gson学习与反射.反射类型.实体类.Child;
import org.junit.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class DynamicLoaderTestCase {
    private static String javaSrc = "public class TestClass{" +
            "public void sayHello(String msg) {" +
            "System.out.printf(\"Hello %s! This message from a Java String.%n\",msg);" +
            "}" +
            "public int add(int a,int b){" +
            "return a+b;" +
            "}" +
            "}";
    private static String javaSrc2 = "package classload;" +
            "import gson学习与反射.gson.gsonList等测试.Person;\n" +
            "import Child;\n" +
            "import Parent;" +
            "public class TestClass extends Child<Short,Float,Person>{" +
            "}";

    public static void testCompile() {
        Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);

        for (Iterator<String> iterator = bytecode.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            byte[] code = bytecode.get(key);

            System.out.printf("Class: %s, Length: %d%n", key, code.length);
        }

        // Since the compiler and compiler options are different, the size of the bytes may be inconsistent.
        Assert.assertEquals(558, bytecode.get("TestClass").length);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
//        testCompile();
//        test2();
        testParent();
    }

    private static void test2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Random random = new Random();
        int a = random.nextInt(1024);
        int b = random.nextInt(1024);

        Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);
        DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader();
        Class clazz = classLoader.loadClass("TestClass");
        Object object = clazz.newInstance();
        Method method = clazz.getMethod("add", int.class, int.class);
        Object returnValue = method.invoke(object, a, b);

        Assert.assertEquals(a + b, returnValue);
        System.out.println(returnValue);
    }

    private static void testParent() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc2);
        DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader();
        classLoader.add(bytecode);
        Class clazz = classLoader.loadClass("classload.TestClass");
//        clazz.getGenericSuperclass();
        Child parent = (Child) (clazz.newInstance());




        Map<String, byte[]> bytecode2 = DynamicLoader.compile("TestClass.java", javaSrc);
        classLoader.add(bytecode2);
        Class clazz2 = classLoader.loadClass("TestClass");
        Object object = clazz2.newInstance();
        Method method = clazz2.getMethod("add", int.class, int.class);
        Object returnValue = method.invoke(object, 110, 120);

        System.out.println(parent.tag);
        System.out.println();
    }
}
