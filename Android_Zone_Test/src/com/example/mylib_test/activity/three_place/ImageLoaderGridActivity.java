package com.example.mylib_test.activity.three_place;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import Android.Zone.Abstract_Class.Adapter_Zone;
import Android.Zone.Image.DiskLruUtils;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageLoaderGridActivity extends Activity{
	private GridView gridView1;
	private String[] imageThumbUrls;
	private DiskLruUtils diskLru;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 diskLru=DiskLruUtils.openLru(this);
		setContentView(R.layout.a_thirdparty_imageloader_grid);
		gridView1=(GridView) findViewById(R.id.gridView1);
//		Images.imageThumbUrls
		List<String> temp = Arrays.asList(Images.imageThumbUrls);
		gridView1.setAdapter(new Adapter(this, temp, R.layout.imageitem));
	}
	public class Adapter extends Adapter_Zone<String>{

		public Adapter(Context context, List<String> data, int layout_id) {
			super(context, data, layout_id);
		}

		@Override
		public void setData(Map<Integer, View> viewMap, String data,
				int position) {
			ImageView iv=(ImageView) viewMap.get(R.id.iv);
			ImageLoader.getInstance().displayImage(data, iv);
//			Bitmap bm = diskLru.getBitmapByUrl(data);
//			if (bm==null) {
//				ImageLoader.getInstance().displayImage(data, iv,new SimpleImageLoadingListener(){
//					@Override
//					public void onLoadingComplete(String imageUri, View view,
//							Bitmap loadedImage) {
//						super.onLoadingComplete(imageUri, view, loadedImage);
//						diskLru.addUrl(imageUri, loadedImage);
//					}
//				});
//			} else
//				iv.setImageBitmap(bm);
		}
		
	} 
	@Override
	protected void onDestroy() {
		diskLru.flush();
		diskLru.delete();
		super.onDestroy();
	}

}
