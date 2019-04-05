package com.oliver.vmovier.core.permission;

import java.util.concurrent.atomic.AtomicInteger;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class RuntimePermission {

    private static AtomicInteger sAtomicInteger = new AtomicInteger();

    private Builder mBuilder;
    private int mId;
    private int mCurrentIndex = 0;
    private int mGrantPermissionSize = 0;

    private RuntimePermission(Builder builder) {
        mBuilder = builder;
    }

    public int getId() {
        return mId;
    }

    public void grantPermission() {
        while(mCurrentIndex < mBuilder.mPermissions.length) {
            if (hasPermission(mBuilder.mPermissions[mCurrentIndex])) {
                mGrantPermissionSize++;
            } else {
                requestPermission(mBuilder.mPermissions[mCurrentIndex]);
                return;
            }
            ++mCurrentIndex;
        }
        if (mGrantPermissionSize == mBuilder.mPermissions.length) {
            execSucc();
        }
    }

    public void checkPermission(String permission, int grantResult) {
        if (permission.equals(mBuilder.mPermissions[mCurrentIndex]) && grantResult == PackageManager.PERMISSION_GRANTED) {
            ++mCurrentIndex;
            mGrantPermissionSize++;
            grantPermission();
        } else {
            execFail();
        }
    }

    private boolean hasPermission(String permission) {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(mBuilder.mActivity, permission);
    }

    private void requestPermission(String permission) {
        mId = sAtomicInteger.incrementAndGet();
        ActivityCompat.requestPermissions(mBuilder.mActivity, new String[] {permission}, mId);
    }

    private void execSucc() {
        if (null != mBuilder.mSuccess) {
            mBuilder.mSuccess.run();
        }
    }

    private void execFail() {
        if (null != mBuilder.mFailed) {
            mBuilder.mFailed.run();
        }
    }

    public static class Builder {

        private AppCompatActivity mActivity;
        private String[] mPermissions;
        private Runnable mSuccess;
        private Runnable mFailed;

        public Builder(@NonNull AppCompatActivity activity) {
            mActivity = activity;
        }

        public Builder setPermission(@NonNull String[] permissions) {
            mPermissions = permissions;
            return this;
        }

        public Builder setSuccessAction(@NonNull Runnable success) {
            mSuccess = success;
            return this;
        }

        public Builder setFailedAction(@NonNull Runnable failed) {
            mFailed = failed;
            return this;
        }

        public RuntimePermission build() {
            return new RuntimePermission(this);
        }

    }
}
