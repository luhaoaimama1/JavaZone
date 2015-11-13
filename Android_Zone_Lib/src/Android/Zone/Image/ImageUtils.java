package Android.Zone.Image;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;

public class ImageUtils {
	
	/**
	 * Create reflection images
	 *
	 * @param bitmap
	 * @return
	 */
	//TODO 未看效果
	public static Bitmap createReflectionImageWithOrigin(Bitmap bitmap) {
		final int reflectionGap = 4;
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();

		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);

		Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, h / 2, w,
				h / 2, matrix, false);

		Bitmap bitmapWithReflection = Bitmap.createBitmap(w, (h + h / 2),
				Config.ARGB_8888);

		Canvas canvas = new Canvas(bitmapWithReflection);
		canvas.drawBitmap(bitmap, 0, 0, null);
		Paint deafalutPaint = new Paint();
		canvas.drawRect(0, h, w, h + reflectionGap, deafalutPaint);

		canvas.drawBitmap(reflectionImage, 0, h + reflectionGap, null);

		Paint paint = new Paint();
		LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
				bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
				0x00ffffff, TileMode.CLAMP);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
		canvas.drawRect(0, h, w, bitmapWithReflection.getHeight()
				+ reflectionGap, paint);

		return bitmapWithReflection;
	}
	
	// 问题就出在当加载的图片宽度和dstWidth且高度和dstHeight相同时，
	// Bitmap.createBitmap方法会返回原来的bitmap对象，这样bitmap== temp了
	public Bitmap getAdjustBitmap(Bitmap bitmap, int dstWidth, int dstHeight) {
		if (bitmap != null) {
			if (dstWidth > 0 && dstHeight > 0) {
				Bitmap temp = Bitmap.createBitmap(bitmap, 0, 0, dstWidth,
						dstHeight);
				// 或者
				// Bitmap temp = Bitmap.createScaledBitmap(bitmap, dstWidth,
				// dstHeight, true);
				if (bitmap != null && !bitmap.isRecycled() && temp != bitmap) {
					bitmap.recycle();
				}
				bitmap = temp;
			}
		}
		return bitmap;
	}

	public static void recycledBitmap(Bitmap bitmap) {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
		}
		System.gc();
	}

	/**
	 * 将图片存入缓存目录
	 */
	public static boolean save2Cache(Context context, String cover,
			Bitmap bitmap) {
		File file = new File(context.getCacheDir(), cover + ".png");
		return Compress_Sample_Utils.saveBitmap(file.getPath(), bitmap);
	}

	/**
	 * 转换图片成圆形
	 * 
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public static Bitmap toRoundBitmap(Bitmap bitmap) {

		if (bitmap == null)
			return null;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = (float) (width / 1.5);
			top = 0;
			bottom = width;
			left = 0;
			right = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = (float) (height / 2);
			float clip = (float) ((width - height) / 2);
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		//设置两张图片相交时的模式,参考http://trylovecatch.iteye.com/blog/1189452  
		canvas.drawBitmap(bitmap, src, dst, paint);
		 //以Mode.SRC_IN模式合并bitmap和已经draw了的Circle  
		return output;
	}

}
