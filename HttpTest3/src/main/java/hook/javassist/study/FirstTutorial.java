package hook.javassist.study;

import javassist.*;

import java.io.IOException;
//https://github.com/jboss-javassist/javassist/wiki/Tutorial-1
public class FirstTutorial {
    static String folderPath = "HttpTest3/ignoreFolder";

    public static void main(String[] args) throws NotFoundException, CannotCompileException, IOException, InstantiationException, IllegalAccessException {
//        makeNewClass();
//        makeNewClassChangeName();
//        frozen();

        //Frozen classes 冻结是为了对不了解的人做警告 告诉这个类已经不允许修改了。如果想修改则需要解冻defrost

        /**
         * <br>pruned 说明</br>
         *         CtClasss cc = ...;
         *         cc.stopPruning(true);
         *     :
         *         cc.writeFile();                             // convert to a class file.
         *         cc is not pruned.
         *  The CtClass object cc is not pruned. Thus it can be defrost after writeFile() is called.
         */

        /**
         * 类搜索路径
         * 可以设置 fold
         * pool.insertClassPath("/usr/local/javalib");
         *
         * 设置可以设置 url
         * ClassPath cp = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
         * pool.insertClassPath(cp);
         */

        //   用新的class对象池
        //        ClassPool cp = new ClassPool(true);
        // if needed, append an extra search path by appendClassPath()

        //ClassPool cp = new ClassPool();
        //cp.appendSystemPath();  // or append another path by appendClassPath()

//级连操作
//        ClassPool parent = ClassPool.getDefault();
//        ClassPool child = new ClassPool(parent);
//        child.insertClassPath("./classes");
        getExistClass();
    }

    private static void frozen() throws NotFoundException, CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.get("Point");
        CtClass ccPointParent = pool.get("PointParent");
        cc.defrost();//不解冻 的话回报错
//        After defrost() is called, the CtClass object can be modified again.
        cc.setSuperclass(ccPointParent);

        cc.stopPruning(true);//不设置这个对象会被修剪一些不必要的属性或者在CtClass修剪对象后
        // ，除了方法名称、签名和注释之外，方法的字节码是不可访问的。被修剪的CtClass对象不能再次解冻
        cc.writeFile(folderPath);
    }

    public static void makeNewClass() throws CannotCompileException, IOException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point");
        CtClass ccParent = pool.makeClass("PointParent");
        cc.stopPruning(true);
        cc.writeFile(folderPath);
        System.out.println(new String(cc.toBytecode()));
    }

    public static void makeNewClassChangeName() throws CannotCompileException, IOException, NotFoundException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("Point2");
        cc.setName("Pair");
        cc.stopPruning(true);
        cc.writeFile(folderPath);
        System.out.println(new String(cc.toBytecode()));
        isSameObj(pool);
    }

    private static void isSameObj(ClassPool pool) throws NotFoundException {
        CtClass cc = pool.get("Point");
        CtClass cc1 = pool.get("Point");   // cc1 is identical to cc.
        cc.setName("Pair");
        CtClass cc2 = pool.get("Pair");    // cc2 is identical to cc.
        CtClass cc3 = pool.get("Point");   // cc3 is not identical to cc
    }

    public static class Hello233 {
        public void say() {
            System.out.println("Hello");
        }
    }
    public static void getExistClass() throws CannotCompileException, IOException, NotFoundException, IllegalAccessException, InstantiationException {
        Hello233 hello233 = new Hello233();
        ClassPool cp = ClassPool.getDefault();
//        cp.insertClassPath(new ClassClassPath(Hello233.class));
        CtClass cc = cp.get("hook.javassist.study.FirstTutorial.Hello233");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
        Class c = cc.toClass();
        Hello233 h = (Hello233)c.newInstance();
        h.say();
    }
}
