package com.oliver.vmovier.base;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import com.oliver.vmovier.core.rxext.AutoCompletableObserver;
import io.reactivex.Completable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RxCompleteLiveData {

    private MutableLiveData<Boolean> mIsSuccess;
    private MutableLiveData<Boolean> mIsError;
    private MediatorLiveData<Boolean> mIsDone;

    public RxCompleteLiveData(Completable completable) {
        mIsSuccess = new MutableLiveData<>();
        mIsError = new MutableLiveData<>();
        mIsDone = new MediatorLiveData<>();
        mIsDone.addSource(mIsSuccess, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mIsDone.setValue(true);
            }
        });
        mIsDone.addSource(mIsError, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                mIsDone.setValue(true);
            }
        });
        completable.subscribe(new AutoCompletableObserver(new Action() {
            @Override
            public void run() throws Exception {
                mIsSuccess.postValue(true);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mIsError.postValue(true);
            }
        }));
    }

    public LiveData<Boolean> isSuccess() {
        return mIsSuccess;
    }

    public LiveData<Boolean> isError() {
        return mIsError;
    }

    public LiveData<Boolean> isDone() {
        return mIsDone;
    }

}
