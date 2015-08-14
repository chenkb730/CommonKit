package org.eric.commonkit.task;

/**
 * Created by kunbin.chen on 2015/8/11.
 */
public interface ITaskManager {
    
    void addTask(CLWorkTask task);

    void removeTask(String taskId, boolean cancelIfRunning);

    void removeAllTask(boolean cancelIfRunning);

    int getTaskCount(String taskId);
}
