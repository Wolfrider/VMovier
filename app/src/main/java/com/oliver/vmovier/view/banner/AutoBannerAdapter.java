package com.oliver.vmovier.view.banner;

import java.util.LinkedList;
import java.util.Queue;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.oliver.vmovier.core.utils.Logger;

public abstract class AutoBannerAdapter extends PagerAdapter {

    private static final String TAG = "AutoBannerAdapter";

    private Queue<AutoBannerViewHolder> mRecyclePool;

    public AutoBannerAdapter() {
        super();
        mRecyclePool = new LinkedList<>();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Logger.d(TAG, "instantiateItem " + position);
        AutoBannerViewHolder viewHolder;
        if (mRecyclePool.isEmpty()) {
            viewHolder = onCreateViewHolder();
        } else {
            viewHolder = mRecyclePool.poll();
        }
        onBindViewHolder(viewHolder, getRealPosition(position));
        container.addView(viewHolder.getView(), LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        viewHolder.getView().setTag(viewHolder);
        return viewHolder.getView();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View)object;
        AutoBannerViewHolder viewHolder = (AutoBannerViewHolder)view.getTag();
        container.removeView(view);
        mRecyclePool.offer(viewHolder);
    }

    public abstract int getRealPosition(int position);

    public abstract int getRealCount();

    public abstract AutoBannerViewHolder onCreateViewHolder();

    public abstract void onBindViewHolder(AutoBannerViewHolder viewHolder, int position);
}
