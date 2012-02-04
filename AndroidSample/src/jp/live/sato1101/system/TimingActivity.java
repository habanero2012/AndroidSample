/*
 * Handlerクラスを使った周期タスクの作成
 */

package jp.live.sato1101.system;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class TimingActivity extends Activity {

	private TextView mTextView;
	private final Handler mHandler = new Handler();
	private final Runnable mTimerTask = new Runnable() {
		@Override
		public void run() {
			Calendar now = Calendar.getInstance();
			mTextView.setText(String.format("%02d:%02d:%02d",
					now.get(Calendar.HOUR),
					now.get(Calendar.MINUTE),
					now.get(Calendar.SECOND)
					));
			mHandler.postDelayed(mTimerTask, 1000);
		}
	};
	
    @Override    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	mTextView = new TextView(this);
    	setContentView(mTextView);
    }
    
    
    @Override
    public void onResume() {
    	super.onResume();
    	mHandler.post(mTimerTask);
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	mHandler.removeCallbacks(mTimerTask);
    }
}
