package com.oliver.vmovier.splash;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.Constant.IntentAction;
import com.oliver.vmovier.core.utils.Logger;

public class SplashView extends BaseView {

    private static final String TAG = "SplashView";

    private SplashViewModel mViewModel;

    public SplashView(@NonNull AppCompatActivity activity) {
        super(activity);
        mViewModel = ViewModelProviders.of(mActivity).get(SplashViewModel.class);
    }

    @Override
    public void init() {
        mViewModel.preLoad().observe(mActivity, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean value) {
                if (null != value && value) {
                    Logger.d(TAG, "has finished");
                    mActivity.startActivity(new Intent(IntentAction.HOME_ACTION));
                    mActivity.finish();
                }
            }
        });
    }
}
