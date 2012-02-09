/**
 * Preferenceを使うサンプル
 */
package jp.live.sato1101.datastore;

import jp.live.sato1101.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PreferenceSampleActivity extends Activity{

	private TextView mNameText;
	private TextView mColorText;
	private Button mSettingsBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datastore_preference_sample_activity);
		
		mNameText = (TextView) findViewById(R.id.datastore_preference_sample_activity_name_value);
		mColorText = (TextView) findViewById(R.id.datastore_preference_sample_activity_color_value);
		mSettingsBtn = (Button) findViewById(R.id.datastore_preference_sample_activity_btn);
		mSettingsBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(PreferenceSampleActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
		// プリファレンスのデフォルト設定をロードする
		// 第３引数のreadAgainをfaluseにすれば、デフォルト値が繰り返しロードされない
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		// 現在のプリファレンス設定にアクセスする
		SharedPreferences settings = 
				PreferenceManager.getDefaultSharedPreferences(this);
		mNameText.setText(settings.getString("name_pref", ""));
		mColorText.setText(settings.getString("color_pref", ""));
	}
}
