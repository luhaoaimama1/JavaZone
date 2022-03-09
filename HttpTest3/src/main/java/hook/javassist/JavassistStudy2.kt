package hook.javassist

import javassist.ClassPool
import javassist.CtClass
import javassist.CtMethod
import javassist.Modifier


fun main(args: Array<String>) {
//    changeClass2("Test")
    changeClass3("Test")
}

fun changeClass2(ClassName: String) {
    val pool = ClassPool.getDefault()
    // 设置类路径
//    pool.appendClassPath(JavassistStudy.folderPath);
    val cc = pool["${JavassistStudy.classPackage}.${ClassName}"]

    if (cc.isFrozen) cc.defrost()//报错frozen 应该是我之前用他写的

    val getName = cc.getDeclaredMethod("last")
    getName.insertBefore("System.out.println(\"getName之前搞事情\");")
    getName.insertAfter("System.out.println(\"getName 之后搞事情\");")

    //执行其他的方式
    val person = cc.toClass().newInstance()
    // 调用 getName 方法
    val method2 = person.javaClass.getDeclaredMethod("setStr",String::class.java)
    method2.isAccessible=true
    method2.invoke(person,"Zone")

    val method = person.javaClass.getDeclaredMethod("last")
    method.isAccessible=true
    method.invoke(person)

    Test().last()

    //改所有类所有的信息 。
}


fun changeClass3(ClassName: String) {
    val pool = ClassPool.getDefault()
    // 设置类路径
//    pool.appendClassPath(JavassistStudy.folderPath);
    val cc = pool["${JavassistStudy.classPackage}.${ClassName}"]

    if (cc.isFrozen) cc.defrost()//报错frozen 应该是我之前用他写的


    val proxyCtClass: CtClass = pool.makeClass("hook.javassist.TestChild")
    //执行其他的方式
    val person = proxyCtClass.toClass().newInstance();
    if (proxyCtClass.isFrozen) proxyCtClass.defrost()//报错frozen 应该是我之前用他写的
    // 设置描述符
    proxyCtClass.modifiers = Modifier.PUBLIC or Modifier.FINAL
    // 设置继承关系
    proxyCtClass.superclass = cc


    // 6. 创建一个名为printName方法，无参数，无返回值，输出name值
    val ctMethod = CtMethod(CtClass.voidType, "last", arrayOf(), proxyCtClass)
    ctMethod.modifiers = Modifier.PUBLIC
    ctMethod.setBody("super.last();")
    proxyCtClass.addMethod(ctMethod)

    val getName = proxyCtClass.getDeclaredMethod("last")
    getName.insertBefore("System.out.println(\"getName之前搞事情\");")
    getName.insertAfter("System.out.println(\"getName 之后搞事情\");")

    // 调用 getName 方法
//    val method2 = person.javaClass.getMethod("setStr",String::class.java)
//    method2.isAccessible=true
//    method2.invoke(person,"Zone")

    val method = proxyCtClass.javaClass.getDeclaredMethod("last")
    method.isAccessible=true
    method.invoke(person)


    Test().last()
}