package com.android.launcher3.scheduler.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public abstract class AbstractDbAdapter {

	public static final String TABLE_SCHEDULER = "scheduler";

	protected static final String KEY_TASK_ID = "_taskId";
	protected static final String SCHEDULER_TIMING = "schedulerTiming";
	protected static final String USER_STATUS = "userStatus";
	protected static final String MODULE_NAME = "moduleName";
	protected static final String TASK_NAME = "taskName";
	protected static final String REPETITIONS = "repetetions";
	protected static final String DATABASE_TABLE = "scheduler";

	protected static final String DATABASE_NAME = "hiveDB";
	private static final int DATABASE_VERSION = 4;
	private static final String TAG= "hivelog satish";

	protected final Context mCtx;
    protected DatabaseHelper mDbHelper;
    protected static SQLiteDatabase mDb;

    
	// Database creation sql statement
	private static final String SCHEDULER_CREATE = "create table "+" "
			+ TABLE_SCHEDULER + "(" + KEY_TASK_ID + " integer primary key , " + SCHEDULER_TIMING +" text not null , "
			+ USER_STATUS + " integer ," + MODULE_NAME + " integer not null ," + TASK_NAME +" text not null ,"
			+ REPETITIONS +" integer not null" + ");";



	static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase database) {
			Log.w(TAG, "Oncreate Method is Called");
			database.execSQL(SCHEDULER_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG,
					"Upgrading database from version " + oldVersion + " to "
							+ newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULER);

			onCreate(db);
		}
		
	/*	public int getLastID(String table) {
		    final String MY_QUERY = "SELECT last_insert_rowid() FROM "+ table;
		    Cursor cur = mDb.rawQuery(MY_QUERY, null);
		    cur.moveToFirst();
		    int ID = cur.getInt(0);
		    cur.close();
		    return ID;
		}*/
	}

	/**
	 * Constructor - takes the context to allow the database to be
	 * opened/created
	 * */
	public AbstractDbAdapter(Context ctx) {
      this.mCtx = ctx;
	} 
      /**
       * Open or create the routes database.
       * 
       * @return this
       * @throws SQLException if the database could be neither opened or created
       */
	public AbstractDbAdapter open() throws SQLException {
		Log.d(TAG ,":="+SCHEDULER_CREATE);

		Log.w(TAG,"Open() Method is Called");
          mDbHelper = new DatabaseHelper(mCtx);
          mDb = mDbHelper.getWritableDatabase();
          return this;
      }
      
      public void close() {
			Log.w(TAG,"close() Method is Called");

          mDbHelper.close();
      }
}