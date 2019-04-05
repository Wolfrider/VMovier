package com.oliver.vmovier.channel.list;

import java.util.List;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseActivity.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.base.recyclerview.RVViewHolderFactory;
import com.oliver.vmovier.channel.ChannelVO;
import com.oliver.vmovier.core.Constant.CardType;
import com.oliver.vmovier.core.post.PostVO;
import com.oliver.vmovier.core.post.PostViewHolder;
import com.oliver.vmovier.core.utils.Logger;

public class ChannelListView extends BaseView {

    private static final String TAG = "ChannelListView";

    private ChannelListViewModel mViewModel;
    private SwipeRefreshLayout mRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private BaseRVAdapter mAdapter;
    private boolean mLoadingData;

    public ChannelListView(@NonNull AppCompatActivity activity, @NonNull ChannelVO channelVO) {
        super(activity);
        mViewModel = new ChannelListViewModel(channelVO);
    }

    @Override
    public void init() {
        setupView();
        setupData();
    }

    private void setupView() {
        setupRecyclerView();
        AppCompatTextView titleTextView = mActivity.findViewById(R.id.toolbar_title);
        titleTextView.setText(mViewModel.getTitle());
        mRefreshLayout = mActivity.findViewById(R.id.channel_list_refresh);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                Logger.d(TAG, "onRefresh");
                if (!mLoadingData) {
                    mViewModel.refresh();
                }
            }
        });
        mRefreshLayout.setRefreshing(true);
        mViewModel.refresh();
    }

    private void setupRecyclerView() {
        final RecyclerView recyclerView = mActivity.findViewById(R.id.channel_list_content);
        mLayoutManager = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mLoadingData && !mRefreshLayout.isRefreshing()) {
                    if (mLayoutManager.findLastVisibleItemPosition() > mLayoutManager.getItemCount() - 3) {
                        Logger.d(TAG, "loadMore");
                        mViewModel.load();
                        mLoadingData = true;
                    }
                }
            }
        });
        mAdapter = new BaseRVAdapter(mActivity, createFactory());
        recyclerView.setAdapter(mAdapter);
    }

    private IRVViewHolderFactory createFactory() {
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                View itemView = LayoutInflater.from(context).inflate(R.layout.post_list_item, viewGroup, false);
                return new PostViewHolder(context, itemView);
            }
        };
    }

    private void setupData() {
        mViewModel.getPostData().observe(mActivity, new Observer<List<PostVO>>() {
            @Override
            public void onChanged(@Nullable List<PostVO> postVOS) {
                if (null != postVOS) {
                    if (mRefreshLayout.isRefreshing()) {
                        mAdapter.insert(postVOS);
                        mLayoutManager.scrollToPosition(0);
                        mRefreshLayout.setRefreshing(false);
                        mLoadingData = false;
                        Logger.d(TAG, "refreshing done");
                    } else if (mLoadingData) {
                        mAdapter.append(postVOS);
                        mRefreshLayout.setRefreshing(false);
                        mLoadingData = false;
                        Logger.d(TAG, "load done");
                    }
                }
            }
        });
    }

}
