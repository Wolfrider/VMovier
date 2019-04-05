package com.oliver.vmovier.core.bootloader;

import com.oliver.vmovier.core.VMovierApplication;
import com.oliver.vmovier.core.utils.Logger;

public abstract class BootLoader {

    protected boolean mHasInit;
    protected VMovierApplication mApp;

    public BootLoader(VMovierApplication application) {
        mApp = application;
    }

    public void init() {
        if (!mHasInit) {
            Logger.d(getClass().getSimpleName(), "init");
            initImpl();
            mHasInit = true;
        }
    }

    protected abstract void initImpl();

}
