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
			startActivity(new Intent(this,ColorTry.class));
			break;

		default:
			break;
		}
	}

}
