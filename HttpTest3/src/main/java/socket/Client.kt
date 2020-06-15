package socket

import java.net.Socket

object Client {
    @JvmStatic
    fun main(args: Array<String>) {
        client()
    }
//    从连接对象的属性信息来看，连接是没有中断，但实际链接已经在服务端建立链接10秒后断开了。这说明了上述几个方法是不能实时判断出socket的链接状态，只是socket驻留在内存的状态。其实，此时如果调用流去读取信息的话，就会出现异常。
    private fun client() {
        try {
            val socket = Socket("127.0.0.1", 30000)
            socket.keepAlive = true
            socket.soTimeout = 10
            while (true) {
                //如果未连接的话 这个发送过去一产生异常
                socket.sendUrgentData(0xFF); // 发送心跳包
                socket.let {  1 }
                socket.run {  1 }
                socket.apply {  1 }
                with(socket){

                }
                System.out.println("目前处于链接状态！");

                println("isBound:${socket.isBound} \t "
                        + "isClosed:${socket.isClosed} \t  "
                        + "isConnected:${socket.isConnected} \t  "
                        + "isInputShutdown:${socket.isInputShutdown} \t  "
                        + "isOutputShutdown:${socket.isOutputShutdown} \t  "
                )
                println("------------我是分割线------------")
                Thread.sleep(3 * 1000.toLong())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}