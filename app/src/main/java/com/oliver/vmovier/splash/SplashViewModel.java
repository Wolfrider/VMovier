package com.oliver.vmovier.splash;

import android.arch.lifecycle.LiveData;
import com.oliver.vmovier.base.BaseRxViewModel;
import com.oliver.vmovier.base.RxCompleteLiveData;
import io.reactivex.schedulers.Schedulers;

public class SplashViewModel extends BaseRxViewModel {

    private SplashBO mSplashBO;

    public SplashViewModel() {
        super();
        mSplashBO = new SplashBO();
    }

    public LiveData<Boolean> preLoad() {
        RxCompleteLiveData liveData = new RxCompleteLiveData(mSplashBO.preLoad().subscribeOn(Schedulers.io()));
        return liveData.isDone();
    }

}
