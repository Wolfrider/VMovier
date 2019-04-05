package com.oliver.vmovier.core.rxext;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class AutoObserver<T> implements Observer<T> {

    private Disposable mDisposable;
    private Consumer<T> mOnNext;
    private Action mOnComplete;
    private Consumer<Throwable> mOnError;

    public AutoObserver(Consumer<T> onNext) {
        mOnNext = onNext;
    }

    public AutoObserver(Consumer<T> onNext, Action onComplete) {
        mOnNext = onNext;
        mOnComplete = onComplete;
    }

    public AutoObserver(Consumer<T> onNext, Consumer<Throwable> onError, Action onComplete) {
        mOnNext = onNext;
        mOnComplete = onComplete;
        mOnError = onError;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (null != mOnNext) {
            try {
                mOnNext.accept(t);
            } catch (Exception ex) {
                onError(ex);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        if (null != mOnError) {
            try {
                mOnError.accept(e);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (null != mDisposable) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    @Override
    public void onComplete() {
        if (null != mOnComplete) {
            try {
                mOnComplete.run();
            } catch (Exception ex) {
                onError(ex);
            }
        }
        if (null != mDisposable) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
