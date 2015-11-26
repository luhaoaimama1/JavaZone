package com.example.mylib_test.activity.animal;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.animal.viewa.Canvas1;
import com.example.mylib_test.activity.animal.viewa.MatrixView;
import com.example.mylib_test.activity.animal.viewa.SimpleDraw;
import com.example.mylib_test.activity.animal.viewa.SinView;

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
		
		if("surfaceView".equals(type)){
			setContentView(new SinView(this));
		}
		if("bt_surface".equals(type)){
			setContentView(new SimpleDraw(this));
		}
		if("bt_MatrixPre".equals(type)){
				setContentView(new MatrixView(this));
			}
		
	
	}
}
