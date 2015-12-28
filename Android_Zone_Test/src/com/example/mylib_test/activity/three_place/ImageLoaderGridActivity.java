package com.example.mylib_test.activity.three_place;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import Android.Zone.Abstract_Class.Adapter.Adapter_Zone;
import Android.Zone.Abstract_Class.Adapter.core.ViewHolder_Zone;
import Android.Zone.Image.lruUtils.DiskLruUtils;
import Android.Zone.Log.ToastUtils;
import Android.Zone.Utils.MeasureUtils;
import Android.Zone.Utils.MeasureUtils.GlobalState;
import Android.Zone.Utils.MeasureUtils.OnMeasureListener;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.LruCache;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ScrollView;

public class ImageLoaderGridActivity extends Activity{
	private GridView gridView1;
	private String[] imageThumbUrls;
	private DiskLruUtils diskLru;
	private ScrollView sl;
//	private DisplayImageOptions options;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LruCache<String, Bitmap> a = new LruCache<String, Bitmap>(100){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return super.sizeOf(key, value);
			}
		};
		diskLru=DiskLruUtils.openLru(this);
		setContentView(R.layout.a_thirdparty_imageloader_grid);
		gridView1=(GridView) findViewById(R.id.gridView1);
		gridView1.setFocusable(false);
		sl=(ScrollView) findViewById(R.id.sl);
	
//		Images.imageThumbUrls
		List<String> temp = Arrays.asList(Images.imageThumbUrls);
		gridView1.setAdapter(new Adapter(this, temp));
//		gridView1.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true));
		
//		options = new DisplayImageOptions.Builder()
//		.showImageOnLoading(R.drawable.ic_stub)
//		.showImageForEmptyUri(R.drawable.ic_empty)
//		.showImageOnFail(R.drawable.ic_error)
//		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
//		.cacheInMemory(true)
//		.cacheOnDisk(true)
//		.bitmapConfig(Bitmap.Config.RGB_565)	 //设置图片的解码类型
//		.build();
	}
	public class Adapter extends Adapter_Zone<String>{

		public Adapter(Context context, List<String> data) {
			super(context, data);
		}

		@Override
		public int setLayoutID() {
			// TODO Auto-generated method stub
			return  R.layout.imageitem;
		}

		@Override
		public void setData(ViewHolder_Zone holder, String data, int position) {
			 ImageView iv=(ImageView) holder.findViewById(R.id.iv);
//				ImageLoader.getInstance().displayImage(data, iv,options);
				ImageLoader.getInstance().displayImage(data, iv);
				iv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						ToastUtils.showLong(ImageLoaderGridActivity.this, "你干啥");
					}
				});
//				Bitmap bm = diskLru.getBitmapByUrl(data);
//				if (bm==null) {
//					ImageLoader.getInstance().displayImage(data, iv,new SimpleImageLoadingListener(){
//						@Override
//						public void onLoadingComplete(String imageUri, View view,
//								Bitmap loadedImage) {
//							super.onLoadingComplete(imageUri, view, loadedImage);
//							diskLru.addUrl(imageUri, loadedImage);
//						}
//					});
//				} else
//					iv.setImageBitmap(bm);
		}
		
	} 
	@Override
	protected void onDestroy() {
		diskLru.flush();
		diskLru.delete();
		super.onDestroy();
	}

}
