package jp.live.sato1101.system;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SystemSampleActivity extends ListActivity {
	String[] tests = {"NotificationActivity"};
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, tests));
    }
    
    protected void onListItemClick(ListView list, View view, int position, long id) {
    	super.onListItemClick(list, view, position, id);
    	String testName = tests[position];
    	try {
    			Class<?> clazz = Class.forName("jp.live.sato1101.system." + testName);
    			Intent intent = new Intent(this, clazz);
    			startActivity(intent);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }

}
