/*
 * Notificationを実行する
 */

package jp.live.sato1101.system;

import android.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class NotificationActivity extends Activity implements OnClickListener{

	private static final int NOTE_ID = 100;
	
	private final Handler mHander = new Handler();
	private final Runnable mTask = new Runnable() {
		@Override
		public void run() {
			NotificationManager manager =
					(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
			Intent lanchIntent = new Intent(getApplicationContext(),
					NotificationActivity.class);
			PendingIntent contentIntent = 
					PendingIntent.getActivity(getApplicationContext(), 0, lanchIntent, 0);
			
			// Notificationに表示されるアイコンとステータスバーに表示される文字を設定
			Notification note = new Notification(R.drawable.ic_secure, "Something Happened!",
					System.currentTimeMillis());
			// Notificationのタイトルと本文、クリックされたときに実行されるIntentを設定
			note.setLatestEventInfo(getApplicationContext(), "We're Finished!", 
					"Click Here!", contentIntent);
			
			// Notification通知時の端末の動作を設定
			note.defaults |= Notification.DEFAULT_SOUND;   // 音を鳴らす
			note.defaults |= Notification.DEFAULT_LIGHTS;  // 光らせる
			note.defaults |= Notification.DEFAULT_VIBRATE; // 振動させる（VIBRATEのパーミッションが必要）
			
			note.flags |= Notification.FLAG_AUTO_CANCEL;   // ユーザーが選択したら通知を自動的に消去する
			note.flags |= Notification.FLAG_INSISTENT;     // ユーザーが応答するまで通知音を繰り返す
			manager.notify(NOTE_ID, note);
			
		}
	};
	
    @Override    
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	LinearLayout layout = new LinearLayout(this);
    	setContentView(layout);
    	
    	Button button = new Button(this);
    	button.setText("Post New Notification");
    	button.setOnClickListener(this);
    	layout.addView(button, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }
	
	@Override
	public void onClick(View v) {
		// クリックから１０秒後に実行する
		mHander.postDelayed(mTask, 10000);
		Toast.makeText(this, "Notification will post in 10 seconds", Toast.LENGTH_SHORT).show();
	}

}
