package com.oliver.vmovier.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.bootloader.BootLoaderManager;
import com.oliver.vmovier.core.permission.RuntimePermission;
import com.oliver.vmovier.core.utils.Logger;

public abstract class BaseActivity<T extends BaseView> extends AppCompatActivity {

    public static abstract class BaseView {

        protected AppCompatActivity mActivity;

        public BaseView(@NonNull AppCompatActivity activity) {
            mActivity = activity;
        }

        public abstract void init();
    }

    protected T mView;
    protected RuntimePermission mPermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onDestroy");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onPostCreate");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onPostResume");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onWindowFocusChanged " + hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onAttachedToWindow");
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onDetachedFromWindow");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onRestoreInstanceState");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Logger.trace(getClass().getSimpleName());
        //Logger.v(getClass().getSimpleName(), "onConfigurationChanged");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (null != mPermission && mPermission.getId() == requestCode) {
            if (permissions.length > 0 && grantResults.length > 0) {
                mPermission.checkPermission(permissions[0], grantResults[0]);
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected void ensurePermission(String[] permissions, Runnable success) {
        mPermission = new RuntimePermission.Builder(this)
            .setPermission(permissions)
            .setSuccessAction(success)
            .setFailedAction(createPermissionFailedRunnable())
            .build();
        mPermission.grantPermission();
    }

    private Runnable createPermissionFailedRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                Logger.d(getClass().getSimpleName(), "permission denied. exit app.");
                Process.killProcess(Process.myPid());
            }
        };
    }

    protected void onInitBootLoader(int type) {
        BootLoaderManager.getInstance().init(type);
    }
}
