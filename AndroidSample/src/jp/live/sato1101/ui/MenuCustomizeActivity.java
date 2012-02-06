/*
 * メニューをlayout XMLでカスタマイズする
 * 
 * onKeyUpメソッドでメニューボタンを押したのを捕捉して、
 * AlertDialogをextendsしたクラスをshow()する。
 * 
 */
package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MenuCustomizeActivity extends Activity {

	private MenuDialog mMenuDialog;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("メニューをカスタマイズ");
        setContentView(textView);
	}
	
	private class MenuDialog extends AlertDialog implements android.view.View.OnClickListener {

		protected MenuDialog(Context context) {
			super(context);
			setTitle("Menu");
			View menu = getLayoutInflater().inflate(R.layout.ui_custommenu, null);
			setView(menu);
			
			ImageButton btn = (ImageButton) menu.findViewById(R.id.imageButton1);
			btn.setOnClickListener(this);
			btn = (ImageButton) menu.findViewById(R.id.imageButton2);
			btn.setOnClickListener(this);
			btn = (ImageButton) menu.findViewById(R.id.imageButton3);
			btn.setOnClickListener(this);
			btn = (ImageButton) menu.findViewById(R.id.imageButton4);
			btn.setOnClickListener(this);
		}
		
		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			if(keyCode == KeyEvent.KEYCODE_MENU) {
				dismiss();
				return true;
			}
			return super.onKeyUp(keyCode, event);
		}

		@Override
		public void onClick(View arg0) {
			Toast.makeText(getApplicationContext(),
					"menu button clicked", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU) {
			if(mMenuDialog == null) {
				mMenuDialog = new MenuDialog(this); 
			}
			mMenuDialog.show();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}
}
