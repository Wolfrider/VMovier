package com.oliver.vmovier.core.Info;

import android.content.Context;
import android.support.annotation.NonNull;

public class InfoBus {

    private static Context mContext;
    private static AppInfo mAppInfo;
    private static DeviceInfo mDeviceInfo;
    private static NetworkInfo mNetworkInfo;

    public static void init(@NonNull Context context) {
        mContext = context;
        mAppInfo = new AppInfo(context);
        mDeviceInfo = new DeviceInfo(context);
        mNetworkInfo = new NetworkInfo(context);
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static AppInfo getAppInfo() {
        return mAppInfo;
    }

    public static DeviceInfo getDeviceInfo() {
        return mDeviceInfo;
    }

    public static NetworkInfo getNetworkInfo() {
        return mNetworkInfo;
    }

    public static String getUserAgent() {
        StringBuilder sb = new StringBuilder();
        sb.append("VmovierApp 5.7.1 / ")
            .append(mDeviceInfo.getSystemNameAndVersion()).append(" / ")
            .append(mNetworkInfo.isWifi() ? "WIFI" : "4G").append(" / ")
            .append(mDeviceInfo.getScreenWidth()).append("*")
            .append(mDeviceInfo.getScreenHeight()).append(" / ")
            .append("3.0");
        return sb.toString();
    }
}
