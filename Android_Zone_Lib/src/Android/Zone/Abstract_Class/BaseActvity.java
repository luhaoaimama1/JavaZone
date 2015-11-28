package Android.Zone.Abstract_Class;
import com.nostra13.universalimageloader.core.ImageLoader;

import Android.Zone.Constant;
import Android.Zone.Log.Logger_Zone;
import Android.Zone.Utils.ScreenUtils;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActvity extends FragmentActivity implements Callback{
	
	protected ImageLoader imageLoader;
	private Logger_Zone logger;
	@Override
	protected void onCreate(Bundle arg0) {
		logger= new  Logger_Zone(Adapter_MultiLayout_Zone.class,Constant.Logger_Config);
		logger.closeLog();
		logger.log("BaseActvity  onCreate");
		super.onCreate(arg0);
		ScreenUtils.getScreenPix(this);
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
}
