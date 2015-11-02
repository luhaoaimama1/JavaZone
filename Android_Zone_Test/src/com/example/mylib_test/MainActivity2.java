package com.example.mylib_test;

import java.util.Map;

import com.example.mylib_test.activity.db.entity.MenuEntity;

import Android.Zone.Abstract_Class.Adapter_Zone;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity2 extends Activity{
	private ListView listView1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_menu);
		listView1=(ListView) findViewById(R.id.listView1);
		final int[] colorArry={Color.WHITE,Color.GREEN,Color.YELLOW,Color.CYAN};
		listView1.setAdapter(new Adapter_Zone<MenuEntity>(this,MainMenu.menu, R.layout.item_menu) {

			@Override
			public void setData(Map<Integer, View> viewMap, MenuEntity data,
					final int position) {
				TextView tv=(TextView) viewMap.get(R.id.tv);
				tv.setText(MainMenu.menu.get(position).info);
				tv.setBackgroundColor(colorArry[position%colorArry.length]);
				tv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startActivity(new Intent(MainActivity2.this, MainMenu.menu.get(position).goClass));
					}
				});
			}
		});
	}

}
