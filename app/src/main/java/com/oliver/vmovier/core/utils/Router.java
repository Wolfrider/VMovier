package com.oliver.vmovier.core.utils;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map.Entry;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.ArrayMap;
import com.oliver.vmovier.core.Constant.IntentAction;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.Info.InfoBus;

public class Router {

    private static final String TAG = "Router";

    private Builder mBuilder;

    private Router(Builder builder) {
        mBuilder = builder;
    }

    public void nav() {
        Logger.d(TAG, String.format(Locale.US, "nav type = %d, content = %s",
            mBuilder.mType, mBuilder.mContent));
        switch (mBuilder.mType) {
            case NavType.ARTICLE:
                gotoArticle();
                break;
            case NavType.WEBVIEW:
                gotoWebView();
                break;
            case NavType.DAILY_COVER:
                gotoDailyCover();
                break;
            case NavType.CHANNEL_LIST:
                gotoChannelList();
                break;
            case NavType.VIDEO:
                gotoVideo();
                break;
            case NavType.SCHEME:
                gotoScheme();
            default:
                Logger.w(TAG, "unable to nav. type = " + mBuilder.mType);
                break;
        }
    }

    private void gotoArticle() {
        Logger.trace(TAG);
        Intent intent = new Intent(IntentAction.ARTICLE_ACTION);
        intent.putExtra(IntentAction.PARAM_CONTENT, mBuilder.mBundle);
        mBuilder.mContext.startActivity(intent);
    }

    private void gotoWebView() {
        Logger.trace(TAG);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBuilder.mContent));
        intent.setPackage(InfoBus.getAppInfo().getPackageName());
        mBuilder.mContext.startActivity(intent);
    }

    private void gotoDailyCover() {
        Logger.trace(TAG);
        Intent intent = new Intent(IntentAction.DAILY_COVER_ACTION);
        intent.putExtra(IntentAction.PARAM_CONTENT, mBuilder.mParcelableContent);
        mBuilder.mContext.startActivity(intent);
    }

    private void gotoChannelList() {
        Logger.trace(TAG);
        Intent intent = new Intent(IntentAction.CHANNEL_LIST_ACTION);
        intent.putExtra(IntentAction.PARAM_CONTENT, mBuilder.mParcelableContent);
        mBuilder.mContext.startActivity(intent);
    }

    private void gotoVideo() {
        Logger.trace(TAG);
        Intent intent = new Intent();
        if (mBuilder.mContent.startsWith("vmovier")) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(mBuilder.mContent));
        } else {
            intent.setAction(IntentAction.VIDEO_ACTION);
            intent.putExtra(IntentAction.PARAM_CONTENT, mBuilder.mContent);
        }
        intent.setPackage(InfoBus.getAppInfo().getPackageName());
        mBuilder.mContext.startActivity(intent);
    }

    private void gotoScheme() {
        Logger.trace(TAG);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mBuilder.mContent));
        intent.setPackage(InfoBus.getAppInfo().getPackageName());
        mBuilder.mContext.startActivity(intent);
    }

    public static class Builder {

        private Context mContext;

        private int mType;

        private String mContent;

        private Parcelable mParcelableContent;

        private Bundle mBundle;

        public Builder(Context context, int type) {
            mContext = context;
            mType = type;
        }

        public Builder setContent(String content) {
            mContent = content;
            return this;
        }

        public Builder setParcelableContent(Parcelable content) {
            mParcelableContent = content;
            return this;
        }

        public Builder setBundle(Bundle bundle) {
            mBundle = bundle;
            return this;
        }

        public Router build() {
            return new Router(this);
        }
    }

}
