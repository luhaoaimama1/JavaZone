package socket

import java.net.ServerSocket
import java.net.Socket

/**
 * @author csc
 * @description 从这里启动一个服务端监听某个端口
 * copy from:https://www.cnblogs.com/sky-heaven/p/9287815.html
 */
object DstService {
    @JvmStatic
    fun main(args: Array<String>) {
        server()
    }

    private fun server() {
        try {
            // 启动监听端口 30000
            val ss = ServerSocket(30000)
            // 没有连接这个方法就一直堵塞
            val s = ss.accept()
            // 将请求指定一个线程去执行
            Thread(DstServiceImpl(s)).start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    /**
     * @author csc
     * @description 服务的启动的线程类
     */
    class DstServiceImpl(s: Socket?) : Runnable {
        var socket: Socket? = null
        override fun run() {
            try {
                var index = 1
                while (true) {
                    // 5秒后中断连接
                    if (index > 10) {
                        socket!!.close()
                        println("服务端已经关闭链接！")
                        break
                    }
                    index++
                    Thread.sleep(1 * 1000.toLong()) //程序睡眠1秒钟
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        init {
            socket = s
        }
    }
}