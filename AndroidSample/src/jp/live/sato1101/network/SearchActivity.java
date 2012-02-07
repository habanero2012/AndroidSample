package jp.live.sato1101.network;

import java.net.URI;
import java.net.URISyntaxException;

import jp.live.sato1101.network.RestTask.RestTaskReceiver;

import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends Activity{

	private static final String SEARCH_ACTION = "jp.live.sato1101.SEARCH";
//	private static final String SEARCH_URI = 
//			"http://search.yahooapis.com/WebSearchService/V1/webSearch?appid=%s&query=%s";
	private static final String SEARCH_URI = "http://www.google.co.jp";
	
	private TextView mTextView;
	private ProgressDialog mProgress;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mTextView = new TextView(this);
		mTextView.setText("request http get");
		setContentView(mTextView);
		
//		String url = String.format(SEARCH_URI, "YahooDemo","Android");
		String url = SEARCH_URI;
		try {
			HttpGet searchRequest = new HttpGet(new URI(url));
			RestTask task = new RestTask(this, SEARCH_ACTION);
			task.execute(searchRequest);
			// ユーザーに進捗を表示する
			mProgress = ProgressDialog.show(this, "Searching", "Waiting For Results...", true);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		mReceiver.registerReceiver(this, SEARCH_ACTION);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mReceiver.unregisterReceiver(this);
	}
	
	private RestTaskReceiver mReceiver = new RestTaskReceiver() {
		
		@Override
		public void onReceive(String response) {
			if(mProgress != null) {
				mProgress.dismiss();
			}
			mTextView.setText(response);
		}
	};
}
