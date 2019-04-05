package com.oliver.vmovier.core.download;

import android.support.annotation.NonNull;
import com.oliver.vmovier.core.function.ActionDelegate;
import com.oliver.vmovier.core.function.ActionDelegate1;

public class DownloadSubject {

    private DownloadTask mTask;
    private ActionDelegate mOnComplete;
    private ActionDelegate1<Integer> mOnProgress;
    private boolean mHasDisposed;

    DownloadSubject(@NonNull DownloadTask task) {
        mTask = task;
    }

    public DownloadTask getTask() {
        return mTask;
    }

    public void subscribe(@NonNull ActionDelegate onComplete) {
        ensureNotDisposed();
        mOnComplete = onComplete;
    }

    public void subscribe(@NonNull ActionDelegate1<Integer> onProgress, @NonNull ActionDelegate onComplete) {
        ensureNotDisposed();
        mOnProgress = onProgress;
        mOnComplete = onComplete;
    }

    public void notifyOnProgress(int progress) {
        if (null != mOnProgress) {
            mOnProgress.exec(progress);
        }
    }

    public void notifyOnComplete() {
        if (null != mOnComplete) {
            mOnComplete.exec();
        }
    }

    public void dispose() {
        mHasDisposed = true;
        mOnProgress = null;
        mOnComplete = null;
        mTask = null;
    }

    private void ensureNotDisposed() {
        if (mHasDisposed) {
            throw new IllegalStateException();
        }
    }
}
