package com.example.mylib_test.activity.three_place;

import java.util.ArrayList;
import java.util.List;

import com.example.mylib_test.R;
import com.example.mylib_test.activity.three_place.recyclerAdapter.RecyclerBaseAdapterTest;
import com.example.mylib_test.activity.three_place.recyclerAdapter.RvAdapter_Pull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

//瀑布刘　动画添加问题　　还有　onclick　位置问题　
public class RecyclerActivity extends Activity {

	private RecyclerView rv;
	private List<String> mDatas=new ArrayList<String>();
	private RvAdapter_Pull pullAdapter;
	 boolean isPull=false;
	private DefaultItemAnimator pullAni;
	private RecyclerBaseAdapterTest baseRecycler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_recycler);
		rv=(RecyclerView)findViewById(R.id.rv);
		for (int i = 'A'; i <='z'; i++) {
			mDatas.add("" + (char) i);
		}
		rv.setLayoutManager(new LinearLayoutManager(this));
//		noPullAdapter=new RvAdapter(this, mDatas);
		pullAdapter=new RvAdapter_Pull(this, mDatas);
		//基础测试
		baseRecycler=new RecyclerBaseAdapterTest(this, mDatas);
		rv.setAdapter(baseRecycler);
		// 设置item动画　　　此动画不设置默认也有
		pullAni=new DefaultItemAnimator();
		rv.setItemAnimator(pullAni);
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.recycler_menu, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case  R.id.all:
			if(isPull){
				pullAdapter.addAllData("insert All");
			}else{
				baseRecycler.addAllData("insert All");
			}
			break;
		case  R.id.add:
			if(isPull){
				pullAdapter.addData("insert one");
			}else{
				baseRecycler.addData("insert one");
			}
			break;
		case  R.id.delete:
			if(isPull){
				pullAdapter.deleteData();
			}else{
				baseRecycler.deleteData();
			}
			break;
		case  R.id.action_GirdView:
			rv.setLayoutManager(new GridLayoutManager(this,3));
			if(isPull){
				rv.setAdapter(baseRecycler);
			}
			isPull=false;
			break;
		case  R.id.action_ListView:
			rv.setLayoutManager(new LinearLayoutManager(this));
			if(isPull){
				rv.setAdapter(baseRecycler);
			}
			isPull=false;
			break;
		case  R.id.action_pull:
			rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
			if(!isPull){
				rv.setAdapter(pullAdapter);
			}
			isPull=true;
			break;
		case  R.id.action_H_GirdView:
			rv.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
			if(isPull){
				//可以在适配器动态修改也可以在外部修改
				rv.setAdapter(baseRecycler);
			}
			isPull=false;
			break;
		case  R.id.baseAdapter:
			rv.setLayoutManager(new LinearLayoutManager(this));
			if(isPull){
				//可以在适配器动态修改也可以在外部修改
				rv.setAdapter(new RecyclerBaseAdapterTest(this, mDatas));
			}
			isPull=false;
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}


}
