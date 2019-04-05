package com.oliver.vmovier.detail.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import com.oliver.vmovier.R;
import com.oliver.vmovier.core.Info.InfoBus;
import com.oliver.vmovier.core.utils.Logger;

public class WebView {

    private static final String TAG = "WebView";

    private AppCompatActivity mActivity;

    private AppCompatTextView mTitle;
    private android.webkit.WebView mWebView;

    public WebView(@NonNull AppCompatActivity activity) {
        mActivity = activity;
    }

    public void init(final String url) {
        mTitle = mActivity.findViewById(R.id.toolbar_title);
        mActivity.findViewById(R.id.toolbar_back).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
        initWebView();
        Logger.d(TAG, "open webview " + url);
        mWebView.loadUrl(url);
    }

    public boolean onKeyBackDown() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return false;
    }

    private void initWebView() {
        mWebView = mActivity.findViewById(R.id.web_content);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(android.webkit.WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTitle.setText(title);
                Logger.d(TAG, "onReceivedTitle " + title);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Logger.v(TAG, "onPageStarted " + url);
                String js = getNativeJs();
                if (!TextUtils.isEmpty(js)) {
                    Logger.v(TAG, "native js = " + js);
                    mWebView.evaluateJavascript(js, null);
                }
            }

            @Override
            public void onPageFinished(android.webkit.WebView view, String url) {
                super.onPageFinished(view, url);
                Logger.v(TAG, "onPageFinished " + url);
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setAllowFileAccess(true);
        String userAgent = settings.getUserAgentString();
        userAgent += " " + InfoBus.getUserAgent();
        Logger.d(TAG, "user-agent = " + userAgent);
        settings.setUserAgentString(userAgent);
    }

    private String getNativeJs() {
        try {
            try (InputStream inputStream = mActivity.getAssets().open("WebViewJs.js")) {
                byte[] buffer = new byte[1024];
                try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                    do {
                        int read = inputStream.read(buffer);
                        if (read < 0) {
                            break;
                        }
                        outputStream.write(buffer, 0, read);
                    } while(true);
                    return outputStream.toString();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

}
