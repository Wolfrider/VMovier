package com.oliver.vmovier.core.rxext;

import java.util.Arrays;
import java.util.LinkedList;

import android.support.annotation.NonNull;
import io.reactivex.disposables.Disposable;

public class RxRes {

    private LinkedList<Disposable> mRes;

    public RxRes() {
        mRes = new LinkedList<>();
    }

    public RxRes(@NonNull Disposable... disposables) {
        this();
        mRes.addAll(Arrays.asList(disposables));
    }

    public void append(@NonNull Disposable... disposables) {
        mRes.addAll(Arrays.asList(disposables));
    }

    public void clear() {
        for (Disposable disposable: mRes) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }
        mRes.clear();
    }
}
