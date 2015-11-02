package Android.Zone.Setting;

import android.util.Log;

/**
 * 内部包的参数设置 与方法设置
 * 
 * @author a
 * 
 */
public class MyAndroid_Preferences {
	public static String projectName = "Zone_AndroidLib";

	/**
	 * 类似Log 但是这个不需要写tag <br>
	 * 而且这个是NotPerfectlib.xx.xx <br>
	 * xx.xx是谁调用这个方法就是谁
	 * 
	 * @param msg
	 */
	public static void MyLog(String msg) {
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		String[] s = ste.toString().split("[.]");
		String sec_path = s[0] + "." + s[1];
		Log.e(projectName + "." + sec_path, msg);
	}
}