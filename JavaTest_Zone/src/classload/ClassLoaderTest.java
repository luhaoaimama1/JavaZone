package classload;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by fuzhipeng on 2017/3/9.
 */
public class ClassLoaderTest {
    /*
    加载统一路径下的类,用其他的类加载器加载。

    --->就是需要流 ,通过defineClass生成初始化类,然后newInstance生成实例。
    一些细节可以看DynamicLoader中的MemoryClassLoader的add方法

    疑问,
    1.如果依赖特定的扩展包 需要继承特定的classLoader吗?
    2.如果类已经 加载过了,那么应该在哪里存储 下一次去验证是否加载过呢?

    jdk 1.2之后 已经不提倡用户覆盖loadClass方法了,而是应当在自己的类加载逻辑写到findClass()中。因为loadClass()方法如果加载失败就会调用
    自己的findClass()去加载。这样就能保证 写出来的类加载器是符合双亲委派模式的

    下面的默认构造器,大概就是解决了1的问题  要在父亲是AppClassLoader的基础上 加载自己的类。不然会破坏双亲的
     protected ClassLoader() {
        this(checkCreateClassLoader(), getSystemClassLoader());
    }
     */
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
//                Class<?> c = findLoadedClass(name);
//                if(c!=null)
//                System.out.println("有有有有");
//                else
//                    System.out.println("没有哈");
                return super.loadClass(name);
            }


            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
//                Class<?> c = findLoadedClass(name);
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if (is == null)
                    return super.findClass(name);
                try {
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return super.findClass(name);
            }
        };

        //应用程序类加载器 加载ClassPath目录上的class 可以通过getSystemClassLoader()方法获得所以叫 appClassLoader;
        System.out.println(ClassLoader.getSystemClassLoader());

        Object obj = myLoader.loadClass("classload.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
