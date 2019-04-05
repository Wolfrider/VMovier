package com.oliver.vmovier.splash;

import com.oliver.vmovier.core.VMovierApplication;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.bootloader.BootLoader;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.local.LocalRepository;

public class SplashBootLoader extends BootLoader {

    public SplashBootLoader(VMovierApplication application) {
        super(application);
    }

    @Override
    public void initImpl() {
        SplashGuiceFactory guiceFactory = new SplashGuiceFactory();
        ObjectProviders.register(ApiGateway.class, guiceFactory);
        ObjectProviders.register(LocalRepository.class, guiceFactory);
    }
}
