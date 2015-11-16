package com.example.mylib_test.activity.touch;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.touch.view.ScrollerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;

public class TouchMainActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_touch_main);
		int a = ViewConfiguration.get(this).getScaledTouchSlop();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.veclocityTracker:
			startActivity(new Intent(this,VeclocityTrackerActivity.class));
			break;
		case R.id.gestureDetectorActivity:
			startActivity(new Intent(this,GestureDetectorActivity.class));
			break;
		case R.id.scrollerView:
			startActivity(new Intent(this,ScrollerActivity.class));
			break;
		case R.id.conflict1:
			startActivity(new Intent(this,Conflict1Activity.class));
			break;

		default:
			break;
		}
		
	}

}
