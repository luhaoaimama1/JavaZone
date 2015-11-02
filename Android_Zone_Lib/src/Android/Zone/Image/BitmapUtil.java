package Android.Zone.Image;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtil {
	//处理某些手机拍照角度旋转的问题
//	public static String compressImage(Context context,String filePath,String fileName,int q) throws FileNotFoundException {
//
//        Bitmap bm = getSmallBitmap(filePath);
//
//        int degree = readPictureDegree(filePath);
//
//        if(degree!=0){//旋转照片角度
//            bm=rotateBitmap(bm,degree);
//        }
//
//        File imageDir = SDCardUtils.getImageDir(context);
//
//        File outputFile=new File(imageDir,fileName);
//
//        FileOutputStream out = new FileOutputStream(outputFile);
//
//        bm.compress(Bitmap.CompressFormat.JPEG, q, out);
//
//        return outputFile.getPath();
//    }
//      判断照片角度
//	public static int readPictureDegree(String path) {
//        int degree = 0;
//        try {
//            ExifInterface exifInterface = new ExifInterface(path);
//            int orientation = exifInterface.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_NORMAL);
//            switch (orientation) {
//            case ExifInterface.ORIENTATION_ROTATE_90:
//                degree = 90;
//                break;
//            case ExifInterface.ORIENTATION_ROTATE_180:
//                degree = 180;
//                break;
//            case ExifInterface.ORIENTATION_ROTATE_270:
//                degree = 270;
//                break;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return degree;
//    }
	
	/**
	 * 
	 * @param bitmap   位图
	 * @param degress  要旋转的角度
	 * @return   旋转照片
	 */
	public static Bitmap rotateBitmap(Bitmap bitmap,int degress) {
	        if (bitmap != null) {
	            Matrix matrix = new Matrix();
	          //旋转图片 动作   
	            matrix.postRotate(degress); 
	            // 创建新的图片   
	            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
	                    bitmap.getHeight(), matrix, true);
	            return bitmap;
	        }
	        return null;
	    }
	/**
	 * 
	 * @param bitmap   位图
	 * @param sx	横向的缩放
	 * @param sy	纵向的缩放
	 * @return   旋转照片
	 */
	public static Bitmap scaleBitmap(Bitmap bitmap,float sx,float sy) {
		if (bitmap != null) {
			Matrix matrix = new Matrix();
			//旋转图片 动作   
			matrix.postScale(sx,sy);
			// 创建新的图片   
			bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
					bitmap.getHeight(), matrix, true);
			return bitmap;
		}
		return null;
	}
}
