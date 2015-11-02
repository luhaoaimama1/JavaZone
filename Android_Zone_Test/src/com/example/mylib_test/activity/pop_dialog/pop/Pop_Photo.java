package com.example.mylib_test.activity.pop_dialog.pop;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.Pop_Zone;
import Android.Zone.Log.ToastUtils;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Pop_Photo extends Pop_Zone implements OnClickListener{
	//控件
	private TextView tv_goodsNumber;
	private TextView tv_call;
	private TextView tv_cancel;
	public Pop_Photo(Activity activity,int layoutId, int showAtLocationViewId,int dismissViewId) {
		super(activity, layoutId, showAtLocationViewId, dismissViewId);
//		setBackground_Visibility(false);
//		setBackgroundColor(Color.CYAN);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_call:
			ToastUtils.showLong(activity, "haha好使不");
			break;
		case R.id.tv_cancel:
			dismiss();
			break;

		default:
			break;
		}
	}

	@Override
	protected void findView(View mMenuView) {
		 tv_goodsNumber=(TextView) mMenuView.findViewById(R.id.tv_goodsNumber);
		 tv_call=(TextView) mMenuView.findViewById(R.id.tv_call);
		 tv_cancel=(TextView) mMenuView.findViewById(R.id.tv_cancel);
	}

	@Override
	protected void initData() {
		tv_goodsNumber.setText("gaga");
	}

	@Override
	protected void setListener() {
		tv_call.setOnClickListener(this);
		tv_cancel.setOnClickListener(this);
	}

	@Override
	protected void showPop(int showAtLocationViewId) {
		this.setAnimationStyle(R.style.PopSelectPicAnimation);
		this.showAtLocation(activity.findViewById(showAtLocationViewId),Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);	
	}


}
