package com.example.mylib_test.activity.custom_view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.Adapter_Zone;
import Android.Zone.ListView.RefreshZone;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RefreshListViewActivity extends Activity{
	RefreshZone rf_list;
	List<String> data=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_customview_refreshlist);
		rf_list=(RefreshZone) findViewById(R.id.rf_list);
		addData();
		rf_list.setAdapter(new Adapter_Zone<String>(this, data, R.layout.item_textview) {

			@Override
			public void setData(Map<Integer, View> viewMap, String data,
					int position) {
				TextView tv=(TextView) viewMap.get(R.id.tv);
				tv.setText(data);
			}
		});
	}
	private void addData() {
		for (int i = 0; i < 15; i++) {
			data.add("ÐéÄâÊý¾Ý£º"+i);
		}
	}
}
