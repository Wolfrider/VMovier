package com.oliver.vmovier.home;

import java.util.ArrayList;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.channel.ChannelFragment;
import com.oliver.vmovier.discovery.DiscoveryFragment;
import com.oliver.vmovier.user.UserFragment;

public class HomeTabView extends BaseView {

    private static final String TAG = "HomeTabView";

    private TabLayout mTabHeader;
    private ViewPager mViewPager;
    private ArrayList<ViewModel> mContent;

    private static class ViewModel {

        private String mTitle;

        private Fragment mFragment;

        ViewModel(String title, Fragment fragment) {
            mTitle = title;
            mFragment = fragment;
        }
    }

    public HomeTabView(@NonNull AppCompatActivity activity) {
        super(activity);
        mContent = new ArrayList<>();
    }

    @Override
    public void init() {
        setupData();
        setupView();
    }

    private void setupData() {
        mContent.add(new ViewModel(mActivity.getResources().getString(R.string.discovery),
            DiscoveryFragment.newInstance()));
        mContent.add(new ViewModel(mActivity.getResources().getString(R.string.channel),
            ChannelFragment.newInstance()));
        mContent.add(new ViewModel(mActivity.getResources().getString(R.string.me),
            UserFragment.newInstance()));
    }

    private void setupView() {
        mTabHeader = mActivity.findViewById(R.id.home_header_tab_indicator);
        mViewPager = mActivity.findViewById(R.id.home_view_pager);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(mActivity.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mContent.get(i).mFragment;
            }

            @Override
            public int getCount() {
                return mContent.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mContent.get(position).mTitle;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                super.destroyItem(container, position, object);
            }
        });
        mViewPager.setCurrentItem(0);
        mTabHeader.setupWithViewPager(mViewPager);
    }
}
