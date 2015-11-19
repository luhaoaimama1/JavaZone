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
import android.annotation.TargetApi;
import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import com.example.mylib_test.app.config.ImageLoaderConfig;

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
		//初始化 sqlite
		//初始化ImageLoader
		ImageLoaderConfig.initImageLoader(getApplicationContext());
		CrashHandler.getInstance().init(this);
	}
}
