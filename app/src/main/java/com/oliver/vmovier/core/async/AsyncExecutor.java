package com.oliver.vmovier.core.async;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.support.annotation.NonNull;

public class AsyncExecutor {

    private static ThreadPoolExecutor sExecutor;

    static {
        sExecutor = new ThreadPoolExecutor(1, 4,
            60, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(),
            new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(null, r, "VMovier_ThreadExector");
                }
            });
        sExecutor.allowCoreThreadTimeOut(true);
    }

    public static void submit(@NonNull Runnable runnable) {
        sExecutor.execute(runnable);
    }

}
