package com.oliver.vmovier.core.download;

import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.oliver.vmovier.core.download.inner.DownloadExecutor;
import com.oliver.vmovier.core.utils.VFiles;

public class Downloader {

    private static class Holder {
        private static final Downloader INSTANCE = new Downloader();
    }

    private Downloader() {
    }

    private DownloadExecutor mExecutor;

    public static Downloader getInstance() {
        return Holder.INSTANCE;
    }

    public void init(@NonNull Application application, @NonNull DownloadConfig config) {
        mExecutor = new DownloadExecutor(config);
    }

    public DownloadSubject submit(@NonNull DownloadTask task) {
        if (isValidTask(task)) {
            DownloadSubject subject = new DownloadSubject(task);
            mExecutor.submit(subject);
            return subject;
        } else {
            return null;
        }
    }

    private boolean isValidTask(@NonNull DownloadTask task) {
        return !TextUtils.isEmpty(task.getUrl())
            && !TextUtils.isEmpty(task.getDirPath())
            && VFiles.exists(task.getDirPath());
    }

}
