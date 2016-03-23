package com.android.launcher3.scheduler.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

/**
 * Created by satish on 25/11/15.
 */
public class TableScheduler extends AbstractDbAdapter {


    private String TAG = "hivelog satish";


    public TableScheduler(Context ctx) {
        super(ctx);
    }


    /**
     * This method create a entry in the table, it generates the id automatically.
     * @param schedulerTiming
     * @param userStatus
     * @param moduleName
     * @param taskName
     * @return
     */
    public long insert(int schedulerTiming,int userStatus,int moduleName,
                       String taskName, int repetitions) {
        Log.w(TAG, "TableScheduler create Method is Called");
        ContentValues args = new ContentValues();
        args.put(SCHEDULER_TIMING,schedulerTiming);
        args.put(USER_STATUS, userStatus);
        args.put(MODULE_NAME,moduleName);
        args.put(TASK_NAME,taskName);
        args.put(REPETITIONS, repetitions);

        return mDb.insert(DATABASE_TABLE, null,args);
    }


    /**
     * This method create a entry in the table when the ID is provided by the caller
     * @param taskId
     * @param schedulerTiming
     * @param userStatus
     * @param moduleName
     * @param taskName
     * @param repetitions
     * @return
     */
    public long insert(int taskId, int schedulerTiming,String userStatus, String moduleName,
                       int taskName, int repetitions) {
        Log.w(TAG, "TableScheduler create Method is Called");
        ContentValues args = new ContentValues();
        args.put(KEY_TASK_ID, taskId);
        args.put(SCHEDULER_TIMING,schedulerTiming);
        args.put(USER_STATUS,userStatus);
        args.put(MODULE_NAME,moduleName);
        args.put(TASK_NAME,taskName);
        args.put(REPETITIONS, repetitions);

        return mDb.insert(DATABASE_TABLE, null,args);
    }



    public boolean delete(String taskId) {

        Log.w(TAG, "TableScheduler delete() Method is Called");
        return mDb.delete(DATABASE_TABLE, KEY_TASK_ID + "='" + taskId+"'", null) > 0;
    }
    public int deleteAll(){
	Log.w(TAG, "TableScheduler deleteAll() Method is Called");
	return mDb.delete(DATABASE_TABLE, null, null);
   }

    /**
     * Fetch all Tasks of a Module.
     * @param moduleName
     * @return
     * @throws SQLException
     */
    public Cursor fetchByModuleName(String moduleName) throws SQLException {
        Log.w(TAG,"Task fetch() Method is Called");

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] {KEY_TASK_ID,
                                SCHEDULER_TIMING, USER_STATUS, TASK_NAME, REPETITIONS}, MODULE_NAME + "= '" + moduleName+"'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Fetch the record by its TaskId
     * @param taskId
     * @return
     * @throws SQLException
     */
    public Cursor fetchByTaskId(int taskId) throws SQLException {
        Log.w(TAG,"TableScheduler fetch() Method is Called");

        Cursor mCursor =
                mDb.query(true, DATABASE_TABLE, new String[] { SCHEDULER_TIMING,
                                USER_STATUS, MODULE_NAME, TASK_NAME, REPETITIONS}, KEY_TASK_ID + "= '" + taskId+"'", null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }
    /**
     * Return a Cursor over the list of all Tasks in the database
     * @return Cursor over all Tasks
     */
    public Cursor fetchAll() {
        Log.w(TAG, "TableScheduler fetchAll() Method is Called");

        return mDb.query(DATABASE_TABLE, new String[]{KEY_TASK_ID, SCHEDULER_TIMING,
                USER_STATUS, MODULE_NAME, TASK_NAME, REPETITIONS}, null, null, null, null, null);
    }

    /**
     * Update the repetition value of a task
     * @param taskId
     * @param repetitions
     * @return
     */
    public int update(String taskId,int repetitions){

        Log.w(TAG, "update() task(TaskId, repetition) called");
        ContentValues values=new ContentValues();
        values.put(REPETITIONS, repetitions);
        return mDb.update(DATABASE_TABLE, values, KEY_TASK_ID + "='" + taskId + "'", null);
    }

  /*  public int updateRepetition(String taskId){

        Log.w(TAG, "updateRepetition(taskId) task repetitions called");
        ContentValues values=new ContentValues();

        values.put(REPETITIONS ,repetitions);
        return mDb.update(DATABASE_TABLE, values, KEY_TASK_ID + "='" + taskId + "'", null);
    }*/







    public int getLastID() {
        final String MY_QUERY = "SELECT last_insert_rowid() FROM "+ DATABASE_TABLE;
        Cursor cur = mDb.rawQuery(MY_QUERY, null);
        cur.moveToFirst();
        int ID = cur.getInt(0);
        cur.close();
        return ID;
    }
}
