package org.eric.commonkit.kit;

import android.os.SystemClock;

import org.eric.commonkit.kit.common.LogKit;

public class TimeKit {
    public static String TIMESTAMP = "abase_timestamp";

    public static long TIME_HOUR = 60 * 60 * 1000;
    public static long TIME_DAY = TIME_HOUR * 24;

    /**
     * 保存时间戳 为开机到现在的时间 不会产生用户修改的情况
     */
    public static void saveTime() {
        PrefKit.setValue(TIMESTAMP, SystemClock.elapsedRealtime());
    }


    public static void initTimeout() {

        PrefKit.setValue(TIMESTAMP, 0L);

    }

    public static boolean isTimeout(long time) {
        long diff = getDiffTime();

        if (diff < 0) {
            saveTime();
            return true;
        } else if (diff > time) {
            saveTime();
            return true;
        } else return false;

    }

    /**
     * 获得上传 保存的时间和当前的差 如果没有上传保存的时间 返回0
     *
     * @return
     */
    public static long getDiffTime() {
        long start = PrefKit.getLong(TIMESTAMP, 0);
        long end = SystemClock.elapsedRealtime();

        LogKit.i("start time =" + start + " end time =" + end + " diff =" + (end - start));

        return end - start;

    }

}
