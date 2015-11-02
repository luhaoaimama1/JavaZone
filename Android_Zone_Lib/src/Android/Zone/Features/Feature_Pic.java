package Android.Zone.Features;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;

import Android.Zone.SD.FileUtils_SD;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.View;

/**
 * 可以调用 系统照相和 系统相册的 activity
 * <br>openCamera pop里也可以调用 静态方法  想处理 onActivityResult的那个 继承此activity就行
 * @author Zone
 *
 */
public abstract  class Feature_Pic extends ExtraFeature{
	public Feature_Pic(Activity activity) {
		super(activity);
	}
	private static String path;
	private static final int REQUESTCODE_CAMERA = 6789;
	private static final int REQUESTCODE_PHOTOS = 9876;
	private static File outFile = FileUtils_SD.FolderCreateOrGet("Zone", "picSave");

	public  void openCamera() {
		String picName = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA))+ ".jpg";
		outFile = new File(outFile, picName);
		path = outFile.getPath();
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));
		activity.startActivityForResult(intent, REQUESTCODE_CAMERA);
	}
	public  void openPhotos() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
		activity.startActivityForResult(intent,REQUESTCODE_PHOTOS);
	}

	/**
	 * 
	 * @param path 照片的路径  已经返回了 你调用就行
	 */
	protected abstract void getReturnedPicPath(String path);

	/**
	 * 判断文件是否存在
	 * @return
	 */
	private boolean isFileExist() {
		File file = new File(path);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}
	@Override
	public void onCreate(Bundle bundle) {
		
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		if (resultCode == -1) {
			switch (requestCode) {
			case REQUESTCODE_CAMERA:
				if (isFileExist()) {
					getReturnedPicPath(path);
				}
				break;
			case REQUESTCODE_PHOTOS:
				if (intent!=null) {
					Uri uri = intent.getData();
					ContentResolver cr = activity.getContentResolver();
					Cursor cursor = cr.query(uri, null, null, null, null);
					if(cursor==null){
//						file:///storage/sdcard0/MIUI/Gallery/cloud/.thumbnailFile/3fbad3015691d03f59ccb97561103abcc9b0ba12.jpg
//						content://media/external/images/media/8
						getReturnedPicPath(uri.getPath());
						break;
					}
					cursor.moveToFirst();
					for (int i = 0; i < cursor.getColumnCount(); i++) {
						// System.out.println(i + "-----------------"+cursor.getString(i)+"----"+cursor.getColumnName(i));
						if (cursor.getColumnName(i).contains("data")) {
							path = cursor.getString(i);
						}
					}
					getReturnedPicPath(path);
				}
				break;

			default:
				break;
			}
		}
	}
	@Override
	public void onDestroy() {
		
	}
	@Override
	public void onClick(View v) {
		
	}
	
}
