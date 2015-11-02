package Android.Zone.Features;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;

public abstract class ExtraFeature implements OnClickListener{
	public  Activity activity=null;
	public ExtraFeature(Activity activity) {
		if(activity==null)
			throw new IllegalArgumentException("arg:activity may be null!");
		this.activity=activity;
	}
	public abstract void onCreate(Bundle bundle);
	public abstract void onActivityResult(int requestCode, int resultCode, Intent intent);
	public abstract void onDestroy();
}
