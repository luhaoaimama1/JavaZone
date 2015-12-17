package com.example.mylib_test.activity.three_place.recyclerAdapter;

import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.recycler.Adapter_Zone_Recycler;
import Android.Zone.Abstract_Class.recycler.MyRecyclerHolder;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerBaseAdapterTest extends Adapter_Zone_Recycler<String> {
	List<String> datas;

	public RecyclerBaseAdapterTest(Context context, List<String> data) {
		super(context, data, R.layout.item_recycler);
		datas = data;
	}

	@Override
	public void setData(Map<Integer, View> viewMap, String data, final int position,final MyRecyclerHolder holder) {
		TextView tem = (TextView) viewMap.get(R.id.id_num);
		LinearLayout ll = (LinearLayout) viewMap.get(R.id.ll_item);
		ll.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				System.out.println("haha position:" + holder.getPosition());
			}
		});
		tem.setText(data);
	}

	public void addData(String str) {
		datas.add(1, str);
		notifyItemInserted(1);
	}

	public void addAllData(String str) {
		datas.add(1, str);
		notifyDataSetChanged();
	}

	public void deleteData() {
		datas.remove(1);
		notifyItemRemoved(1);
	}
}
