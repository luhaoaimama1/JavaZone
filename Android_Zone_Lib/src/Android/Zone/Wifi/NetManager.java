package Android.Zone.Wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
public class NetManager {

	/**
	 * 判断wifi是否处于连接状态
	 * 
	 * @return boolean :返回wifi是否连接
	 */
	public static boolean isWIFI(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean result = false;
		if (networkInfo != null) {
			result = networkInfo.isConnected();
		}
		return result;
	}

	/**
	 * 判断是否APN列表中某个渠道处于连接状态
	 * 
	 * @return 返回是否连接
	 */
	public static boolean isMoble(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean result = false;
		if (networkInfo != null) {
			result = networkInfo.isConnected();
		}
		return result;
	}
}
