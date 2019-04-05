package com.oliver.vmovier.core.crash;

import java.lang.Thread.UncaughtExceptionHandler;

import com.oliver.vmovier.core.utils.Logger;

public class CrashReport {

    private static final String TAG = "CrashReport";

    public static void init() {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                Logger.e(TAG, e.toString());
                e.printStackTrace();
            }
        });
    }
}
