package com.oliver.vmovier.detail.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.base.NoneView;
import com.oliver.vmovier.core.Constant.IntentAction;

public class WebActivity extends BaseActivity<NoneView> {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_activity);
        String url = getIntent().getDataString();
        mWebView = new WebView(this);
        mWebView.init(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.onKeyBackDown()) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
