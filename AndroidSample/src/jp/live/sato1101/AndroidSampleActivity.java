package jp.live.sato1101;

public class AndroidSampleActivity extends SampleListActivity {
	String[] tests = {
			"system.SystemSampleActivity",
			"ui.UISampleActivity",
			"network.NetworkSampleActivity",
			"datastore.DataStoreSampleActivity"
	};

	@Override
	protected String[] getActivityList() {
		return tests;
	}
}