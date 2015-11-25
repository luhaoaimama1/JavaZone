package com.example.mylib_test.activity.animal;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.animal.viewa.Canvas1;

import android.app.Activity;
import android.os.Bundle;

public class CanvasTest extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String type = getIntent().getStringExtra("type");
		if("layer".equals(type)){
			setContentView(new Canvas1(this));
		}
		if("porterDuff".equals(type)){
			setContentView(R.layout.a_porterduff_xfermode);
		}
		
		if("shader".equals(type)){
			setContentView(R.layout.a_shader);
		}
		
	
	}
}
