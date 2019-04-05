package com.oliver.vmovier.home;

import com.oliver.vmovier.channel.ChannelBO;
import com.oliver.vmovier.channel.list.ChannelListBO;
import com.oliver.vmovier.core.api.ApiGateway;
import com.oliver.vmovier.core.guice.NewInstanceFactory;
import com.oliver.vmovier.core.guice.NotFoundTargetClassException;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.core.local.LocalRepository;
import com.oliver.vmovier.detail.video.VideoDetailBO;
import com.oliver.vmovier.discovery.DiscoveryBO;
import com.oliver.vmovier.home.daily.DailyCoverBO;
import com.oliver.vmovier.home.open.FirstScreenBO;

public class HomeGuiceFactory implements NewInstanceFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> targetClass) {
        if (targetClass.equals(FirstScreenBO.class)) {
            return (T) new FirstScreenBO(ObjectProviders.getSingleton(ApiGateway.class),
                ObjectProviders.getSingleton(LocalRepository.class));
        } else if (targetClass.equals(DailyCoverBO.class)) {
            return (T) new DailyCoverBO(ObjectProviders.getSingleton(ApiGateway.class));
        } else if (targetClass.equals(ChannelBO.class)) {
            return (T) new ChannelBO(ObjectProviders.getSingleton(ApiGateway.class));
        } else if (targetClass.equals(ChannelListBO.class)) {
            return (T) new ChannelListBO(ObjectProviders.getSingleton(ApiGateway.class));
        } else if (targetClass.equals(DiscoveryBO.class)) {
            return (T) new DiscoveryBO(ObjectProviders.getSingleton(ApiGateway.class));
        } else if (targetClass.equals(VideoDetailBO.class)) {
            return (T) new VideoDetailBO(ObjectProviders.getSingleton(ApiGateway.class));
        }
        throw new NotFoundTargetClassException(targetClass);
    }
}
