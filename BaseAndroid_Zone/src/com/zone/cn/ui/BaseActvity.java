package com.zone.cn.ui;

import com.nostra13.universalimageloader.core.ImageLoader;

import Android.Zone.Sqlite.Sqlite_Utils;
import Android.Zone.Utils.ScreenUtils;
import android.os.Bundle;
import android.os.Message;
import android.os.Handler.Callback;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActvity extends FragmentActivity implements Callback{
	public static int screenW;
	public static int screenH;
	protected ImageLoader imageLoader;
	protected Sqlite_Utils dao;
	@Override
	protected void onCreate(Bundle arg0) {
		long time = System.currentTimeMillis();
		super.onCreate(arg0);
		getScreenPix();
		dao=Sqlite_Utils.getInstance(this);
		imageLoader = ImageLoader.getInstance();
		setContentView();
		findIDs();
		initData();
		setListener();
		time=System.currentTimeMillis()-time;
		System.out.println("base耗时："+time);
	}

	private void getScreenPix() {
		int[] screen = ScreenUtils.getScreenPix(this);
		screenW=screen[0];
		screenH=screen[1];
	}

	/**
	 * 设置子类布局对象
	 */
	public abstract void setContentView();

	/**
	 * 子类查找当前界面所有id
	 */
	public abstract void findIDs();

	/**
	 * 子类初始化数据
	 */
	public abstract void initData();

	/**
	 * 子类设置事件监听
	 */
	public abstract void setListener();
	@Override
	public boolean handleMessage(Message msg) {
		return false;
	}
}
