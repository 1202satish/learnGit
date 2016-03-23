package com.android.launcher3.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.util.Log;

import com.android.launcher3.Constants;
import com.android.launcher3.LauncherAppState;
import com.android.launcher3.Utilities;
import com.android.launcher3.cleaner.NotificationUtils;
import com.android.launcher3.power.BoostFeature;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by satish on 25/11/15.
 */
public class SchedulerJobService extends JobService {
    private static final String TAG = "hivelog satish";


    @Override
    public boolean onStartJob(JobParameters params) {
        // We don't do any real 'work' in this sample app. All we'll
        // do is track which jobs have landed on our service, and
        // update the UI accordingly.
        String demoLog = Utilities.getStringPreferences(getApplicationContext(), "DEMO_LOG", "No Content yet");
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy'=>'HH:mm:ss", Locale.ENGLISH);
        String timeStamp=dateFormat.format(new Date());
        demoLog = demoLog + "Job Id "+params.getJobId()+ ": Time Stamp "+ timeStamp+"\n";

        SharedPreferences sp = getSharedPreferences(LauncherAppState.getSharedPreferencesKey(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("DEMO_LOG", demoLog);
        editor.commit();
        Log.i(TAG, "on start job: ##" + params.getJobId());
        Log.i(TAG, "Demo Log: ##" + demoLog);

        if(params.getJobId() == Constants.WEEKLY){
            NotificationUtils.getInstance().sendCacheJunkNotification(SchedulerJobService.this);
        }else if(params.getJobId() == Constants.GAMING_MODE_AUTO_OFF) {
            BoostFeature.getInstance(this).setMode(BoostFeature.MODE_NORMAL);
            NotificationUtils.getInstance().setGamingModeTrial(this, false);
            ((JobScheduler) this.getSystemService(Context.JOB_SCHEDULER_SERVICE)).cancel(Constants.GAMING_MODE_AUTO_OFF);
        }
        return false;
    }


    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "on stop job: " + params.getJobId());
        PersistableBundle pBundle = params.getExtras();

        Boolean isRecursive = Boolean.parseBoolean(pBundle.getString("ISRECURSIVE"));
        if(isRecursive) return true;
        return false;

    }

}