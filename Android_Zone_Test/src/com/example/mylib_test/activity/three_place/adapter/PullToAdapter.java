package com.example.mylib_test.activity.three_place.adapter;

import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import Android.Zone.Abstract_Class.Adapter_Zone;

public class PullToAdapter extends Adapter_Zone<String>{

	private TextView tv;

	public PullToAdapter(Context context, List<String> data) {
		super(context, data);
	}

	@Override
	public void setData(Map<Integer, View> viewMap, String data, int position) {
		tv=(TextView)viewMap.get(R.id.tv);
		tv.setText(data);
	}

	@Override
	public int setLayoutID() {
		return  R.layout.item_threethird_pull;
	}

}
