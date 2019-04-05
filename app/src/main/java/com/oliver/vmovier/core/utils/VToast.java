package com.oliver.vmovier.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.Toast;

public final class VToast {

    private Builder mBuilder;

    private VToast(Builder builder) {
        mBuilder = builder;
    }

    public void show() {
        Toast.makeText(mBuilder.mContext, mBuilder.mTips,
            mBuilder.mLongDuration ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }

    public static class Builder {

        private Context mContext;
        private String mTips;
        private boolean mLongDuration;

        public Builder(@NonNull Context context) {
            mContext = context;
        }

        public Builder setTips(@NonNull String tips) {
            mTips = tips;
            return this;
        }

        public String getTips() {
            return mTips;
        }

        public Builder setLongDuration(boolean longDuration) {
            mLongDuration = longDuration;
            return this;
        }

        public boolean getLongDuration() {
            return mLongDuration;
        }

        public VToast build() {
            return new VToast(this);
        }

    }
}
