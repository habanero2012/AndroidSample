package jp.live.sato1101.datastore;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	
	private static final String DB_NAME = "myDB";
	private static final int DB_VERSION = 1;
	
	public static final String TABLE_NAME = "people";
	public static final String COL_NAME = "pName";
	public static final String COL_DATE = "pDate";

	private static final String CREATE_SQL  = 
			"CREATE TABLE " + TABLE_NAME +
			" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
			COL_NAME + " TEXT, " +
			COL_DATE + " DATE);";
	
	public MyDBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_SQL);  // テーブルの作成
		
		// テーブルの初期値をinsert
		ContentValues cv = new ContentValues(2);
		cv.put(COL_NAME, "John Doe");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cv.put(COL_DATE, sdf.format(new Date()));
		db.insert(TABLE_NAME, null, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

}
