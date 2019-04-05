package com.oliver.vmovier.core.download.inner.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;

public class DownloadServiceProxy {

    private static final String SERVICE_ACTION = "com.oliver.VMovier.DownloadService";

    public DownloadServiceProxy() {
    }

    public void init(@NonNull Context context) {
        Intent intent = new Intent(SERVICE_ACTION);
        context.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }



}
