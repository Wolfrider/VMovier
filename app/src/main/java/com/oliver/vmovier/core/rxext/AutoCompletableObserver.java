package com.oliver.vmovier.core.rxext;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class AutoCompletableObserver implements CompletableObserver {

    private Disposable mDisposable;
    private Action mOnComplete;
    private Consumer<Throwable> mOnError;

    public AutoCompletableObserver(Action onComplete, Consumer<Throwable> onError) {
        mOnComplete = onComplete;
        mOnError = onError;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onComplete() {
        try {
            mOnComplete.run();
        } catch (Exception ex) {
            onError(ex);
        } finally {
            dispose();
        }
    }

    @Override
    public void onError(Throwable e) {
        try {
            mOnError.accept(e);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            dispose();
        }
    }

    private void dispose() {
        if (null != mDisposable) {
            mDisposable.dispose();
        }
        mDisposable = null;
    }
}
