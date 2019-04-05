package com.oliver.vmovier.splash;

import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.guice.NewInstanceFactory;
import com.oliver.vmovier.core.guice.NotFoundTargetClassException;
import com.oliver.vmovier.core.local.LocalRepository;

public class SplashGuiceFactory implements NewInstanceFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> targetClass) {
        if (targetClass.equals(ApiGateway.class)) {
            return (T) new ApiGateway();
        } else if (targetClass.equals(LocalRepository.class)) {
            return (T) new LocalRepository();
        }
        throw new NotFoundTargetClassException(targetClass);
    }
}
