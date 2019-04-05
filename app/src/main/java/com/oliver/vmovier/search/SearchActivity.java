package com.oliver.vmovier.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity;
import com.oliver.vmovier.core.Constant.BootLoaderType;
import com.oliver.vmovier.core.bootloader.BootLoaderManager;

public class SearchActivity extends BaseActivity<SearchView> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        BootLoaderManager.getInstance().init(BootLoaderType.SEARCH);
        mView = new SearchView(this);
        mView.init();
    }
}
