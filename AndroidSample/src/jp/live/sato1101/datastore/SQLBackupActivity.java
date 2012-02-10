/**
 * SQLiteを使う
 */
package jp.live.sato1101.datastore;

import java.text.SimpleDateFormat;
import java.util.Date;

import jp.live.sato1101.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SQLBackupActivity extends Activity implements View.OnClickListener,
    AdapterView.OnItemClickListener, BackupTask.CompletionListener {
	
	private EditText mEditText;
	private Button mButton;
	private ListView mListView;
	
	private MyDBHelper mDBHelper;
	private SQLiteDatabase mDB;
	private Cursor mCursor;
	private SimpleCursorAdapter mAdapter;
	
	private BackupTask mBackupTask;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.datastore_sqlite_backup_activity);
		
		mEditText = (EditText) findViewById(R.id.datastore_sqlite_sample_edit_text);
		mButton = (Button) findViewById(R.id.datastore_sqlite_sample_btn);
		mButton.setOnClickListener(this);
		mListView = (ListView) findViewById(R.id.datastore_sqlite_sample_list);
		mListView.setOnItemClickListener(this);
		
		mDBHelper = new MyDBHelper(this);
		
		mBackupTask = new BackupTask(this, MyDBHelper.DB_NAME, "jp.live.sato1101");
		mBackupTask.setCompletionListener(this);
		
		Button btn = (Button)findViewById(R.id.datastore_sqlite_backup_activity_backup);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mBackupTask.execute(BackupTask.COMMAND_BACKUP);
			}
		});
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		mDB = mDBHelper.getWritableDatabase();
		String[] columns = new String[] {"_id", MyDBHelper.COL_NAME, MyDBHelper.COL_DATE};
		mCursor = mDB.query(MyDBHelper.TABLE_NAME, columns, null, null, null, null, null);
		
		// リストをリフレッシュする
		String[] headers = new String[] {MyDBHelper.COL_NAME, MyDBHelper.COL_DATE};
		mAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
				mCursor, headers, new int[]{android.R.id.text1, android.R.id.text2});
		mListView.setAdapter(mAdapter);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		mDB.close();
		mCursor.close();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		// データベースから項目を削除する
		mCursor.moveToPosition(position);
		// この行のID値（カラムの０番目）を取得する
		String rowId = mCursor.getString(0);
		mDB.delete(MyDBHelper.TABLE_NAME, "_id = ?", new String[]{rowId});
		// リストをリフレッシュ
		mCursor.requery();
		mAdapter.notifyDataSetChanged();
	}

	@Override
	public void onClick(View v) {
		// データベースに新しい値を追加する
		ContentValues cv = new ContentValues(2);
		cv.put(MyDBHelper.COL_NAME, mEditText.getText().toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cv.put(MyDBHelper.COL_DATE, sdf.format(new Date()));
		mDB.insert(MyDBHelper.TABLE_NAME, null, cv);
		
		// リストをリフレッシュ
		mCursor.requery();
		mAdapter.notifyDataSetChanged();
		mEditText.setText(null);
	}

	@Override
	public void onBackupComplete() {
		Toast.makeText(this, "Backup Complete", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onRestoreComplete() {
		Toast.makeText(this, "Restore Complete", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onError(int errorCode) {
		Toast.makeText(this, "error code:" + errorCode, Toast.LENGTH_SHORT).show();
	}

}
