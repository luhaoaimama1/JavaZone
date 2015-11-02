package com.example.mylib_test.activity.http;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;

import com.example.mylib_test.R;
import com.example.mylib_test.handler.HandlerTest;

import Android.Zone.Http.MyConn_Utils;
import Android.Zone.Http.Client.MyHttpFilePostThread;
import Android.Zone.Http.Client.MyHttpGetThread;
import Android.Zone.Http.Client.MyHttpPostThread;
import Android.Zone.Http.DownFile.DownLoader;
import Android.Zone.Http.DownFile.DownLoader.ProgressListener;
import Android.Zone.Http.MyConn_Utils.CallBack;
import Android.Zone.Http.MyConn_Utils.FileUpLoad_CallBack;
import Android.Zone.SD.FileUtils_SD;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Http_MainActivity extends Activity implements OnClickListener{
	 final	String UrlPath = "http://192.168.1.102:8008/Test/log";
	Map<String,Object> map= new HashMap<String, Object>();
	Map<String, String> params = new HashMap<String, String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_http_test);
	}
	@Override
	public void onClick(View v) {
		map.clear();
		params.clear();
		client(v);
		conn(v);
		switch (v.getId()) {
		case R.id.handle:
			Button hand=(Button) findViewById(R.id.handle);
			HandlerTest ht=new HandlerTest();
			ht.updateView(hand);
			break;

		default:
			break;
		}
	}
	private void client(View v) {
		switch (v.getId()) {
		case R.id.client_get:
		new MyHttpGetThread(UrlPath+"?un=8&kb=ga", "UTF-8") {
			
			@Override
			public void onSuccess(HttpResponse response, String responseStr) {
				System.out.println(responseStr);
			}
		}.start();;
			break;
		case R.id.client_post:
			params.put("userName", "唉");
			params.put("passWord", "123");
			new MyHttpPostThread(UrlPath, params, "UTF-8") {
				@Override
				public void success(HttpResponse response, String responseStr) {
					Log.i("MyHttpPostThread", responseStr);
//					System.out.println("回来的值:" + responseStr);
				}
			}.start();
			break;
		case R.id.client_upload:
			//测试 封装的 文件提交表单
			File f = new File(FileUtils_SD.FolderCreateOrGet(""), "高达 - 00.mp3");
//			DCIM\Camera
			File f2 = new File(FileUtils_SD.FolderCreateOrGet("DCIM","Camera"), "20150621_121327.jpg");
			map.put("String_uid", "love");
			map.put("File_upload", f);
			map.put("File_upload2", f2);	
			new MyHttpFilePostThread(UrlPath, map, "utf-8") {
			@Override
			public void success(HttpResponse response, String responseStr) {
				Log.e("result嘎嘎", "response的值:" + responseStr);
			}
		}.start();

		default:
			break;
		}
	}
	private void conn(View v) {
		switch (v.getId()) {
		case R.id.con_get:
			MyConn_Utils.getInstance().executeHttpGet(UrlPath+"?un=8&kb=ga", "utf-8", new CallBack() {
				
				@Override
				public void onSuccess(String msg) {
					System.out.println(msg);
				}
				
				@Override
				public void onFailure(String msg) {
					System.err.println(msg);
				}
			});
			break;
		case R.id.con_post:
			params.put("un", "luhaoaimama7");
			params.put("fr", "index");
			MyConn_Utils.getInstance().setNotPrintHeaders(true);
			MyConn_Utils.getInstance().executeHttpPost(UrlPath, params, "utf-8", new CallBack() {
				
				@Override
				public void onSuccess(String msg) {
					System.out.println(msg);
				}
				
				@Override
				public void onFailure(String msg) {
					
				}
			});
			break;
		case R.id.con_down:
//			String urlPath="http://img4.freemerce.com/ci49h5p.jpg";
			String urlPath="http://down.360safe.com/360/inst.exe";
			DownLoader b=DownLoader.INSTANCE;
			final Button connectionDown=(Button) findViewById(R.id.con_down);
			b.downLoader(urlPath,  FileUtils_SD.FolderCreateOrGet(""), 4,new ProgressListener() {

				@Override
				public void onProgressUpdate(int current, int total,
						float progress) {
					connectionDown.setText(progress+"");
					System.out.println("current:"+current+"  total:"+total+"  progress:"+progress);
				}
				
			});
			break;
		case R.id.con_upload:
			//测试 封装的 文件提交表单
			File f = new File(FileUtils_SD.FolderCreateOrGet(""), "高达 - 00.mp3");
//			DCIM\Camera
			File f2 = new File(FileUtils_SD.FolderCreateOrGet("DCIM","Camera"), "20150621_121327.jpg");
			map.put("String_uid", "love");
			map.put("File_upload", f);
			map.put("File_upload2", f2);
			MyConn_Utils.getInstance().executeHttpFile(UrlPath, map, "utf-8", new FileUpLoad_CallBack() {
				
				@Override
				public void onSuccess(String msg) {
					System.out.println(msg);
				}
				
				@Override
				public void onStart() {
					
				}
				@Override
				public void onLoading(Long total, Long current) {
					System.out.println("total:"+total+"/tcurrent"+current+"\t百分比："+(float)current/total);
				}
				
				@Override
				public void onFailure(String msg) {
					
				}
			});

		default:
			break;
		}
	}


}
