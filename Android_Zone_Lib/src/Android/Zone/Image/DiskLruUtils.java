package Android.Zone.Image;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import Android.Zone.SD.SdSituation;
import Android.Zone.Utils.AppUtils;
import Android.Zone.Utils.MD5Utils;

public class DiskLruUtils {
	private static DiskLruUtils diskLru = new DiskLruUtils();
	private static DiskLruCache mDiskLruCache = null;

	/**
	 * 因为一个应用应该就用一个而不是多个 所以我就final了 想改自己改就ok了
	 */
	private static final String DirName = "bitmap";
	private static final long CacheMax=10 * 1024 * 1024;
	
	private static final String TAG="DiskLruUtils";
	private static boolean writeLog=true;
	public static void log(String str){
		if (writeLog) {
			Log.d(TAG, str);
		}
	}

	private DiskLruUtils() {
	}
	public static DiskLruUtils getInstance(){
		return diskLru;
	}

	/**
	 * 版本号改变 则自动清除
	 * @param context
	 * @return
	 */
	public   void  openLru(Context context) {
		try {
			/**
			 * open()方法接收四个参数，第一个参数指定的是数据的缓存地址，
			 * 第二个参数指定当前应用程序的版本号，
			 * 第三个参数指定同一个key可以对应多少个缓存文件，基本都是传1，第四个参数指定最多可以缓存多少字节的数据。
			 */
			File cacheDir = SdSituation.getDiskCacheDir(context, DirName);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = DiskLruCache.open(cacheDir,AppUtils.getAppVersion(context), 1, CacheMax);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 所以只有成功才调用这个方法
	 * 
	 * @param url
	 */
	public void addUrl(String url) {
		// if (downloadUrlToStream(imageUrl, outputStream)) {
		// editor.commit();
		// } else {
		// editor.abort(); //这个就是不提交了~
		// }
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			DiskLruCache.Editor editor = mDiskLruCache.edit(key);
			editor.commit();
			log("addUrl:"+url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean removeUrl(String url) {
		String key = MD5Utils.hashKeyForDisk(url);
		try {
			boolean temp= mDiskLruCache.remove(key);
			log("addUrl:"+url+(temp==true?"成功":"失败"));
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Bitmap getBitmapByUrl(String url) {
		String key = MD5Utils.hashKeyForDisk(url);
		Bitmap bitmap = null;
		try {
			DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
			bitmap = BitmapFactory.decodeStream(snapShot.getInputStream(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}

	/**
	 * 比较标准的做法就是在Activity的onPause()方法中去调用一次flush()方法就可以了。
	 */
	public void flush() {
		try {
			mDiskLruCache.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 这个方法用于将DiskLruCache关闭掉，是和open()方法对应的一个方法。关闭掉了之后就不能再调用DiskLruCache中任何操作缓存数据的方法，
	 * 通常只应该在Activity的onDestroy()方法中去调用close()方法。
	 */
	public void close() {
		try {
			mDiskLruCache.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 这个方法用于将所有的缓存数据全部删除，比如说网易新闻中的那个手动清理缓存功能，其实只需要调用一下DiskLruCache的delete()方法就可以实现了。
	 */
	public void delete() {
		try {
			mDiskLruCache.delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 这个方法会返回当前缓存路径下所有缓存数据的总字节数，以byte为单位，
		如果应用程序中需要在界面上显示当前缓存数据的总大小，就可以通过调用这个方法计算出来。比如网易新闻中就有这样一个功能，如下图所示：
	 * @return 
	 */
	public long size(){
		return 	mDiskLruCache.size();
	}
	
}
