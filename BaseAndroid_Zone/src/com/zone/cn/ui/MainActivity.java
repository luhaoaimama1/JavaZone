package com.zone.cn.ui;
import java.util.List;

import Android.Zone.Utils.TypefaceUtils;
import Java.Zone.Log.PrintUtils;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.zone.cn.Apps;
import com.zone.cn.R;
import com.zone.cn.entity.User;
import com.zone.cn.sqlite.DbManager;
import com.zone.cn.widget.LaunchDialog;

public class MainActivity extends BaseActvity  implements OnClickListener{
	private Button button1;
	private ImageView iv_guide;
	@Override
	public void setContentView() {
		if(Apps.openCount==0){
			LaunchDialog	dialog=new LaunchDialog(this);
			dialog.showDialog();	
		}
		setContentView(R.layout.a_main);
		if(Apps.openCount==0)
			mattePage();
	}

	private void mattePage() {
		iv_guide=(ImageView) findViewById(R.id.iv_guide);
		iv_guide.setOnClickListener(this);
		iv_guide.setVisibility(View.VISIBLE);
	}

	@Override
	public void findIDs() {
		button1=(Button) findViewById(R.id.button1);
	}

	@Override
	public void initData() {
		TypefaceUtils.useTypeface(this, button1);
		 
	}

	@Override
	public void setListener() {
		testDb();
	}
	private void testDb() {
		DbManager.setUser(dao, "sunhao", "123456");
		List<User> list = DbManager.getUser(dao, "fuzhipeng");
		for (User user : list) {
			 PrintUtils.printAllField(user);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_guide:
			//如果有 处理 没有也没事
			iv_guide.setVisibility(View.GONE);
			iv_guide.setClickable(false);
			break;
		default:
			break;
		}
	}



}
