/**
 * 外部ストレージを使用するサンプル
 * 
 * android.permission.WRITE_EXTERNAL_STORAGE　の許可が必要
 */
package jp.live.sato1101.datastore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import jp.live.sato1101.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UseExternalStorageActivity extends Activity {

	private static final String FILENAME = "data.txt";
	private static final String DIR_NAME = "jp.live.sato1101";

	private TextView mTextView;
	private EditText mEditText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datastore_use_external_storage_activity);
		
		if(!isStorageAvailable()) {
			Toast.makeText(this, "Cannot use storage.", Toast.LENGTH_SHORT).show();
			finish();
			return;
		}

		
		File rootPath = new File(Environment.getExternalStorageDirectory(), DIR_NAME);
		if(!rootPath.exists()) {
			rootPath.mkdirs();
		}
		final File dataFile = new File(rootPath, FILENAME);
		
		mTextView = (TextView) findViewById(R.id.use_external_storege_activity_text_view);
		mEditText = (EditText) findViewById(R.id.use_external_storeage_activity_edit_text);
		Button btn = (Button) findViewById(R.id.use_external_storeage_activity_read_btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FileInputStream in = null;
				try {
					in = new FileInputStream(dataFile);
					byte[] data = new byte[128];
					in.read(data);
					String text = new String(data);
					mTextView.setText(text.trim());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(in != null) {
						try {
							in.close();
						} catch (IOException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}
		});
		btn = (Button) findViewById(R.id.use_external_storage_activity_write_btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(dataFile);
					String data = mEditText.getText().toString();
					out.write(data.getBytes());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(out != null) {
						try {
							out.close();
						} catch (IOException e) {
							// TODO 自動生成された catch ブロック
							e.printStackTrace();
						}
					}
				}
			}
		});
		
		btn = (Button) findViewById(R.id.use_external_storage_activity_delete_btn);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(dataFile.delete()) {
					Toast.makeText(UseExternalStorageActivity.this, "delete file", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(UseExternalStorageActivity.this, "error occurred!", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	
	/**
	 * 外部ストレージがマウントされているか調べる
	 * @return 使用可能な場合、true
	 */
	private boolean isStorageAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
}
