package Android.Zone.Features;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class FeaturesActivity extends FragmentActivity implements OnClickListener{
	public List<ExtraFeature> featureList= new ArrayList<ExtraFeature>();
	public Activity activity=null;
	@Override
	protected void onCreate(Bundle bundle) {
		activity=this;
		addFeatureMethod();
		super.onCreate(bundle);
		for (ExtraFeature item : featureList) {
			item.onCreate(bundle);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		for (ExtraFeature item : featureList) {
			item.onActivityResult(requestCode, resultCode, intent);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		for (ExtraFeature item : featureList) {
			item.onDestroy();
		}
	}
	protected FeaturesActivity addFeature(ExtraFeature feature) {
		featureList.add(feature);
		return this;
	}
	protected abstract void addFeatureMethod() ;
	@Override
	public void onClick(View v) {
		for (ExtraFeature item : featureList) {
			item.onClick(v);
		}
	}
}
