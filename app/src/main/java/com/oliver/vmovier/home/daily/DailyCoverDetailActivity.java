package com.oliver.vmovier.home.daily;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant.IntentAction;

public class DailyCoverDetailActivity extends BaseActivity<DailyCoverDetailView> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_cover_detail);
        Intent intent = getIntent();
        DailyCoverVO dailyCoverVO = intent.getParcelableExtra(IntentAction.PARAM_CONTENT);
        mView = new DailyCoverDetailView(this, dailyCoverVO);
        mView.init();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mView.release();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mView.release();
        }
        return super.onKeyDown(keyCode, event);
    }
}
