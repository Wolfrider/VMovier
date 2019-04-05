package com.oliver.vmovier.core;

import android.app.Application;
import com.oliver.vmovier.core.Constant.BootLoaderType;
import com.oliver.vmovier.core.Info.InfoBus;
import com.oliver.vmovier.core.bootloader.BootLoaderManager;
import com.oliver.vmovier.core.crash.CrashReport;
import com.oliver.vmovier.core.download.DownloadConfig;
import com.oliver.vmovier.core.download.Downloader;
import com.oliver.vmovier.core.lifecycle.LifecycleMonitor;
import com.oliver.vmovier.core.utils.VFiles;
import com.oliver.vmovier.home.HomeBootLoader;
import com.oliver.vmovier.search.SearchBootLoader;
import com.oliver.vmovier.splash.SplashBootLoader;

public class VMovierApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        CrashReport.init();
        InfoBus.init(this);
        LifecycleMonitor.init(this);
        DownloadConfig config = new DownloadConfig.Builder()
            .setMaxThreadSize(5).setCacheDirPath(VFiles.getAppCacheDir(this)).build();
        Downloader.getInstance().init(this, config);

        BootLoaderManager.getInstance().register(BootLoaderType.SPLASH, new SplashBootLoader(this));
        BootLoaderManager.getInstance().register(BootLoaderType.HOME, new HomeBootLoader(this));
        BootLoaderManager.getInstance().register(BootLoaderType.SEARCH, new SearchBootLoader(this));
    }
}
