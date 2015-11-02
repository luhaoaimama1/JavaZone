package com.example.mylib_test.activity.utils;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.frag_viewpager_expand.FramentSwitchAcitiviy;

import Android.Zone.Utils.KeyBoardUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class Utils_MainActivity extends Activity implements OnClickListener{
	private EditText keyboard;
	private View view1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_utils_test);
		keyBoardTest();
		

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;  // ÆÁÄ»¿í¶È£¨ÏñËØ£©
        int height = metric.heightPixels;  // ÆÁÄ»¸ß¶È£¨ÏñËØ£©
        float density = metric.density;  // ÆÁÄ»ÃÜ¶È£¨0.75 / 1.0 / 1.5£©
        int densityDpi = metric.densityDpi;  // ÆÁÄ»ÃÜ¶ÈDPI£¨120 / 160 / 240£©
        Log.e("Utils_MainActivity:¡ª¡ª¡ª¡ª¡ª¡ª¡ª¡ª-" + "  DisplayMetrics", "density=" + density + "; densityDPI=" + densityDpi);  
        float tem = getResources().getDimension(R.dimen.test);
        System.err.println(tem);
	
	}

	private void keyBoardTest() {
		keyboard=(EditText) findViewById(R.id.keyboard);
		view1=findViewById(R.id.view1);
		new KeyBoardUtils() {
			
			@Override
			public void openState(int keyboardHeight) {
				System.out.println("¼üÅÌ£ºopenState ¸ß¶È:"+keyboardHeight);
			}
			
			@Override
			public void closeState(int keyboardHeight) {
				System.out.println("¼üÅÌ£ºcloseState ¸ß¶È:"+keyboardHeight);
			}
		}.monitorKeybordState(findViewById(R.id.flowLayoutZone1));;	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.openKeyboard:
			KeyBoardUtils.openKeybord(keyboard, this);
			break;
		case R.id.closeKeyboard:
			KeyBoardUtils.closeKeybord(keyboard, this);
			break;
		case R.id.frammentSwitch:
			startActivity(new Intent(this,FramentSwitchAcitiviy.class));
			break;

		default:
			break;
		}
		
	}

}
