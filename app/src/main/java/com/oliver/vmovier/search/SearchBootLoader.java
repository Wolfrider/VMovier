package com.oliver.vmovier.search;

import com.oliver.vmovier.core.VMovierApplication;
import com.oliver.vmovier.core.bootloader.BootLoader;
import com.oliver.vmovier.core.guice.ObjectProviders;

public class SearchBootLoader extends BootLoader {

    public SearchBootLoader(VMovierApplication application) {
        super(application);
    }

    @Override
    protected void initImpl() {
        SearchGuiceFactory factory = new SearchGuiceFactory();
        ObjectProviders.register(SearchBO.class, factory);
    }
}
