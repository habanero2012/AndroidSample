package jp.live.sato1101.system;

import jp.live.sato1101.SampleListActivity;

public class SystemSampleActivity extends SampleListActivity {
	String[] tests = {"NotificationActivity", "TimingActivity"};

	@Override
	protected String[] getActivityList() {
		return tests;
	}

}
