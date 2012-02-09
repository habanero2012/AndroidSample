package jp.live.sato1101.network;

import jp.live.sato1101.SampleListActivity;

public class NetworkSampleActivity extends SampleListActivity {
	String[] tests = {
			"SearchActivity",
			"ConnectivityCheckActivity"
	};
	
	@Override
	protected String[] getActivityList() {
		return tests;
	}

}
