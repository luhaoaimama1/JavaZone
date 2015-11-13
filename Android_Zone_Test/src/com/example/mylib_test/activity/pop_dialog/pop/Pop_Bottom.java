package com.example.mylib_test.activity.pop_dialog.pop;
import com.example.mylib_test.R;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import Android.Zone.Abstract_Class.Pop_Zone;
import Android.Zone.Log.ToastUtils;

public class Pop_Bottom extends Pop_Zone implements OnClickListener{
	private Button bt_how,bt_are,bt_you;
	private Activity context;

	public Pop_Bottom(Activity activity, int layoutId,int showAtLocationViewId) {
		super(activity, layoutId, showAtLocationViewId, -1);
		this.context=activity;
	}

	@Override
	protected void findView(View mMenuView) {
		bt_how=(Button) mMenuView.findViewById(R.id.bt_how);
		bt_are=(Button) mMenuView.findViewById(R.id.bt_are);
		bt_you=(Button) mMenuView.findViewById(R.id.bt_you);
	}

	@Override
	protected void initData() {
		
	}

	@Override
	protected void setListener() {
		bt_how.setOnClickListener(this);
		bt_are.setOnClickListener(this);
		bt_you.setOnClickListener(this);
	}

	@Override
	protected void showPop(int showAtLocationViewId) {
		setBackground_Visibility(false);
		this.showAsDropDown(context.findViewById(R.id.pop_bottom),0, 0);
	}

	@Override
	public void onClick(View v) {
		ToastUtils.showLong(context, "ºÙºÙ");
		
	}

}
