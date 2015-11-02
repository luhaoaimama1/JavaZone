package com.example.mylib_test.activity.photo_shot;

import java.io.File;
import java.io.FileNotFoundException;

import com.example.mylib_test.R;

import Android.Zone.Image.Compress_Sample_Utils;
import Android.Zone.SD.FileUtils_SD;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_clip);
		 imgFile=FileUtils_SD.FolderCreateOrGet("","abc.jpg");
		 imageView=(ImageView) findViewById(R.id.iv);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt1:
			//Ð¡Í¼
			saveSmall();
			break;
		case R.id.bt2:
			//´óÍ¼
			saveBig();
			break;

		default:
			break;
		}
	}

	private void saveBig() {
		cropImageUri( Uri.parse(imgFile.getPath()), 100, 100, CHOOSE_BIG_PICTURE);
	}

	private void saveSmall() {
		Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getPath());
		Bitmap btemp=bitmap.copy(Config.ARGB_4444, true);
		Canvas can=new Canvas(btemp);
		can.clipRect(new Rect(0, 0, 100, 100));
		can.save();
		imageView.setImageBitmap(btemp);
		
//		Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
//		intent.setType("image/*");
//		intent.putExtra("crop", "true");
//		intent.putExtra("aspectX", 2);
//		intent.putExtra("aspectY", 1);
//		intent.putExtra("outputX", 200);
//		intent.putExtra("outputY", 100);
//		intent.putExtra("scale", true);
//		intent.putExtra("return-data", true);
//		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
//		intent.putExtra("noFaceDetection", true); // no face detection
//		startActivityForResult(intent, CHOOSE_SMALL_PICTURE);
	}
	
	private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode){
		 Intent intent = new Intent("com.android.camera.action.CROP");
		 intent.setDataAndType(uri, "image/*");
		 intent.putExtra("crop", "true");
		 intent.putExtra("aspectX", 2);
		 intent.putExtra("aspectY", 1);
		 intent.putExtra("outputX", outputX);
		 intent.putExtra("outputY", outputY);
		 intent.putExtra("scale", true);
		 intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		 intent.putExtra("return-data", false);
		 intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
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
