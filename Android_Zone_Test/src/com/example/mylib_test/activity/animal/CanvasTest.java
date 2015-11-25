package com.example.mylib_test.activity.animal;

import com.example.mylib_test.activity.animal.viewa.Canvas1;

import android.app.Activity;
import android.os.Bundle;

public class CanvasTest extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new Canvas1(this));
	}
}
