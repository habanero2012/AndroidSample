package jp.live.sato1101.ui;

import jp.live.sato1101.SampleListActivity;

public class UISampleActivity extends SampleListActivity {
	String[] tests = {
			"InflateActivity",
			"LockScreenActivity",
			"PopupMenuActivity"
			};
	
	@Override
	protected String[] getActivityList() {
		return tests;
	}

}
