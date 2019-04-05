package com.oliver.vmovier.core.bootloader;

import android.support.annotation.NonNull;
import android.util.ArrayMap;

public class BootLoaderManager {

    private static final class Holder {
        private static final BootLoaderManager INSTANCE = new BootLoaderManager();
    }

    private ArrayMap<Integer, BootLoader> mBootLoaders;

    private BootLoaderManager() {
        mBootLoaders = new ArrayMap<>(16);
    }

    public static BootLoaderManager getInstance() {
        return Holder.INSTANCE;
    }

    public void register(int type, @NonNull BootLoader bootLoader) {
        mBootLoaders.put(type, bootLoader);
    }

    public void init(int type) {
        BootLoader bootLoader = mBootLoaders.get(type);
        if (null != bootLoader) {
            bootLoader.init();
        }
    }
}
