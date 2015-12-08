package Android.Zone.Abstract_Class;
import java.util.ArrayList;
import java.util.List;
import com.nostra13.universalimageloader.core.ImageLoader;
import Android.Zone.Constant;
import Android.Zone.Log.Logger_Zone;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseActvity extends FragmentActivity implements Callback,OnClickListener{
	
	protected ImageLoader imageLoader;
	private Logger_Zone logger;
	public static List<Activity> activitys=new ArrayList<Activity>();
	@Override
	protected void onCreate(Bundle arg0) {
		activitys.add(this);
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
		logger.log("BaseActvity  onCreate");
		super.onCreate(arg0);
		imageLoader = ImageLoader.getInstance();
		logger.log("BaseActvity  setContentView");
		setContentView();
		findIDs();
		initData();
		setListener();
	}


	/**
	 * 设置子类布局对象
	 */
	public abstract void setContentView();

	/**
	 * 子类查找当前界面所有id
	 */
	public abstract void findIDs();

	/**
	 * 子类初始化数据
	 */
	public abstract void initData();

	/**
	 * 子类设置事件监听
	 */
	public abstract void setListener();
	@Override
	public boolean handleMessage(Message msg) {
		return false;
	}
	@Override
	public void onClick(View v) {
		
	}
	@Override
	protected void onDestroy() {
		/*
		 * 防止内存泄漏
		 */
		activitys.remove(this);
		super.onDestroy();
	}
	/**
	 * 结束所有 还存在的activitys  一般在异常出现的时候
	 */
	public void finishAll() {
		for (Activity item : activitys) {
			item.finish();
		}
	}
}
