package com.oliver.vmovier.splash;

import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.local.LocalRepository;
import io.reactivex.Completable;

public class SplashBO {

    private LocalRepository mRepository;

    public SplashBO() {
        mRepository = ObjectProviders.getSingleton(LocalRepository.class);
    }

    public Completable preLoad() {
        return mRepository.loadLocal();
    }
}
