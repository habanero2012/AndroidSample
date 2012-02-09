package jp.live.sato1101.datastore;

import jp.live.sato1101.SampleListActivity;

public class DataStoreSampleActivity extends SampleListActivity {
	String[] tests = {
			"PreferenceSampleActivity",
			"UseExternalStorageActivity"
	};
	
	@Override
	protected String[] getActivityList() {
		return tests;
	}

}
