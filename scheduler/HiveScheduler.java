package com.android.launcher3.scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.launcher3.Constants;


/**
 * Created by satish on 25/11/15.
 */
 comment for fun
public class HiveScheduler {
    private static final String TAG = "hiveLog satish";

    private long SECOND = 1000L;
    private long MINUTE = 60 * SECOND;
    private long HOUR = 60 * MINUTE;
    private long DAY = 24 * HOUR;
    private long WEEK = 7 * DAY;
    private long FORTNIGHT = 2 * WEEK;
    private long MONTH = 30 * WEEK;

    Context mContext;
    ComponentName mServiceComponent;
    JobScheduler jobScheduler = null;
    public HiveScheduler(Context mContext) {
        this.mContext = mContext;
        mServiceComponent = new ComponentName(mContext, SchedulerJobService.class);
        jobScheduler = (JobScheduler) mContext.getSystemService(Context.JOB_SCHEDULER_SERVICE);

    }

    /***
     * Make an entry into the TaskScheduler
     * @param hiveTaskObj
     * @return true if the operation is successfull
     */
    public void scheduletask(HiveTask hiveTaskObj){

        Log.i(TAG, "Scheduletask saty" + hiveTaskObj.toString());

        Long schedulerDelta = null;
        switch (hiveTaskObj.get_taskId()){
            case Constants.HOURLY:
                schedulerDelta = HOUR;
                Toast.makeText(mContext, "HOURLY Scheduling started.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "SchedulerTiming():HOURLY " );

                break;
            case Constants.DAILY:
                schedulerDelta = DAY;
                Toast.makeText(mContext, "DAILY Scheduling started.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "SchedulerTiming():DAILY" );

                break;
            case Constants.WEEKLY:
                schedulerDelta = WEEK;
                Toast.makeText(mContext, "WEEKLY Scheduling started.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "SchedulerTiming():WEEKLY");

                break;
            case Constants.FORTNIGHTLY:
                schedulerDelta = SECOND*15;
                Toast.makeText(mContext, "FORTNIGHTLY Scheduling started.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "SchedulerTiming():FORTNIGHTLY" );

                break;
            case Constants.MONTHLY:
                schedulerDelta = SECOND*30;
                Toast.makeText(mContext, "MONTHLY Scheduling started.", Toast.LENGTH_SHORT).show();
                Log.i(TAG, "SchedulerTiming():MONTHLY" );

                break;
            case Constants.GAMING_MODE_AUTO_OFF:
                schedulerDelta = hiveTaskObj.getSchedulerTiming();
                break;
        }

        JobInfo.Builder builder = new JobInfo.Builder(hiveTaskObj.get_taskId(),mServiceComponent);
        builder.setPeriodic(schedulerDelta);
        builder.setPersisted(true);
        builder.setRequiresDeviceIdle(true);

        jobScheduler.schedule(builder.build());

    }

    public void unscheduletask(int taskId){
        Toast.makeText(mContext, "Scheduler of type" + taskId+ "stopped", Toast.LENGTH_SHORT).show();
        jobScheduler.cancel(taskId);
    }

    public void unscheduleAlltask(){
        Toast.makeText(mContext, "All Schedulers are stopped", Toast.LENGTH_SHORT).show();
        jobScheduler.cancelAll();
    }

}
