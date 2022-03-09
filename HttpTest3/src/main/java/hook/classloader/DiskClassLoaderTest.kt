package hook.classloader

import gson学习与反射.反射框架测试.Reflect
import okio.Okio
import java.io.File

class DiskClassLoaderTest {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val diskClassLoader = DiskClassLoader("E:\\test")
            val loadClass = diskClassLoader.loadClass("com.zone.test.Test")
            val newInstance = loadClass.newInstance()
            Reflect.on(newInstance).call("say")

            val loadClass1 = diskClassLoader.loadClass("com.zone.test.Test")
            println("loadClass:${loadClass}   loadClass1:${loadClass1}     equal?${loadClass == loadClass1}")
        }
    }

    class DiskClassLoader : ClassLoader {
        var libPath: String

        constructor(path: String) : super() {
            this.libPath = path
        }

        override fun loadClass(name: String?, resolve: Boolean): Class<*> {
            return super.loadClass(name, resolve)
        }


        override fun findClass(name: String): Class<*> {
            val fileName = getFileName(name)
            val file = File(libPath, fileName)

            return try {
                val sink = Okio.source(file)
                val buffer = Okio.buffer(sink)
                val readByteArray = buffer.readByteArray()
                defineClass(name, readByteArray, 0, readByteArray.size)
            } catch (e: Exception) {
                e.printStackTrace()
                super.findClass(name)
            }
        }

        fun getFileName(name: String): String {
            val index = name.lastIndexOf('.');
            return if (index == -1) "$name.class";
            else name.substring(index + 1) + ".class";
        }

    }
}