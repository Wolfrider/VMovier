package com.oliver.vmovier.home;

import com.oliver.vmovier.channel.ChannelBO;
import com.oliver.vmovier.channel.list.ChannelListBO;
import com.oliver.vmovier.core.VMovierApplication;
import com.oliver.vmovier.core.bootloader.BootLoader;
import com.oliver.vmovier.core.db.VMovierDB;
import com.oliver.vmovier.core.guice.ObjectProviders;
import com.oliver.vmovier.detail.video.VideoDetailBO;
import com.oliver.vmovier.discovery.DiscoveryBO;
import com.oliver.vmovier.home.daily.DailyCoverBO;
import com.oliver.vmovier.home.open.FirstScreenBO;

public class HomeBootLoader extends BootLoader {

    public HomeBootLoader(VMovierApplication application) {
        super(application);
    }

    @Override
    public void initImpl() {
        HomeGuiceFactory guiceFactory = new HomeGuiceFactory();
        ObjectProviders.register(FirstScreenBO.class, guiceFactory);
        ObjectProviders.register(DailyCoverBO.class, guiceFactory);
        ObjectProviders.register(ChannelBO.class, guiceFactory);
        ObjectProviders.register(ChannelListBO.class, guiceFactory);
        ObjectProviders.register(DiscoveryBO.class, guiceFactory);
        ObjectProviders.register(VideoDetailBO.class, guiceFactory);
        VMovierDB.init(mApp);
    }
}
