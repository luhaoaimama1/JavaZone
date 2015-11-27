package com.example.mylib_test.activity.photo_shot;

import java.io.File;
import java.io.FileNotFoundException;

import com.example.mylib_test.R;

import Android.Zone.Image.Compress_Sample_Utils;
import Android.Zone.SD.FileUtils_SD;
import Android.Zone.Utils.MeasureUtils;
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
		cropImageUri( Uri.parse(imgFile.getPath()), 100, 100, CHOOSE_BIG_PICTURE);
	}
	
	private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode){
		 Intent intent = new Intent("com.android.camera.action.CROP");
		 intent.setDataAndType(uri, "image/*");
		 intent.putExtra("crop", "true"); // crop=true 有这句才能出来最后的裁剪页面.  
		 intent.putExtra("aspectX", 2);// 这两项为裁剪框的比例.  
		 intent.putExtra("aspectY", 1);
		 intent.putExtra("outputX", outputX);
		 intent.putExtra("outputY", outputY);
		 intent.putExtra("scale", true);
		 intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		 intent.putExtra("return-data", false);
		 intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		 intent.putExtra("output", Uri.fromFile(new File("SDCard/1.jpg")));//输出地址  
		 intent.putExtra("noFaceDetection", true); // no face detection
		 startActivityForResult(intent, requestCode);
		}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case CHOOSE_BIG_PICTURE:
		    Log.d("a", "CHOOSE_BIG_PICTURE: data = " + data);//it seems to be null
			Uri imageUri = data.getData();
		    if(imageUri != null){
		        Bitmap bitmap = decodeUriAsBitmap(imageUri);//decode bitmap
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
	private Bitmap decodeUriAsBitmap(Uri uri){
	    Bitmap bitmap = null;
	    try {
	        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }
	    return bitmap;
	}
	
}
