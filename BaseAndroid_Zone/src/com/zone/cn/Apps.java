package com.zone.cn;

import java.io.File;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.zone.cn.cache.FileCache;
import com.zone.cn.entity.User;
import Android.Zone.Sqlite.Sqlite_Utils;
import Android.Zone.Sqlite.Sqlite_Utils.OnCreate;
import Android.Zone.Sqlite.Sqlite_Utils.OnUpgrade;
import Android.Zone.Utils.SharedUtils;
import Android.Zone.Utils.TypefaceUtils;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.StrictMode;

public class Apps extends Application{
	
	protected SharedPreferences readSp;
	protected Editor writeSp;
	public static long  openCount=0;
	@Override
	public void onCreate() {
		long time = System.currentTimeMillis();
		super.onCreate();
		
		TypefaceUtils.initTTF("fonts/zhanghaishan2.0.ttf");
		recordLoginCount();
		
		configureSqlit();
		configureImageLoader();
		
		time=System.currentTimeMillis()-time;
		System.out.println("耗时："+time);
		
	}
	/**
	 * 配置 ImageLoader
	 */
	private void configureImageLoader() {
		if (false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		}
		initImageLoader(getApplicationContext());
	}
	/**
	 * 配置 sqlite
	 */
	private void configureSqlit() {
		Sqlite_Utils.setPrintLog(false);
		Sqlite_Utils.init_listener(Apps.this, new OnCreate() {
			@Override
			public void onCreateTable(Sqlite_Utils instance) {
				instance.createTableByEntity(User.class);
			}
		}, new OnUpgrade() {
			@Override
			public void annoColumn_DeleOrUpdate(Sqlite_Utils instance2) {

			}

			@Override
			public void onUpgrade(int oldVersion, int newVersion,
					Sqlite_Utils instance2) {
				// System.err.println("oldVersion:" + oldVersion);
				// System.err.println("newVersion:" + newVersion);
				// instance2.dropTableByClass(DbEntity.class);

			}
		});	
	}
	/**
	 * 记录打开次数
	 */
	private void recordLoginCount() {
		readSp = SharedUtils.getInstance(this).readSp();
		writeSp=SharedUtils.getInstance(this).writeSp();
		openCount = readSp.getLong("openCount", 0);
		writeSp.putLong("openCount", openCount+1).commit();
	}
	/**
	 * 内存溢出的话 可以找笔记
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		// This configuration tuning is custom. You can tune every option, you may tune some of them,
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		//路径是：/data/data/com.example.mylib_test/cache 要加image
		File cacheDir =FileCache.imageLoaderCacheFile;
		
		ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		/** ==========================线程方面 =========================*/		
		// 设置显示图片线程池大小，默认为3
		config.threadPoolSize(3);
		// 设定线程等级比普通低一点
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		//设置用于加载和显示图像的任务的队列处理类型。
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		/** ==========================内存缓存  =========================*/
		//图片解码期间  中bitmap的宽高 默认是  手机宽高  
//		config.memoryCacheExtraOptions(480, 800);
		// 设定内存缓存 
		config.memoryCache(new LruMemoryCache(2 * 1024 * 1024));
		//缓存到内存的最大数据
		config.memoryCacheSize(2 * 1024 * 1024); 
		//文件数量
		config.diskCacheFileCount(1000); 
		// 设定只保存同一尺寸的图片在内存
		config.denyCacheImageMultipleSizesInMemory();
		/** ==========================文件缓存  =========================*/
		//下载图片后 compress保存到文件中的 宽高
//		config.diskCacheExtraOptions(480, 800, null);
		// 设定缓存的SDcard目录，UnlimitDiscCache速度最快
		config.diskCache(new UnlimitedDiskCache(cacheDir));
		// 设定缓存到SDCard目录的文件__命名!
		config.diskCacheFileNameGenerator(new HashCodeFileNameGenerator()); 
		//缓存到文件的最大数据   50 MiB
		config.diskCacheSize(50 * 1024 * 1024); 
		/** ==========================超时 与log打印  =========================*/
		// 设定网络连接超时 timeout: 10s 读取网络连接超时read timeout: 20s
		config.imageDownloader(new BaseImageDownloader(context, 10000, 20000));
		config.defaultDisplayImageOptions(
				new DisplayImageOptions.Builder()
						.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
						.cacheInMemory(true)
						.resetViewBeforeLoading(true)
						.cacheOnDisk(true)
						.bitmapConfig(Bitmap.Config.RGB_565)
						.build());
		//打印log
		config.writeDebugLogs(); // Remove for release app
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config.build());
	}
}
