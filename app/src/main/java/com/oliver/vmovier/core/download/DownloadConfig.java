package com.oliver.vmovier.core.download;

import android.support.annotation.IntRange;

public class DownloadConfig {

    private int mMaxThreadSize;

    private String mCacheDirPath;

    private DownloadConfig(int size, String dirPath) {
        mMaxThreadSize = size;
        mCacheDirPath = dirPath;
    }

    public int getMaxThreadSize() {
        return mMaxThreadSize;
    }

    public String getCacheDirPath() {
        return mCacheDirPath;
    }

    public static class Builder {

        private int mMaxThreadSize = 2;

        private String mCacheDirPath;

        public Builder setMaxThreadSize(@IntRange(from = 1, to = 10) int size) {
            mMaxThreadSize = size;
            return this;
        }

        public Builder setCacheDirPath(String dirPath) {
            mCacheDirPath = dirPath;
            return this;
        }

        public DownloadConfig build() {
            return new DownloadConfig(mMaxThreadSize, mCacheDirPath);
        }
    }

}
