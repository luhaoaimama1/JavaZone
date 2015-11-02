package Android.Zone.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import Java.Zone.Setting.MyJava_Preferences;
import android.content.Context;
import android.widget.Toast;

public class RunProcess {
	/**
	 * (Android端)
	 * <br>
	 * 需要开启线程来操作！！！
	 * @param PingBoolean  控制什么时候停止 循环的开关 改变即关闭Ping
	 * @param IPAddress ping的IP地址（www.baidu.com 也可以192.168.60.112）
	 * @param context	Toast需要的Context
	 */
	public static void Ping(boolean PingBoolean, String IPAddress,Context context) {
		MyJava_Preferences.op();
		BufferedReader br = null;
		try {
			System.out.println("Ping 的值为 : ping -c 100" + IPAddress);
			Process p = Runtime.getRuntime().exec("ping -c 100   " + IPAddress);

			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while (PingBoolean) {
				String s = br.readLine();
				if (s == null) {
					System.out.println("s == null  即wifi未开");
					toToast(context, "wifi未开！");
					break;
				}
				if (s != null && s.contains("Host Unreachable")) {
					toToast(context, "未连通！");
				}
				if (s != null && s.contains("64 bytes")) {
					toToast(context, "已经连通!");
					break;
				}
				System.out.println("Ping的结果：" + s);
			}
		} catch (Exception e) {
			System.out.println("发生异常！！！：" + e.getMessage());
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println("br流关闭异常");
			}
			MyJava_Preferences.ed();
		}

	}

	/**
	 * Java端 <br> 可以有空格 任何程序都可以打开<br>
	 * 例子： 	File f=new File("C:/Users/a/Desktop/a b.txt"); <br>
	 * openExe(f.getCanonicalPath());
	 * 
	 * @param string
	 *            文件的绝对路径
	 */
	public static void openExe(String string) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec("cmd /c \""+string+"\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("打开程序发生IOException！！！");
		}
	}
	/**
	 * Java端 <br>可以有空格 任何程序都可以打开<br>
	 * 例子：	File f=new File("C:/Users/a/Desktop/a b.txt"); <br>
	 * openExe(f);
	 * @param file  文件即可
	 */
	public static void openExe(File file) {
		// TODO Auto-generated method stub
		try {
			Runtime.getRuntime().exec("cmd /c \""+file.getCanonicalPath()+"\"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("打开程序发生IOException！！！");
		}
	}

	private static void toToast(Context context, String string) {
		Toast.makeText(context, string, 1).show();
	}
}
