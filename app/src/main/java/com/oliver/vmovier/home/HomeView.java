package com.oliver.vmovier.home;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.core.Constant.NavType;
import com.oliver.vmovier.core.Constant.SCHEME;
import com.oliver.vmovier.core.utils.Router;
import com.oliver.vmovier.home.daily.DailyCoverView;
import com.oliver.vmovier.home.open.FirstScreenView;
import com.oliver.vmovier.home.open.FirstScreenView.OnFinishListener;

public class HomeView extends BaseView {

    private FirstScreenView mFirstScreenView;
    private DailyCoverView mDailyCoverView;
    private HomeTabView mHomeTabView;

    public HomeView(@NonNull AppCompatActivity activity) {
        super(activity);
        mFirstScreenView = new FirstScreenView(mActivity, new OnFinishListener() {
            @Override
            public void onFinish() {
                mDailyCoverView.onShow();
            }
        });
        mDailyCoverView = new DailyCoverView(mActivity);
        mHomeTabView = new HomeTabView(mActivity);
    }

    @Override
    public void init() {
        mFirstScreenView.init();
        mDailyCoverView.init();
        mHomeTabView.init();
        mActivity.findViewById(R.id.home_search).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new Router.Builder(mActivity, NavType.SCHEME)
                    .setContent(SCHEME.SEARCH)
                    .build().nav();
            }
        });
    }
}
