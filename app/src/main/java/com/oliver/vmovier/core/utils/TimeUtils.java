package com.oliver.vmovier.core.utils;

public class TimeUtils {

    private static final String TAG = "TimeUtils";

    public static long getDiffSeconds(int timeStamp) {
        return System.currentTimeMillis() / 1000 - timeStamp;
    }

    public static long getDiffDay(int timeStamp) {
        return getDiffHour(timeStamp) / 24;
    }

    public static long getDiffHour(int timeStamp) {
        return getDiffSeconds(timeStamp) / 3600;
    }
}
