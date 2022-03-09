package hook.Instrumentation.premain

import javassist.ClassPool
import javassist.CtClass
import java.io.ByteArrayInputStream
import java.lang.instrument.ClassFileTransformer
import java.lang.instrument.IllegalClassFormatException
import java.security.ProtectionDomain

class ByteChangeTransformerKt : ClassFileTransformer {
    @Throws(IllegalClassFormatException::class)
    override fun transform(loader: ClassLoader,
                           className: String,
                           classBeingRedefined: Class<*>?,
                           protectionDomain: ProtectionDomain,
                           classfileBuffer: ByteArray): ByteArray {
//        if (!"hook.Instrumentation.premain.TestAgent".equals(className)) {
//            // 只修改指定的Class
//            return classfileBuffer;
//        }
        println(" passing! className：$className")
        var transformed: ByteArray? = null
        var cl: CtClass? = null
        try {
            // CtClass、ClassPool、CtMethod、ExprEditor都是javassist提供的字节码操作的类
            val pool = ClassPool.getDefault()
            cl = pool.makeClass(ByteArrayInputStream(classfileBuffer))
            val methods = cl.declaredMethods
            for (i in methods.indices) {
                val method = methods[i]
                if (method.methodInfo.codeAttribute != null) {

                    method.insertBefore("System.out.println(\" inserting \t ${className}.${method.name}\");")
                }
                //                methods[i].insertAfter("System.out.println(\" after\");");
//                methods[i].instrument(new ExprEditor() {
//                    @Override
//                    public void edit(MethodCall m) throws CannotCompileException {
//                        // 把方法体直接替换掉，其中 $proceed($$);是javassist的语法，用来表示原方法体的调用
//                        m.replace("{ long stime = System.currentTimeMillis();"
//                                + " $_ = $proceed($$);"
//                                + "System.out.println(\"" + m.getClassName() + "." + m.getMethodName()
//                                + " cost:\" + (System.currentTimeMillis() - stime) + \" ms\"); }");
//                    }
//                });
            }
            // javassist会把输入的Java代码再编译成字节码byte[]
            transformed = cl.toBytecode()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cl?.detach()
        }
        return transformed!!
    }
}