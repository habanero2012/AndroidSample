package jp.live.sato1101;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public abstract class SampleListActivity extends ListActivity{
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, getActivityList()));
    }
    
    protected abstract String[] getActivityList();
    
    
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
    	super.onListItemClick(list, view, position, id);
    	String[] activityList = getActivityList();
    	String activityName = activityList[position];
    	try {
	        String packageName = getClass().getPackage().getName();
		    Class<?> clazz = Class.forName(packageName + "." + activityName);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    }
}
