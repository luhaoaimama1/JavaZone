package classload;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Required JDK >= 1.6<br><br>
 * This class can help you create the Java byte code dynamically through the string and load it into memory.<br><br>
 * <p>
 * HOW TO:<br>
 * First step. <code>Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);</code><br>
 * Second step. <code>DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(bytecode);</code><br>
 * Third step. <code>Class clazz = classLoader.loadClass("TestClass");</code><br>
 * <br>
 * Then just like the normal use of the call this class can be.
 */
public class DynamicLoader {
    /**
     * auto fill in the java-name with code, return null if cannot find the public class
     *
     * @param javaSrc source code string
     * @return return the Map, the KEY means ClassName, the VALUE means bytecode.
     */
    public static Map<String, byte[]> compile(String javaSrc) {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");

        Matcher matcher = pattern.matcher(javaSrc);

        if (matcher.find())
            return compile(matcher.group(1) + ".java", javaSrc);
        return null;
    }

    /**
     * @param javaName the name of your public class,eg: <code>TestClass.java</code>
     * @param javaSrc  source code string
     * @return return the Map, the KEY means ClassName, the VALUE means bytecode.
     */
    public static Map<String, byte[]> compile(String javaName, String javaSrc) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);

        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = manager.makeStringSource(javaName, javaSrc);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, Arrays.asList(javaFileObject));
            if (task.call())
                return manager.getClassBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class MemoryClassLoader extends URLClassLoader {

        Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

        public MemoryClassLoader() {
            super(new URL[0], MemoryClassLoader.class.getClassLoader());
        }

        /**
         * 需要  className,与对应的流 然后defineClass生成类  defineClass可以在任何方法内生成  例如findClass or loadClass等
         *
         * @param classBytes
         */
        public void add(Map<String, byte[]> classBytes) {
            this.classBytes.putAll(classBytes);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] buf = classBytes.get(name);
            if (buf == null) {
                return super.findClass(name);
            }
            classBytes.remove(name);
            return defineClass(name, buf, 0, buf.length);
        }
    }

    public static class MemoryClassLoader2 extends ClassLoader {

        Map<String, byte[]> classBytes = new HashMap<String, byte[]>();


        public void add(Map<String, byte[]> classBytes) {
            this.classBytes.putAll(classBytes);
        }

        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            System.out.println("1:" + name);
            Class<?> c = findLoadedClass(name);
            System.out.println("c:" + c);
            return super.loadClass(name);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] buf = classBytes.get(name);
            if (buf == null) {
                return super.findClass(name);
            }
            classBytes.remove(name);
            //defineClass 会走loadClass但不会走 但不会走父加载器循环
            return defineClass(name, buf, 0, buf.length);
        }
    }
}
