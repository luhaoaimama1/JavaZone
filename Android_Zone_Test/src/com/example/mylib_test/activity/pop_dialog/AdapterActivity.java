package com.example.mylib_test.activity.pop_dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.example.mylib_test.R;
import com.example.mylib_test.R.id;
import com.example.mylib_test.R.layout;
import com.example.mylib_test.activity.pop_dialog.entity.Item;
import com.google.gson.Gson;

import Android.Zone.Abstract_Class.Adapter_Zone;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class AdapterActivity extends Activity {
	private Adapter_Zone<Item> myAdapter;
	private int[] idArray;
	private List<Item> data=new ArrayList<Item>();
	private ListView listView ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adapter);
		init();
	}

	private void init() {
		listView=(ListView) findViewById(R.id.listView);
		for (int i = 0; i < 20; i++) {
			Item it=new Item();
			it.setTv1("tv11---"+i);
			it.setTv2("tv22---."+i);
			data.add(it);
			
		}
		idArray=new int[]{R.id.Item_Tv1,R.id.Item_Tv2};
		
		myAdapter = new Adapter_Zone<Item>(AdapterActivity.this, data, R.layout.item) {

			@Override
			public void setData(Map<Integer, View> map,Item data,int arg0) {
				((TextView)	map.get(R.id.Item_Tv1)).setText(data.getTv1());
				((TextView)	map.get(R.id.Item_Tv2)).setText( data.getTv2());
			}
			
		};
		listView.setAdapter(myAdapter);
	}
}
