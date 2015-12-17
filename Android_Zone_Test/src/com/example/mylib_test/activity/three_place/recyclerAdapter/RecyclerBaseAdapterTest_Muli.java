package com.example.mylib_test.activity.three_place.recyclerAdapter;

import java.util.List;
import java.util.Map;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.recycler.Adapter_Zone_MuliRecycler;
import Android.Zone.Abstract_Class.recycler.Adapter_Zone_Recycler;
import Android.Zone.Abstract_Class.recycler.RecyclerHolder_Zone;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RecyclerBaseAdapterTest_Muli extends Adapter_Zone_MuliRecycler<String> {
	List<String> datas;

	public RecyclerBaseAdapterTest_Muli(Context context, List<String> data) {
		super(context, data);
		datas = data;
	}
	@Override
	public int[] setLayoutIDs() {
		return  new int[]{R.layout.item_recycler,R.layout.item_recycler_muptli};
	}
	@Override
	public void setData(Map<Integer, View> viewMap, String data, final int position,final RecyclerHolder_Zone holder) {
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

	@Override
	public int getItemViewType_Zone(int position, int[] layoutArr) {
		if(position%2==0){
			return  R.layout.item_recycler;
		}else{
			return  R.layout.item_recycler_muptli;	
		}
	}

	
}
