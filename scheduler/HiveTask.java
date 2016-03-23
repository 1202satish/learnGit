package com.android.launcher3.scheduler;

/**
 * Created by satish on 25/11/15.
 */
public class HiveTask {
    private int _taskId;
    private long schedulerTiming;
    private int userStatus;
    private int moduleName;
    private String taskName;
    private int repetitions;

    public int get_taskId() {
        return _taskId;
    }

    public void set_taskId(int _taskId) {
        this._taskId = _taskId;
    }

    public long getSchedulerTiming() {
        return schedulerTiming;
    }

    public void setSchedulerTiming(long schedulerTiming) {
        this.schedulerTiming = schedulerTiming;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getModuleName() {
        return moduleName;
    }

    public void setModuleName(int moduleName) {
        this.moduleName = moduleName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
