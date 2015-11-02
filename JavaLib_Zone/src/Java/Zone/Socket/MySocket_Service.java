package Java.Zone.Socket;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 已经测试 OK 不玩忘记 startAccept 例子说明在 class旁边Service说明
 * 
 * @author zone
 * 
 */
public abstract class  MySocket_Service {
	private ServerSocket server;
	private Socket client;

	public MySocket_Service(int port) throws IOException {
		// TODO Auto-generated constructor stub
		server = new ServerSocket(port);
	}

	/**
	 * 开始tcp的服务
	 * 
	 * @throws IOException
	 */
	public void startAccept() throws IOException {
		while (true) {
			client = server.accept();
			new Thread(new ClientThread(client)).start();
		}
	}

	/**
	 * 客户端处理
	 * 
	 * @author Ozone
	 * 
	 */
	class ClientThread implements Runnable {
		private Socket client;
		private BufferedOutputStream br_out;
		private BufferedInputStream br_in;

		public ClientThread(Socket client) throws IOException {
			// TODO Auto-generated constructor stub
			this.client = client;
			br_out = new BufferedOutputStream(client.getOutputStream());
			br_in = new BufferedInputStream(client.getInputStream());
		}

		public void run() {
			// TODO Auto-generated method stub
				blockProcess(br_in,br_out);
				try {
					br_in.close();
					br_out.close();
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("流与客户端关闭IOException！！！");
				}
		}

	}
	/**
	 * 在这里写 读写逻辑就行了
	 * @param br_in Client（客户端）提供给你的输入流 已经包装好了 BufferedInputStream
	 * @param br_out  service（服务器）提供给你的输出流 已经包装好了 BufferedOutputStream 
	 */ 
	public abstract void blockProcess(BufferedInputStream br_in, BufferedOutputStream br_ou) ;
}
