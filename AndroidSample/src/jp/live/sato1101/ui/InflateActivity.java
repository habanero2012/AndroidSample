/*
 * layoutをinflateする
 * ボタンのクリックに反応する
 */

package jp.live.sato1101.ui;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

public class InflateActivity extends Activity {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //レイアウトファイルを展開する
        LinearLayout layout =
        		(LinearLayout) getLayoutInflater().inflate(R.layout.ui_inflate, null);
        
        //新しいボタンを追加
        Button reset = new Button(this);
        reset.setText("Reset Form");
        layout.addView(reset, new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        //ボタンにリスナーを追加
        Button saveBtn = (Button) layout.findViewById(R.id.save);
        saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(InflateActivity.this, "save button clicked!", Toast.LENGTH_SHORT).show();
			}
		});
        
        // TextViewもクリックに反応できる
        TextView textView = new TextView(this);
        textView.setClickable(true);        // TextViewはsetClickableしなくてもOkだけどSampleなので
        textView.setText("クリックに反応するテキスト");
        textView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(InflateActivity.this, "clickableなテキスト", Toast.LENGTH_SHORT).show();
			}
		});
        layout.addView(textView, new LinearLayout.LayoutParams(
        		LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        
        setContentView(layout);
    }
}
