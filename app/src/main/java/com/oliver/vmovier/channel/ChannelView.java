package com.oliver.vmovier.channel;

import java.util.List;
import java.util.Locale;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseFragment.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.utils.Logger;

public class ChannelView extends BaseView {

    private static final String TAG = "ChannelView";

    private ChannelViewModel mViewModel;
    private BaseRVAdapter mAdapter;

    public ChannelView(@NonNull Fragment fragment) {
        super(fragment);
        mViewModel = ViewModelProviders.of(mFragment).get(ChannelViewModel.class);
    }

    @Override
    public void init(@NonNull View view) {
        super.init(view);
        Logger.trace(TAG);
        setupView();
        setupData();
    }

    private void setupView() {
        RecyclerView recyclerView = mView.findViewById(R.id.channel_content);
        final GridLayoutManager layoutManager = new GridLayoutManager(mFragment.getContext(), 2,
            LinearLayoutManager.VERTICAL, false);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    int start = layoutManager.findFirstVisibleItemPosition();
                    int end = layoutManager.findLastVisibleItemPosition();
                    for (int i = start; i <= end; ++i) {
                        View view = layoutManager.findViewByPosition(i);
                        Rect rect = new Rect();
                        if (null != view && view.getLocalVisibleRect(rect)) {
                            int height = view.getHeight();
                            int visibleHeight = rect.bottom - rect.top;
                            Logger.d(TAG, String.format(Locale.US, "check view %d, height = %d, visibleHeight = %d, visiblePercent = %f",
                                i, height, visibleHeight, visibleHeight * 1.0 / height));
                        }
                    }
                    Logger.d(TAG, String.format(Locale.US, "VisibleItem start = %d, end = %d", start, end));
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        mAdapter = new BaseRVAdapter(mFragment.getContext(), createFactory());
        recyclerView.setAdapter(mAdapter);
    }

    private IRVViewHolderFactory createFactory() {
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.channel_card_item, viewGroup, false);
                return new ChannelViewHolder(context, itemView);
            }
        };
    }

    private void setupData() {
        mViewModel.getData().observe(mFragment, new Observer<List<ChannelVO>>() {
            @Override
            public void onChanged(@Nullable final List<ChannelVO> channelVOList) {
                if (null != channelVOList) {
                    mAdapter.refresh(channelVOList);
                }
            }
        });
    }
}
