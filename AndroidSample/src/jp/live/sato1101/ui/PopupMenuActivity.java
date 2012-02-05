/*
 * コンテキストメニューを呼び出す
 * オプションメニューを呼び出す
 */

package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PopupMenuActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Button btn = new Button(this);
		btn.setText("Create Context Menu");
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				openContextMenu(v);    // コンテキストメニューを開く
			}
		});
		registerForContextMenu(btn);    // コンテキストメニューを開くビューはこのメソッドを呼ぶ必要がある
	    
		setContentView(btn);
	}
	
	/*
	 * コンテキストメニューを作成する
	 * (非 Javadoc)
	 * @see android.app.Activity#onCreateContextMenu(android.view.ContextMenu, android.view.View, android.view.ContextMenu.ContextMenuInfo)
	 */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			                        ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		menu.setHeaderTitle("Choose an Option");
	}
	
	/*
	 * コンテキストメニューを選択したらこのメソッドが呼ばれる
	 * (非 Javadoc)
	 * @see android.app.Activity#onContextItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_copy:
			Toast.makeText(this, "ContextItemSelected: menu_copy", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_delete:
			Toast.makeText(this, "ContextItemSelected: menu_delete", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_edit:
			Toast.makeText(this, "ContextItemSelected: menu_edit", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
	
	/*
	 * オプションメニューを作成する
	 * (非 Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.contextmenu, menu);
		return true;
	}
	

	/*
	 * オプションメニューを選択するとこのメソッドが呼ばれる
	 * (非 Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_copy:
			Toast.makeText(this, "OptionsItemSelected: menu_copy", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_delete:
			Toast.makeText(this, "OptionsItemSelected: menu_delete", Toast.LENGTH_SHORT).show();
			break;
		case R.id.menu_edit:
			Toast.makeText(this, "OptionsItemSelected: menu_edit", Toast.LENGTH_SHORT).show();
			break;
		}
		return true;
	}
}
