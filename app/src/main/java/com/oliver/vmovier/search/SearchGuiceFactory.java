package com.oliver.vmovier.search;

import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.guice.NewInstanceFactory;
import com.oliver.vmovier.core.guice.NotFoundTargetClassException;
import com.oliver.vmovier.core.guice.ObjectProviders;

public class SearchGuiceFactory implements NewInstanceFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> targetClass) {
        if (targetClass.equals(SearchBO.class)) {
            return (T) new SearchBO(ObjectProviders.getSingleton(ApiGateway.class));
        }
        throw new NotFoundTargetClassException(targetClass);
    }
}
