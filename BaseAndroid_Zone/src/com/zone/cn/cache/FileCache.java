package com.zone.cn.cache;

import java.io.File;
import android.os.Environment;

public class FileCache {
	private static final String APP_NAME="Zone";
	/** SD卡根目录 */
	public static File sdFile;
	/** app目录 */
	public static File appFile;
	/** 文件下载目录 */
	public static File downloadFile;
	/** 图片下载目录 */
	public static File downloadFileImages;
	/** app处理图片缓存目录 */
	public static File imageCacheFile;
	/** ImageLoader缓存目录 */
	public static File imageLoaderCacheFile;
	/**  app处理声音缓存目录 */
	public static File voicesCacheFile;
	/** bug日志收集目录 */
	public static File logFile;

	static {
		if (sdFile == null || !sdFile.exists()) {
			sdFile = Environment.getExternalStorageDirectory();
			appFile = new File(sdFile, APP_NAME);
			downloadFile = new File(appFile, "/download/");
			downloadFileImages = new File(appFile, "/download/images");
			imageCacheFile = new File(appFile, "/cache/images");
			voicesCacheFile = new File(appFile, "/cache/voices");
			imageLoaderCacheFile = new File(appFile, "/imageLoader");
			logFile = new File(appFile, "/log/");
			appFile.mkdirs();
			downloadFile.mkdirs();
			imageCacheFile.mkdirs();
			voicesCacheFile.mkdirs();
			imageLoaderCacheFile.mkdirs();
			logFile.mkdirs();
		}
	}
}
