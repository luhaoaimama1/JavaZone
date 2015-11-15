package Android.Zone.Image;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
//һ��app����һ��LruCache
public class LruCacheUtils {
	private static LruCacheUtils instance=new LruCacheUtils();
	private static final String TAG = "LruCacheUtils";
	private LruCache<String, Bitmap> mMemoryCache;
	private int MAXMEMONRY = (int) (Runtime.getRuntime() .maxMemory() / 1024);
	public static LruCacheUtils getInstance(){
		return instance;
	}
	private LruCacheUtils() {
	        if (mMemoryCache == null)
	            mMemoryCache = new LruCache<String, Bitmap>( MAXMEMONRY / 8) {
	                @Override
	                protected int sizeOf(String key, Bitmap bitmap) {
	                    // ��д�˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ������
	                    return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
	                }
	            };
	    }
	public void clearCache() {
        if (mMemoryCache != null) {
            if (mMemoryCache.size() > 0) {
                Log.d(TAG, "mMemoryCache.size() " + mMemoryCache.size());
                mMemoryCache.evictAll();
                Log.d(TAG, "mMemoryCache.size()" + mMemoryCache.size());
            }
            mMemoryCache = null;
        }
    }

    public synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (mMemoryCache.get(key) == null) {
            if (key != null && bitmap != null)
                mMemoryCache.put(key, bitmap);
        } else
            Log.w(TAG, "the res is aready exits");
    }

    public synchronized Bitmap getBitmapFromMemCache(String key) {
        Bitmap bm = mMemoryCache.get(key);
        if (key != null) {
            return bm;
        }
        return null;
    }

    /**
     * �Ƴ�����
     * 
     * @param key
     */
    public synchronized void removeImageCache(String key) {
        if (key != null) {
            if (mMemoryCache != null) {
                Bitmap bm = mMemoryCache.remove(key);
                if (bm != null)
                    bm.recycle();
            }
        }
    }
}