package com.example.mylib_test.activity.photo_shot;

import com.example.mylib_test.R;
import com.example.mylib_test.R.id;
import com.example.mylib_test.R.layout;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowPicActivity extends Activity {
	private ImageView iv_show_pic;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pic_show);
		iv_show_pic = (ImageView) findViewById(R.id.iv_show_pic);
		Uri uri = getIntent().getData();
		if (uri != null) {
			iv_show_pic.setImageURI(uri);
		}

	}

}
