package com.example.mylib_test.activity.wifi;

import com.example.mylib_test.R;
import Android.Zone.Log.ToastUtils;
import Android.Zone.Wifi.MyWifiAnd3G;
import Android.Zone.Wifi.NetManager;
import Android.Zone.Wifi.NetManager.NetStatue;
import Android.Zone.Wifi.NetStatusReceiver.NetWorkListener;
import Android.Zone.Wifi.NetStatusReceiverUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Wifi3g_MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_wifitest);
	}
	@Override
	public void onClick(View v) {
		MyWifiAnd3G mwa = new MyWifiAnd3G(this);
		switch (v.getId()) {
		case R.id.openWifi:
			// 打开wifi
			mwa.openWifi();
			break;
		case R.id.closeWifi:
			// 关闭wifi
			mwa.closeWifi();
			break;
		case R.id.connWifi:
			Intent intent2 = new Intent(this, WifiAdapterActivity.class);
			startActivity(intent2);
			break;
		case R.id.conn3G:
			// 连接3g手机
			mwa.openOrClose3GNet(true);
			break;
		case R.id.getNetStatue:
			ToastUtils.showLong(this, NetManager.getNetStatue(this).toString());
			break;
		case R.id.unRegister:
			NetStatusReceiverUtils.unRegister(this);
			break;
		case R.id.register:
			NetStatusReceiverUtils.register(this, new NetWorkListener() {
				@Override
				public void netWorkChange(NetStatue status) {
					ToastUtils.showLong(Wifi3g_MainActivity.this, "变成:"+status.toString());
				}
			});
			break;

		default:
			break;
		}
	}

}
