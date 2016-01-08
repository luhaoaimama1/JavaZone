package com.example.mylib_test.app;
/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
import Android.Zone.Image.ImageLoader.ImageLoaderConfigUtils;
import Android.Zone.Image.ImageLoader.ImageLoaderOptionsUtils;
import Android.Zone.Log.Logger_Zone;
import Android.Zone.Log.Logger_Zone.LogStatue;
import Android.Zone.Network.core.NetworkEngine;
import Android.Zone.Network.engine.XutilsEngine;
import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.example.mylib_test.R;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public class Apps extends Application {

	// SDCard路径
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressWarnings("unused")
	@Override
	public void onCreate() {
		super.onCreate();
		if (false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
			StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
		}
		
		NetworkEngine.setGlobalEngine(XutilsEngine.class);
		//全局控制　打印日志
		Logger_Zone.setAllLogStatue(LogStatue.Child_Control,false);
		
		//初始化 sqlite
		ImageLoaderOptionsUtils.initShowImage(R.drawable.ic_stub, R.drawable.ic_empty, R.drawable.ic_error);
		//初始化ImageLoader
		ImageLoaderConfigUtils.initImageLoader(getApplicationContext(),ImageLoaderOptionsUtils.getNormalOption＿NotBuild().build(),true);
		
//		CrashHandler.getInstance().init(this);

//		每次loading页 发送bug日志  如果发送成功就删除
//		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(
//				this));


	}
}
