package Android.Zone.Utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class ScreenUtils {
	public static int screenW;
	public static int screenH;
	
	public static int[] getScreenPix(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int[] screen=new int[2];
		screenW=dm.widthPixels;
		screenH=dm.heightPixels;
		
		screen[0]=screenW;
		screen[1]=screenH;
		return screen;
	}
	public static void  requestFillWindow(Activity context){
		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	public static void  requestNoTitle(Activity context){
		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
}
