/*
 * 画面の向きをロックする
 */

package jp.live.sato1101.ui;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class LockScreenActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ToggleButton toggle = new ToggleButton(this);
        setContentView(toggle);
        toggle.setTextOn("LOCKED");
        toggle.setTextOff("Lock");
        
        // 画面の向きをデバイスに任せる設定になっているかどうか調べる
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
        	toggle.setChecked(true);
        } else {
        	toggle.setChecked(false);
        }
        
        toggle.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				int current = getResources().getConfiguration().orientation;
				if(isChecked) {
					switch(current) {
					case Configuration.ORIENTATION_LANDSCAPE:
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
						break;
					case Configuration.ORIENTATION_PORTRAIT:
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
						break;
					default:
						setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
					}
				} else {
					// 画面の回転をデバイスに任せる
					setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
				}
			}
		});
	}
}
