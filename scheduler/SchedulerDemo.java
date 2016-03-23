package com.android.launcher3.scheduler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.android.launcher3.Constants;
import com.android.launcher3.R;

public class SchedulerDemo extends Activity {
    HiveTask dummyTask = null;
    HiveScheduler scheduler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler_demo);

        scheduler = new HiveScheduler(SchedulerDemo.this);
    }

    public void startHourly(View view){
        dummyTask = new HiveTask();
        dummyTask.set_taskId(Constants.HOURLY);
        dummyTask.setSchedulerTiming(Constants.HOURLY);
        dummyTask.setUserStatus(Constants.AWAKE);
        dummyTask.setModuleName(Constants.CACHE);
        dummyTask.setTaskName("CLEAN_CACHE1");
        dummyTask.setRepetitions(2);

        scheduler.scheduletask(dummyTask);

    }

    public void startDaily(View view){
        dummyTask = new HiveTask();
        dummyTask.set_taskId(Constants.DAILY);
        dummyTask.setSchedulerTiming(Constants.DAILY);
        dummyTask.setUserStatus(Constants.AWAKE);
        dummyTask.setModuleName(Constants.CACHE);
        dummyTask.setTaskName("CLEAN_CACHE1");
        dummyTask.setRepetitions(2);

        scheduler.scheduletask(dummyTask);
    }

    public void startWeekly(View view){
        dummyTask = new HiveTask();
        dummyTask.set_taskId(Constants.WEEKLY);
        dummyTask.setSchedulerTiming(Constants.WEEKLY);
        dummyTask.setUserStatus(Constants.AWAKE);
        dummyTask.setModuleName(Constants.CACHE);
        dummyTask.setTaskName("CLEAN_CACHE1");
        dummyTask.setRepetitions(2);

        scheduler.scheduletask(dummyTask);
    }

    public void startFortnightly(View view){
        dummyTask = new HiveTask();
        dummyTask.set_taskId(Constants.FORTNIGHTLY);
        dummyTask.setSchedulerTiming(Constants.FORTNIGHTLY);
        dummyTask.setUserStatus(Constants.AWAKE);
        dummyTask.setModuleName(Constants.CACHE);
        dummyTask.setTaskName("CLEAN_CACHE1");
        dummyTask.setRepetitions(2);

        scheduler.scheduletask(dummyTask);
    }

    public void startMonthly(View view){
        dummyTask = new HiveTask();
        dummyTask.set_taskId(Constants.MONTHLY);
        dummyTask.setSchedulerTiming(Constants.MONTHLY);
        dummyTask.setUserStatus(Constants.AWAKE);
        dummyTask.setModuleName(Constants.CACHE);
        dummyTask.setTaskName("CLEAN_CACHE1");
        dummyTask.setRepetitions(2);

        scheduler.scheduletask(dummyTask);
    }

    public void stopHourly(View view){
        scheduler.unscheduletask(Constants.HOURLY);
    }

    public void stopDaily(View view){
        scheduler.unscheduletask(Constants.DAILY);
    }

    public void stopWeekly(View view){
        scheduler.unscheduletask(Constants.WEEKLY);
    }

    public void stopFortnightly(View view){
        scheduler.unscheduletask(Constants.FORTNIGHTLY);
    }

    public void stopMonthly(View view){
        scheduler.unscheduletask(Constants.MONTHLY);
    }

    public void stopAll(View view){
    scheduler.unscheduleAlltask();
    }
}
