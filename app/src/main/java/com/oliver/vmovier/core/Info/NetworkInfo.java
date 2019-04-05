package com.oliver.vmovier.core.Info;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

public class NetworkInfo {

    private Context mContext;
    private boolean mHasRead;
    private boolean mIsConnected;
    private boolean mIsWifi;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), ConnectivityManager.CONNECTIVITY_ACTION)) {
                readNetwork();
            }
        }
    };

    public NetworkInfo(@NonNull Context context) {
        mContext = context;
        mContext.registerReceiver(mBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public boolean isConnected() {
        ensureReadNetwork();
        return mIsConnected;
    }

    public boolean isWifi() {
        ensureReadNetwork();
        return mIsWifi;
    }

    private void ensureReadNetwork() {
        if (!mHasRead) {
            readNetwork();
        }
        mHasRead = true;
    }

    private void readNetwork() {
        ConnectivityManager connectivityManager = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (null == networkInfo) {
            mIsConnected = false;
            mIsWifi = false;
        } else {
            mIsConnected = networkInfo.isConnected();
            mIsWifi = (networkInfo.getType() == ConnectivityManager.TYPE_WIFI);
        }
    }

}
