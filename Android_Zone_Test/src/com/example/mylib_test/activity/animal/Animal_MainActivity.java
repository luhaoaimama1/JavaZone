package com.example.mylib_test.activity.animal;
import com.example.mylib_test.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Animal_MainActivity extends Activity implements OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_animaltest);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.animal:
			startActivity(new Intent(this,AniPro.class));
			break;
		case R.id.color:
			startActivity(new Intent(this,ColorMarixTry.class));
			break;
		case R.id.bt_canvas:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "layer"));
			break;
		case R.id.bt_PorterDuff:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "porterDuff"));
			break;
		case R.id.bt_shader:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "shader"));
			break;
		case R.id.bt_surfaceView:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "surfaceView"));
			break;
		case R.id.bt_surface:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "bt_surface"));
			break;
		case R.id.bt_MatrixPre:
			startActivity(new Intent(this,CanvasTest.class).putExtra("type", "bt_MatrixPre"));
			break;
		case R.id.bt_Pixels:
			startActivity(new Intent(this,PixelsAcitivity.class));
			break;

		default:
			break;
		}
	}

}
