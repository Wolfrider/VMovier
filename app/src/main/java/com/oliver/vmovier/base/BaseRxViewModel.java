package com.oliver.vmovier.base;

import android.arch.lifecycle.ViewModel;
import com.oliver.vmovier.core.rxext.RxRes;

public abstract class BaseRxViewModel extends ViewModel {

    protected RxRes mRxRes;

    public BaseRxViewModel() {
        mRxRes = new RxRes();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mRxRes.clear();
    }
}
