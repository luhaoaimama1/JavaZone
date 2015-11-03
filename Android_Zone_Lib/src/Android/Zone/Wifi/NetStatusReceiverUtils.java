package Android.Zone.Wifi;

import java.util.HashMap;
import java.util.Map;
import Android.Zone.Wifi.NetStatusReceiver.NetWorkListener;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

public class NetStatusReceiverUtils {
	private static Map<Context, NetStatusReceiver> map = new HashMap<Context, NetStatusReceiver>();

	public static void register(Context context, NetWorkListener listener) {
		if(map.get(context)==null){
			IntentFilter filter = new IntentFilter();
			filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
			filter.setPriority(1000);
			NetStatusReceiver receiver = new NetStatusReceiver(listener);
			map.put(context, receiver);
			context.registerReceiver(receiver, filter);
		}
	}

	public static void unRegister(Context context) {
		context.unregisterReceiver(map.get(context));
		map.remove(context);
	}
}
