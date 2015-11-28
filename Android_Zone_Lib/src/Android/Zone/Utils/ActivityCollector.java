package Android.Zone.Utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
//**这个不能用　因为这个让acitivity不能正常的销毁
public class ActivityCollector {
	public static List<Activity> activities = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		if (!activities.contains(activity))
			activities.add(activity);
	}

	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}

	public static void finishAll() {
		for (Activity item : activities) {
			if (!item.isFinishing()) {
				item.finish();
			}
		}

	}
}
