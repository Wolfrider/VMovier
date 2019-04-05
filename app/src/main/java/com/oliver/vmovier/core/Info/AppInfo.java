package com.oliver.vmovier.core.Info;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;

public class AppInfo {

    private Context mContext;
    private PackageInfo mPackageInfo;

    public AppInfo(@NonNull Context context) {
        mContext = context;
    }

    public boolean isDebug() {
        ApplicationInfo applicationInfo = mContext.getApplicationInfo();
        return (applicationInfo.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    public String getVersionName() {
        ensurePackageInfo();
        return mPackageInfo.versionName;
    }

    public long getVersionCode() {
        ensurePackageInfo();
        return mPackageInfo.versionCode;
    }

    public String getPackageName() {
        ensurePackageInfo();
        return mPackageInfo.packageName;
    }

    private void ensurePackageInfo() {
        if (null == mPackageInfo) {
            try {
                mPackageInfo = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
            } catch (NameNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }


}
