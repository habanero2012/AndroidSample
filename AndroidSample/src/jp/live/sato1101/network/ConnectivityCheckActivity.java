/**
 * Networkの接続性とネットワークのタイプ(Wifi,mobile)を判定する
 * 
 * android.permission.ACCESS_NETWORK_STATE の許可が必要
 */
package jp.live.sato1101.network;

import jp.live.sato1101.R;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ConnectivityCheckActivity extends Activity {
	
	private TextView mConnectedText;
	private TextView mWifiText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network_connectivity_check_activity);
		
		mConnectedText = (TextView) findViewById(R.id.network_connectivity_check_activity_conn_value);
		mWifiText = (TextView) findViewById(R.id.network_connectivity_check_activity_is_wifi_value);
		
		Button btn = (Button) findViewById(R.id.network_connectivity_check_activity_btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					if(isNetworkReachable()) {
						mConnectedText.setText("connected");
					} else {
						mConnectedText.setText("disconnected");
					}
					
					if(isWifiReachable()) {
						mWifiText.setText("Wifi enable");
					} else {
						mWifiText.setText("Wifi unable");
					}
			}
		});
	}
	
	/**
	 * ネットワークが使えるか判定する
	 * @return ネットワークに接続している場合、trueを返す
	 */
	private boolean isNetworkReachable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo current = manager.getActiveNetworkInfo();
		if(current == null) {
			return false;
		}
		return (current.getState() == NetworkInfo.State.CONNECTED);
	}
	
	
	/**
	 * Wifi接続しているか判定する
	 * @return wifi接続の場合、trueを返す
	 */
	private boolean isWifiReachable() {
		ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo current = manager.getActiveNetworkInfo();
		if(current == null) {
			return false;
		}
		return (current.getType() == ConnectivityManager.TYPE_WIFI);
	}
}
