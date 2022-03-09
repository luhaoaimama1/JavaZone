package hook.javassist

import gson学习与反射.反射框架测试.Reflect
import hook.classloader.DiskClassLoaderTest
import javassist.*
import java.io.File


//copy from:https://www.cnblogs.com/rickiyang/p/11336268.html
/**
 * 证明了：
 * 1.生成的类 生成的对象。对象反射得到的方法都是生成类中包含的。
 * 2.关于某个现有的类。插入方法和 某个方法内外插入代码 会如何
 *
 */
object JavassistStudy {
    val folderPath = "HttpTest3/ignoreFolder"
    val classPackage = "hook.javassist"
    val ClassName = "NewJavassist"

    @JvmStatic
    fun main(args: Array<String>) {
//        verify1()
        verify2()
    }

    /**
     * 问题:javassist.CannotCompileException: by java.lang.LinkageError: loader (instance of  sun/misc/Launcher$AppClassLoader): attempted  duplicate class definition for name
     * https://stackoverflow.com/questions/40105981/changing-a-class-with-javassist-java-reflexion
     */
    private fun verify2() {
        NowClas()
        val cp = ClassPool(true)
//        val clas=cp[NowClas::class.java.name]
        val clas=cp["hook.javassist.NowClas"]
//        clas.name="NowClasJavaS"
        clas.defrost()
        val getName = clas.getDeclaredMethod("printName")
        getName.insertBefore("System.out.println(\"getName之前搞事情\");")
        getName.insertAfter("System.out.println(\"getName 之后搞事情\");")
        clas.toClass()
//        val person = clas.toClass().newInstance()
//        (person as NowClas).printName()
//        NowClas().printName()

        // 调用 personFly 方法
        // 调用 personFly 方法clas
//        val personFlyMethod= person.javaClass.getMethod("printName")
//        personFlyMethod.invoke(person)
//        clas.toClass().newInstance()
//        val nowClas=clas.toClass().newInstance() as NowClas
        val nowClas = NowClas()
        nowClas.printName()
    }

    private fun verify1() {
        createClass(ClassName)
        val path = File(folderPath, classPackage.replace(".", File.separator))
        //设置优先加载路径
        val diskClassLoader = DiskClassLoaderTest.DiskClassLoader(path.canonicalPath)
        //加载路径中的class NewJavassist
        val loadClass = diskClassLoader.loadClass("${classPackage}.${ClassName}")
        val newInstance = loadClass.newInstance()
        println("getName===>:" + Reflect.on(newInstance).call("getName"))


        val ClassName2 = "${ClassName}2"
        createClass(ClassName2)
        changeClass(ClassName2)
    }

    //另外需要注意的是：上面的insertBefore() 和 setBody()中的语句，
    // 如果你是单行语句可以直接用双引号，但是有多行语句的情况下，你需要将多行语句用{}括起来。javassist只接受单个语句或用大括号括起来的语句块

    //注意修改的代码 不是修改在 class上了 因为我看class没改变应该是  classLoader 里define存入的地方那里
    fun changeClass(ClassName: String) {
        val pool = ClassPool.getDefault()
        // 设置类路径
        pool.appendClassPath(folderPath);
        val cc = pool["${classPackage}.${ClassName}"]

        if (cc.isFrozen) cc.defrost()//报错frozen 应该是我之前用他写的

        val getName = cc.getDeclaredMethod("getName")
        getName.insertBefore("System.out.println(\"getName之前搞事情\");")
        getName.insertAfter("System.out.println(\"getName 之后搞事情\");")

        //新增一个方法
        val ctMethod = CtMethod(CtClass.voidType, "joinFriend", arrayOf(), cc)
        ctMethod.modifiers = Modifier.PUBLIC
        ctMethod.setBody("{System.out.println(\"i want to be your friend\");}")
        cc.addMethod(ctMethod)

        //执行其他的方式
        val person = cc.toClass().newInstance()
        // 调用 getName 方法
        person.javaClass.getMethod("getName").invoke(person)
        //调用 joinFriend 方法
        person.javaClass.getMethod("joinFriend").invoke(person)

        //看一下 插入的方法这个类的对象有吗
        println("person对象有 joinFriend方法么:${containMethod(person, "joinFriend")}")
    }

    private fun containMethod(person: Any, s: String):Boolean {
        val methods = person::class.java.methods
        for (method in methods) {
            if(method.name.contains(s)) return true
        }
        return false
    }


    fun createClass(ClassName: String) {
        val pool = ClassPool.getDefault()
        // 1. 创建一个空类
        val newClass = pool.makeClass("${classPackage}.${ClassName}")

        // 2. 新增一个字段 private String name;
        // 字段名为name
        val field = CtField(pool.get("java.lang.String"), "name", newClass)
        // 访问级别是 private
        field.modifiers = Modifier.PRIVATE
        // 初始值是 "xiaoming"
        newClass.addField(field, CtField.Initializer.constant("xiaoming"));

        // 3. 生成 getter、setter 方法
        newClass.addMethod(CtNewMethod.setter("setName", field));
        newClass.addMethod(CtNewMethod.getter("getName", field));

        // 4. 添加无参的构造函数
        var cons = CtConstructor(arrayOf(), newClass)
        cons.setBody("{name = \"xiaohong\";}")
        newClass.addConstructor(cons)

        // 5. 添加有参的构造函数
        cons = CtConstructor(arrayOf(pool["java.lang.String"]), newClass)
        // $0=this / $1,$2,$3... 代表方法参数
        cons.setBody("{$0.name = $1;}")
        newClass.addConstructor(cons)

        // 6. 创建一个名为printName方法，无参数，无返回值，输出name值
        val ctMethod = CtMethod(CtClass.voidType, "printName", arrayOf(), newClass)
        ctMethod.modifiers = Modifier.PUBLIC
        ctMethod.setBody("{System.out.println(name);}")
        newClass.addMethod(ctMethod)

        //这里会将这个创建的类对象编译为.class文件
        newClass.writeFile(folderPath);

//        println("类字符串==》" + String(newClass.toBytecode()))
    }
}