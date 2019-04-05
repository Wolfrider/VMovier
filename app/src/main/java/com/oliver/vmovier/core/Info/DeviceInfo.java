package com.oliver.vmovier.core.Info;

import java.util.Locale;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.oliver.vmovier.core.utils.Logger;

public class DeviceInfo {

    private static final String TAG = "DeviceInfo";

    private Context mContext;
    private int mWidth;
    private int mHeight;
    private int mDpi;
    private float mDensity;
    private String mDeviceId;


    public DeviceInfo(@NonNull Context context) {
        mContext = context;
    }

    public int getScreenWidth() {
        ensureDisplay();
        return mWidth;
    }

    public int getScreenHeight() {
        ensureDisplay();
        return mHeight;
    }

    public String getSystemNameAndVersion() {
        return "Android " + getSystemVersion();
    }

    public String getSystemVersion() {
        return VERSION.RELEASE;
    }

    public int getSystemVersionCode() {
        return VERSION.SDK_INT;
    }

    public String getDeviceId() {
        if (TextUtils.isEmpty(mDeviceId)) {
            TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
            mDeviceId = telephonyManager.getDeviceId();
        }
        return mDeviceId;
    }

    private void ensureDisplay() {
        if (mWidth <= 0 || mHeight <= 0) {
            WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);
            mWidth = displayMetrics.widthPixels;
            mHeight = displayMetrics.heightPixels;
            mDpi = displayMetrics.densityDpi;
            mDensity = displayMetrics.density;
            Logger.d(TAG, String.format(Locale.US, "device screen, width = %d, height = %d, dpi = %d, density = %f", mWidth, mHeight, mDpi, mDensity));
        }
    }

}
