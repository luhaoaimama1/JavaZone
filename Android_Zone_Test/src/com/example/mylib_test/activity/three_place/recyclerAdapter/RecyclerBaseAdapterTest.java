package com.example.mylib_test.activity.three_place.recyclerAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.example.mylib_test.R;

import Android.Zone.Abstract_Class.recyclerAdapter.AdapterRecycler_Zone;
import Android.Zone.Abstract_Class.recyclerAdapter.core.RecyclerHolder_Zone;
import android.content.Context;
import android.support.v7.widget.RecyclerView.AdapterDataObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 对比list  
 * 1 先remove remove的  然后 
 * 2.然后 判断添加的
 * @author Zone
 *
 */
public class RecyclerBaseAdapterTest extends AdapterRecycler_Zone<String> {
	List<String> datas;
	List<String> backUpDatas=new ArrayList<String>();

	public RecyclerBaseAdapterTest(Context context, List<String> data) {
		super(context, data);
		datas = data;
	}
	
	public void addData(String str) {
		for (int i = 2; i >=0; i--) {
			datas.add(2*i+1, str);
			notifyItemInserted(2*i+1);//插入1的动画	
		}
//		notifyItemMoved(1, 3);//1与3互换的动画
		
//		notifyItemChanged(1);
		
		
	}

	public void saveDataForAni(List<String> backUpDatas){
		this.backUpDatas.clear();
		for (String item : backUpDatas) {
			this.backUpDatas.add(item);
		}
	}
	public void notifyDataSetChangedWithAni(){
		List<Integer> removeList=new ArrayList<Integer>();
		for (int i = 0; i < backUpDatas.size(); i++) {
			if(!data.contains(backUpDatas.get(i)))
				removeList.add(i);
		}
		//倒叙即可
//		for (int i = removeList.size()-1;i>=0; i--) {
//			//这样 remove的去掉了 在对比就是add的了
//			notifyItemRemoved(removeList.get(i));
//			backUpDatas.remove(removeList.get(i));
//		}
		for (int i = 0; i < removeList.size(); i++) {
			//这样 remove的去掉了 在对比就是add的了
			notifyItemRemoved(removeList.get(i));
			backUpDatas.remove(removeList.get(i));
		}
		removeList.clear();
		for (int i = 0; i < data.size(); i++) {
			if(!backUpDatas.contains(data.get(i)))
				removeList.add(i);	
		}
		System.out.println();
		//这个则需要正序
		for (int i = 0; i < removeList.size(); i++) {
			notifyItemInserted(removeList.get(i));	
		}
//		for (int i = removeList.size()-1;i>=0; i--) {
//			notifyItemInserted(removeList.get(i));	
//		}
	}
//	public boolean dstContainSrcIndex(int index,List<String> src,List<String> dst){
//		//原数组  的index是否是 dst的index
//		boolean result=false;
//		int max=sr
//		switch (index) {
//		case 0:
//			
//			break;
//		case size:
//			
//			break;
//
//		default:
//			break;
//		}
//		return result;
//	}
	public void addAllData(String str) {
		datas.add(1, str);
		notifyDataSetChanged();
		
//		notifyItemRangeChanged(1, 3);、、
//		
//		for (int i = 2; i >=0; i--) {
//			datas.add(2*i+1, str);
//			notifyItemInserted(2*i+1);//插入1的动画	
//		}
//		for (int i = 2; i >=0; i--) {
//			datas.remove(2*i+4);
//			notifyItemRemoved(2*i+4);
//		}
		
	}

	public void deleteData() {
//		for (int i = 2; i >=0; i--) {
//			datas.remove(2*i+1);
//			notifyItemRemoved(2*i+1);
//		}
//		saveDataForAni(datas);
//		datas.remove(3);
//		datas.remove(2);
//		datas.remove(1);
		
		for (int i = 4; i >= 0; i--) {
			datas.add(1, "insert"+i);
		}
		notifyItemInserted(1);//在现在的窗口上的1的位置上添加一个  item带动画 并且数据是  现在data的1的数据
//		notifyDataSetChangedWithAni();
	
		
	}
	@Override
	public int setLayoutID() {
		return  R.layout.item_recycler;
	}

	@Override
	public void setData(RecyclerHolder_Zone holder, String data, int position) {
		TextView tem = (TextView) holder.findViewById(R.id.id_num);
		LinearLayout ll = (LinearLayout) holder.findViewById(R.id.ll_item);
//		ll.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				System.out.println("haha position:" + holder.getPosition()+"\t 参数 postion"+position);
//			}
//		});
		tem.setText(data);
	}

}
