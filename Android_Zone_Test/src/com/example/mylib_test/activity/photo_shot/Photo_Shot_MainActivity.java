package com.example.mylib_test.activity.photo_shot;
import com.example.mylib_test.R;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import Android.Zone.Features.FeaturesActivity;
import Android.Zone.Features.Feature_Pic;
public class Photo_Shot_MainActivity extends FeaturesActivity{
	private Feature_Pic feature_Pic;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.a_photo_shot);
	}
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.shot:
			feature_Pic.openCamera();
			break;
		case R.id.photo:
			feature_Pic.openPhotos();
			break;
		case R.id.clip:
			startActivity(new Intent(this,ClipTest.class));
			break;
		default:
			break;
		}
	}

	@Override
	protected void addFeatureMethod() {
		feature_Pic = new Feature_Pic(this) {
			@Override
			protected void getReturnedPicPath(String path) {
				System.out.println(path);
				Intent intent = new Intent(Photo_Shot_MainActivity.this,ShowPicActivity.class);
				Uri uri = Uri.parse(path);
				intent.setData(uri);
				startActivity(intent);
			}
		};
		addFeature(feature_Pic);
	}

}
