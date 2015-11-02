package com.example.mylib_test.activity.animal;

import view.testIng.ColorView;
import android.app.Activity;
import android.os.Bundle;

public class ColorTry extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new ColorView(this));
	}
}
