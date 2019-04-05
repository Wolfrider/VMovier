package com.oliver.vmovier.home;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant;
import com.oliver.vmovier.core.Constant.BootLoaderType;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.core.utils.VToast;

public class HomeActivity extends BaseActivity<HomeView> {

    private static final String TAG = "HomeActivity";

    private long mPrevPressBackTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        onInitBootLoader(BootLoaderType.HOME);
        mView = new HomeView(this);
        mView.init();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!doubleClickToExit()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean doubleClickToExit() {
        long current = SystemClock.elapsedRealtime();
        if (current - mPrevPressBackTime > Constant.EXIT_INTERVAL_TIME) {
            mPrevPressBackTime = current;
            new VToast.Builder(this).setTips(getResources().getString(R.string.exit_tips))
                .build().show();
            Logger.d(TAG, "show exit toast");
            return false;
        }
        return true;
    }


}
