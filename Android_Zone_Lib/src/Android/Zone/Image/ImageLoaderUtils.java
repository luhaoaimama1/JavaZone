package Android.Zone.Image;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
public class ImageLoaderUtils {
	/**
	 * 	 例子： http的话 任何人都会用就不封装里面了
		<br>String imageUri = "http://site.com/image.png"; // 网络图片  
		<br>String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片  
		<br>String imageUri = "content://media/external/audio/albumart/13"; // 媒体文件夹  
		<br>String imageUri = "assets://image.png"; // assets  
		<br>String imageUri = "drawable://" + R.drawable.image; //  drawable文件  
	 *
	 */
	public enum Type{
		File("file://"),Assets("assets://"),Drawable("drawable://"),Content("content://");
		private String str;
		private Type(String str) {
			this.str=str;
		}
		public String getStr() {
			return str;
		}
	}
	public void displayImage(ImageView imageView,String uri,Type type){
		uri=type.getStr()+uri;
		ImageLoader.getInstance().displayImage(uri, imageView,new ImageLoadingListener() {
			
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub
				
			}
		});
	}

    /**
     * 显示图片的所有配置
     * @return
     */
//    private DisplayImageOptions getWholeOptions() {
//        DisplayImageOptions options = new DisplayImageOptions.Builder()  
//        .showImageOnLoading(R.drawable.loading) //设置图片在下载期间显示的图片  
//        .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片  
//        .showImageOnFail(R.drawable.error)  //设置图片加载/解码过程中错误时候显示的图片
//        .cacheInMemory(true)//设置下载的图片是否缓存在内存中  
//        .cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中  
//        .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
//        .imageScaleType(ImageScaleType.IN_SAMPLE_INT)//设置图片以如何的编码方式显示  
//        .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
//        //.decodingOptions(BitmapFactory.Options decodingOptions)//设置图片的解码配置  
//        .delayBeforeLoading(0)//int delayInMillis为你设置的下载前的延迟时间
//        //设置图片加入缓存前，对bitmap进行设置  
//        //.preProcessor(BitmapProcessor preProcessor)  
//        .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位  
//        .displayer(new RoundedBitmapDisplayer(20))//不推荐用！！！！是否设置为圆角，弧度为多少  
//        .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间，可能会出现闪动
//        .build();//构建完成
//        
//        return options;
//    }
}
