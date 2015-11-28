package com.example.mylib_test.activity.photo_shot;

import java.io.File;
import java.io.FileNotFoundException;

import com.example.mylib_test.R;

import Android.Zone.Image.Compress_Sample_Utils;
import Android.Zone.SD.FileUtils_SD;
import Android.Zone.Utils.MeasureUtils;
import Android.Zone.Utils.ScreenUtils;
import Android.Zone.Utils.MeasureUtils.GlobalState;
import Android.Zone.Utils.MeasureUtils.OnMeasureListener;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ClipTest extends Activity implements OnClickListener{
	private static final int CHOOSE_SMALL_PICTURE=1;
	private static final int CHOOSE_BIG_PICTURE=2;
	private File imgFile;
	private ImageView imageView;
	private Bitmap bitmap;
	private Bitmap chipSmallBt;
	private  Uri savePath=Uri.fromFile(FileUtils_SD.FolderCreateOrGet("", "a1.jpg"));
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_clip);
		imgFile = FileUtils_SD.FolderCreateOrGet("", "abc.jpg");
		imageView = (ImageView) findViewById(R.id.iv);
		MeasureUtils.measureView_addGlobal(imageView, GlobalState.MEASURE_REMOVE_LISNTER, new OnMeasureListener() {
			
			@Override
			public void measureResult(View v, int view_width, int view_height) {
				bitmap = Compress_Sample_Utils.getSampleBitmap(imgFile.getPath(),imageView.getWidth(), null);
				imageView.setImageBitmap(bitmap);
			}
		});
		
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			//小图
			saveSmall();
			break;
		case R.id.bt2:
			//大图
			saveBig();
			break;

		default:
			break;
		}
	}

	private void saveSmall() {
		chipSmallBt = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth()/2,  bitmap.getHeight());
		imageView.setImageBitmap(chipSmallBt);
	}
	private void saveBig() {
		//http://bbs.csdn.net/topics/390932011 解决地址
		//已经解决了，4.4后gallery的最近和图片返回的content前缀的uri，需要将其转换为file:///前缀的绝对地址，然后再去调用com.android.camera.action.CROP，将该uri给setData，就不会有权限问题了。
		cropImageUri( Uri.parse("file://" + "/"+imgFile.getPath()), 800, 800, CHOOSE_BIG_PICTURE);
	}
	
	private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode){
		//小米特殊的intent action
//		不难知道，我们从相册选取图片的Action为Intent.ACTION_GET_CONTENT。
//		 Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
		 Intent intent = new Intent("com.android.camera.action.CROP");
		//可以选择图片类型，如果是*表明所有类型的图片
		 intent.setDataAndType(uri, "image/*");
	     // 下面这个crop = true是设置在开启的Intent中设置显示的VIEW可裁剪
		 intent.putExtra("crop", "true");
		//裁剪时是否保留图片的比例，这里的比例是1:1
		 intent.putExtra("scale", true);
		// 这两项为裁剪框的比例.   固定比率　　
		 intent.putExtra("aspectX", 2);
		 intent.putExtra("aspectY", 1);
		  // outputX outputY 是裁剪图片宽高
		 intent.putExtra("outputX", outputX);
		 intent.putExtra("outputY", outputY);
		 //是否将数据保留在Bitmap中返回
		 intent.putExtra("return-data", true);
		 //设置输出的格式
		 intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		 intent.putExtra("noFaceDetection", true); // no face detection
		 intent.putExtra(MediaStore.EXTRA_OUTPUT,savePath );//输出地址  
		 startActivityForResult(intent, requestCode);
		}
	
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CHOOSE_BIG_PICTURE:
		    Log.d("a", "CHOOSE_BIG_PICTURE: data = " + data);//it seems to be null
			if (data != null) {
				int[] screen = ScreenUtils.getScreenPix(this);
				Bitmap bitmap = Compress_Sample_Utils.getSampleBitmap(savePath.getPath(), screen[0], screen[1]);
				imageView.setImageBitmap(bitmap);
			}
		    break;
		case CHOOSE_SMALL_PICTURE:
		    if(data != null){
		        Bitmap bitmap = data.getParcelableExtra("data");
		        imageView.setImageBitmap(bitmap);
		    }else{
		        Log.e("a", "CHOOSE_SMALL_PICTURE: data = " + data);
		    }
		    break;
		default:
		    break;
		}
		
	}
	
}
