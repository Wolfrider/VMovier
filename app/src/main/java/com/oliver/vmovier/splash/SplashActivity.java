package com.oliver.vmovier.splash;

import android.Manifest;
import android.Manifest.permission;
import android.os.Bundle;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant.BootLoaderType;

public class SplashActivity extends BaseActivity<SplashView> {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        onInitBootLoader(BootLoaderType.SPLASH);
        ensurePermission(new String[] {permission.READ_PHONE_STATE}, new Runnable() {
            @Override
            public void run() {
                mView = new SplashView(SplashActivity.this);
                mView.init();
            }
        });
    }

}
