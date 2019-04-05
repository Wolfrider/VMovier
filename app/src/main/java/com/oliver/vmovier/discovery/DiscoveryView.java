package com.oliver.vmovier.discovery;

import java.util.List;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.SimpleOnItemTouchListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.oliver.vmovier.R;
import com.oliver.vmovier.base.BaseFragment.BaseView;
import com.oliver.vmovier.base.recyclerview.BaseRVAdapter;
import com.oliver.vmovier.base.recyclerview.BaseRVViewHolder;
import com.oliver.vmovier.base.recyclerview.IRVItemVO;
import com.oliver.vmovier.base.recyclerview.IRVViewHolderFactory;
import com.oliver.vmovier.core.Constant.CardType;
import com.oliver.vmovier.core.post.PostViewHolder;
import com.oliver.vmovier.core.utils.Logger;
import com.oliver.vmovier.view.RefreshLayout;

public class DiscoveryView extends BaseView {

    private static final String TAG = "DiscoveryView";

    private static final int LAST_LOADING = 3;

    private DiscoveryViewModel mViewModel;
    private RefreshLayout mRefreshLayout;
    private BaseRVAdapter mAdapter;
    private boolean mIsLoading;

    public DiscoveryView(@NonNull Fragment fragment) {
        super(fragment);
        mViewModel = ViewModelProviders.of(mFragment).get(DiscoveryViewModel.class);
    }

    @Override
    public void init(@NonNull View view) {
        super.init(view);
        setupView(view);
        mViewModel.getData().observe(mFragment, new Observer<List<IRVItemVO>>() {
            @Override
            public void onChanged(@Nullable List<IRVItemVO> baseDiscoveryVOS) {
                if (null != baseDiscoveryVOS) {
                    if (mRefreshLayout.isRefreshing()) {
                        Logger.d(TAG, "update finished");
                        mAdapter.refresh(baseDiscoveryVOS);
                        mRefreshLayout.setRefreshing(false);
                    } else if (mIsLoading) {
                        Logger.d(TAG, "loadMore finished");
                        mAdapter.append(baseDiscoveryVOS);
                        mIsLoading = false;
                    }
                }
            }
        });
    }

    private void setupView(View view) {
        mRefreshLayout = view.findViewById(R.id.discovery_refresh);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mViewModel.refresh();
            }
        });
        setupRecyclerView(view);
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.discovery_content);
        mAdapter = new BaseRVAdapter(mFragment.getContext(), createFactory());
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mFragment.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mIsLoading) {
                    if (linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.getItemCount() - LAST_LOADING) {
                        Logger.d(TAG, "loadMore");
                        mViewModel.loadMore();
                        mIsLoading = true;
                    }
                }
            }
        });
        recyclerView.addOnItemTouchListener(new SimpleOnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                View view = rv.findChildViewUnder(e.getX(), e.getY());
                if (null != view) {
                    if (view instanceof ViewGroup) {
                        return ((ViewGroup)view).onInterceptTouchEvent(e);
                    }
                    return view.dispatchTouchEvent(e);
                }
                return false;
            }
        });
    }

    private IRVViewHolderFactory createFactory() {
        return new IRVViewHolderFactory() {
            @Override
            public BaseRVViewHolder newInstance(@NonNull Context context, @NonNull ViewGroup viewGroup, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                switch (viewType) {
                    case CardType.Discovery.BANNER:
                        Logger.d(TAG, "onCreateViewHolder BannerViewHolder");
                        return new BannerViewHolder<BannerItemVO, SimpleBannerItemViewHolder>(context,
                            layoutInflater.inflate(R.layout.discovery_banner_layout, viewGroup, false), SimpleBannerItemViewHolder.class);
                    case CardType.Discovery.TITLE:
                        Logger.d(TAG, "onCreateViewHolder TitleViewHolder");
                        return new TitleViewHolder(context,
                            layoutInflater.inflate(R.layout.discovery_title_layout, viewGroup, false));
                    case CardType.POST:
                        Logger.d(TAG, "onCreateViewHolder PostViewHolder");
                        return new PostViewHolder(context,
                            layoutInflater.inflate(R.layout.post_list_item, viewGroup, false));
                    case CardType.Discovery.GRID_POST:
                        Logger.d(TAG, "onCreateViewHolder GridPostViewHolder");
                        return new GridPostViewHolder(context,
                            layoutInflater.inflate(R.layout.discovery_grid_post_layout, viewGroup, false));
                    case CardType.Discovery.ALBUM:
                        Logger.d(TAG, "onCreateViewHolder GridPostViewHolder");
                        return new BannerViewHolder<AlbumItemVO, AlbumBannerItemViewHolder>(context,
                            layoutInflater.inflate(R.layout.discovery_banner_layout, viewGroup, false), AlbumBannerItemViewHolder.class);
                    default:
                        break;
                }
                throw new IllegalArgumentException();
            }
        };
    }
}
