package com.oliver.vmovier.core.utils;

import java.util.Locale;

import android.support.annotation.NonNull;
import android.util.Log;

public final class Logger {

    private static final String TAG = "VMovier";

    public static void v(@NonNull String tag, @NonNull String msg) {
        Log.v(TAG, String.format("[%s] %s", tag, msg));
    }

    public static void d(@NonNull String tag, @NonNull String msg) {
        Log.d(TAG, String.format("[%s] %s", tag, msg));
    }

    public static void i(@NonNull String tag, @NonNull String msg) {
        Log.i(TAG, String.format("[%s] %s", tag, msg));
    }

    public static void w(@NonNull String tag, @NonNull String msg) {
        Log.w(TAG, String.format("[%s] %s", tag, msg));
    }

    public static void e(@NonNull String tag, @NonNull String msg) {
        Log.e(TAG, String.format("[%s] %s", tag, msg));
    }

    public static void trace(@NonNull String tag) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        StackTraceElement traceElement = elements[3];
        v(tag, String.format(Locale.US, "stackTrace, method = %s, class = %s, lines = %d", traceElement.getMethodName(),
            traceElement.getClassName(), traceElement.getLineNumber()));
    }

}
