package com.oliver.vmovier.core.download;

import java.util.concurrent.atomic.AtomicInteger;

import android.support.annotation.IntRange;

public class DownloadTask {

    private static final AtomicInteger ID_SEED = new AtomicInteger(0);

    public static final int PRIORITY_LOW = 0x01;
    public static final int PRIORITY_NORMAL = 0x02;
    public static final int PRIORITY_HIGH = 0x03;

    private int mId = ID_SEED.incrementAndGet();

    private String mUrl;

    private String mDirPath;

    private String mFileName;

    private int mPriority;

    private DownloadTask(String url, String dirPath, String fileName, int priority) {
        mUrl = url;
        mDirPath = dirPath;
        mFileName = fileName;
        mPriority = priority;
    }

    public int getId() {
        return mId;
    }

    public String getUrl() {
        return mUrl;
    }

    public String getDirPath() {
        return mDirPath;
    }

    public String getFileName() {
        return mFileName;
    }

    public int getPriority() {
        return mPriority;
    }

    public static class Builder {
        private String mUrl;

        private String mDirPath;

        private String mFileName;

        private int mPriority = PRIORITY_NORMAL;

        public Builder setUrl(String url) {
            mUrl = url;
            return this;
        }

        public Builder setDirPath(String dirPath) {
            mDirPath = dirPath;
            return this;
        }

        public Builder setFileName(String fileName) {
            mFileName = fileName;
            return this;
        }

        public Builder setPriority(@IntRange(from = PRIORITY_LOW, to = PRIORITY_HIGH) int priority) {
            mPriority = priority;
            return this;
        }

        public DownloadTask build() {
            return new DownloadTask(mUrl, mDirPath, mFileName, mPriority);
        }

    }
}
