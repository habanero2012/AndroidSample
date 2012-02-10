/**
 * SQLiteデータベースを外部ストレージにバックアップする
 */
package jp.live.sato1101.datastore;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;

public class BackupTask extends AsyncTask<Integer, Void, Integer>{

	/**
	 * BackupTaskの処理完了やエラー通知を受け取るリスナーインターフェイス
	 */
	public interface CompletionListener {
		void onBackupComplete();
		void onRestoreComplete();
		void onError(int errorCode);
	}
	
	public static final int BACKUP_SUCCESS = 1;
	public static final int RESTORE_SUCCESS = 2;
	public static final int BACKUP_ERROR = 3;
	public static final int RESTORE_NOFILE_ERROR = 4;
	
	public static final int COMMAND_BACKUP = 5;
	public static final int COMMAND_RESTORE = 6;
	
	private String mDBName;
	private File mDBFile;
	private File mBackupDir;
	private CompletionListener mListener;
	
    /**
     * 
     * @param context
     * @param db_name SQLiteデータベース名
     * @backup_dir バックアップ先の外部ストレージのディレクトリ名
     */
    public BackupTask(Context context, String db_name, String backup_dir) {
    	super();
    	mDBName = db_name;
    	mDBFile = context.getDatabasePath(db_name);
    	mBackupDir = new File(Environment.getExternalStorageDirectory(), backup_dir);
    }
    
    public void setCompletionListener(CompletionListener listener) {
    	mListener = listener;
    }
	
    @Override
	protected Integer doInBackground(Integer... params) {
		if(!mBackupDir.exists()) {
			mBackupDir.mkdirs();
		}
		
		int result_code;
		switch(params[0]) {
		case COMMAND_BACKUP:
			result_code = commandBackup();
			break;
		case COMMAND_RESTORE:
			result_code = commandRestore();
			break;
		default:
			result_code = BACKUP_ERROR;
		}
		return result_code;
	}
	
	private int commandBackup() {
		File backup = new File(mBackupDir, mDBName);
		try {
			backup.createNewFile();
			fileCopy(mDBFile, backup);
			return BACKUP_SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return BACKUP_ERROR;
		}
	}
	
	private int commandRestore() {
		File backup = new File(mBackupDir, mDBName);
		if(!backup.exists()) {
			return RESTORE_NOFILE_ERROR;
		}
		try {
			mDBFile.createNewFile();
			fileCopy(backup, mDBFile);
			return RESTORE_SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return BACKUP_ERROR;
		}
	}
	
	private void fileCopy(File source, File dest) throws IOException {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
		    inChannel = new FileInputStream(source).getChannel();
		    outChannel = new FileInputStream(dest).getChannel();
		    inChannel.transferTo(0, inChannel.size(), outChannel);
		} finally {
			if(inChannel != null) {
				inChannel.close();
			}
			
			if(outChannel != null) {
				outChannel.close();
			}
		}
	}

	@Override
	protected void onPostExecute(Integer result) {
		if(mListener == null) {
			return;
		}
		
		switch(result) {
		case BACKUP_SUCCESS:
			mListener.onBackupComplete();
			break;
		case RESTORE_SUCCESS:
			mListener.onRestoreComplete();
			break;
		case RESTORE_NOFILE_ERROR:
			mListener.onError(RESTORE_NOFILE_ERROR);
		default:
			mListener.onError(BACKUP_ERROR);
		}
	}
}
